package com.github.mayoi7.easyshop.oauth2.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.ResponseResult.StateCode;
import com.github.mayoi7.easyshop.dto.user.LoginInfo;
import com.github.mayoi7.easyshop.dto.user.LoginParam;
import com.github.mayoi7.easyshop.po.User;
import com.github.mayoi7.easyshop.service.UserService;
import com.github.mayoi7.easyshop.utils.HttpClientUtils;
import com.github.mayoi7.easyshop.utils.ValidateUtils;
import com.google.common.collect.Maps;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author LiuHaonan
 * @date 8:46 2020/5/16
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/user/")
public class LoginController {

    private static final String OAUTH_TOKEN_URL = "http://localhost:9000/oauth/token";

    @Value("${easyshop.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${easyshop.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${easyshop.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource
    public TokenStore tokenStore;

    @Reference
    private UserService userService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/health/{name}")
    public String hello(@PathVariable("name") String name) {
        return JSON.toJSONString(userService.findUserByName(name));
    }

    @PostMapping(value = "/login")
    public ResponseResult<Map<String, Object>> visitorLogin(@RequestBody @Validated LoginParam loginParam,
                                                            BindingResult bindingResult)
            throws IOException {
        // 先校验参数，只返回第一个错误消息
        String errMsg = ValidateUtils.validateFirst(bindingResult);
        if (errMsg != null) {
            return new ResponseResult<>(StateCode.ILLEGAL_PARAM, errMsg, null);
        }
        Map<String, Object> result = Maps.newHashMap();
        String tokenName = "access_token";

        Response response = requestAuthToken(loginParam.getUsername(), loginParam.getPassword());

        String json = Objects.requireNonNull(response.body()).string();
        JSONObject jsonObject = JSON.parseObject(json);

        if (Strings.isEmpty(json) || jsonObject.get(tokenName) == null) {
            // 该接口为游客接口，所有账号密码均可使用，如果不存在对应账号则会进行创建，但是禁止重名
            String encryptedPassword = passwordEncoder.encode(loginParam.getPassword());
            User user = new User(loginParam.getUsername(), encryptedPassword);
            userService.insertUser(user);
            response = requestAuthToken(user.getName(), user.getPassword());
            json = Objects.requireNonNull(response.body()).string();
            jsonObject = JSON.parseObject(json);
        }

        String token = jsonObject.get(tokenName).toString();
        result.put("token", token);
        return new ResponseResult<>(ResponseResult.StateCode.OK, "登陆成功", result);
    }

    /**
     * 向认证服务器申请资源token
     * @param username 用户名
     * @param password 密码（明文）
     * @return 返回响应结果
     */
    private Response requestAuthToken(String username, String password) {
        Map<String, String> params = Maps.newHashMap();

        params.put("username", username);
        params.put("password", password);
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        return HttpClientUtils.getInstance().postData(OAUTH_TOKEN_URL, params);
    }

    @GetMapping("/info")
    public ResponseResult<LoginInfo> queryUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginInfo info = new LoginInfo(authentication.getName());
        return new ResponseResult<>(ResponseResult.StateCode.OK, info);
    }

    @PostMapping("/logout")
    public ResponseResult<String> logout(HttpServletRequest request) {
        String token = request.getParameter("access_token");
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(accessToken);
        return new ResponseResult<>(ResponseResult.StateCode.OK, "注销成功", null);
    }

}
