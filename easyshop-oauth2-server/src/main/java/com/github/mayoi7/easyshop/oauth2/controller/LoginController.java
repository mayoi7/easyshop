package com.github.mayoi7.easyshop.oauth2.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.user.LoginInfo;
import com.github.mayoi7.easyshop.dto.user.LoginParam;
import com.github.mayoi7.easyshop.service.UserService;
import com.github.mayoi7.easyshop.utils.HttpClientUtils;
import com.google.common.collect.Maps;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.Map;

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

    @GetMapping("/health/{name}")
    public String hello(@PathVariable("name") String name) {
        return JSON.toJSONString(userService.findUserByName(name));
    }

    @PostMapping(value = "/login")
    public ResponseResult<Map<String, Object>> visitorLogin(@RequestBody @Validated LoginParam loginParam)
            throws IOException {
        // 该接口为游客接口，所有账号密码均可使用，如果不存在对应账号则会进行创建，但是禁止重名

        Map<String, Object> result = Maps.newHashMap();

        Map<String, String> params = Maps.newHashMap();
        params.put("username", loginParam.getUsername());
        params.put("password", loginParam.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);
        Response response = HttpClientUtils.getInstance().postData(OAUTH_TOKEN_URL, params);
        String json = response.body().string();
        JSONObject jsonObject = JSON.parseObject(json);

        if (Strings.isEmpty(json) || jsonObject.get("access_token") == null) {
            return new ResponseResult<>(ResponseResult.StateCode.FAIL, "账号或密码错误", null);
        }

        String token = jsonObject.get("access_token").toString();
        result.put("token", token);
        return new ResponseResult<>(ResponseResult.StateCode.OK, "登陆成功", result);
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
