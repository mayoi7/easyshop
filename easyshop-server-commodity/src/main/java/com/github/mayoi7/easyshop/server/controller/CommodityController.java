package com.github.mayoi7.easyshop.server.controller;

import com.alibaba.fastjson.JSON;
import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.ResponseResult.StateCode;
import com.github.mayoi7.easyshop.dto.commodity.CommodityParam;
import com.github.mayoi7.easyshop.po.Commodity;
import com.github.mayoi7.easyshop.po.User;
import com.github.mayoi7.easyshop.service.CommodityService;
import com.github.mayoi7.easyshop.service.UserService;
import com.github.mayoi7.easyshop.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuHaonan
 * @date 10:17 2020/5/17
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/shop/commodity")
@Slf4j
public class CommodityController {

    @Value("${image.upload.path}")
    private String filePath;

    @Reference
    private UserService userService;

    @Resource
    private CommodityService commodityService;

    @GetMapping("/{id}")
    public ResponseResult<String> findById(@PathVariable("id") Long id) {
        Commodity commodity = commodityService.findById(id);

        return new ResponseResult<>(JSON.toJSONString(commodity));
    }

    @GetMapping("/list/{page}")
    public ResponseResult<List<Commodity>> listCommodities(
            @PathVariable(value = "page", required = false) int page) {
        List<Commodity> commodities = commodityService.findListByIdDescInPage(page);
        if (commodities == null || commodities.isEmpty()) {
            log.warn("[COMMODITY] commodity list is null <page={}>", page);
            return new ResponseResult<>("查询成功", new ArrayList<>());
        } else {
            return new ResponseResult<>("查询成功", commodities);
        }
    }

    @PostMapping("/")
    public ResponseResult<Commodity> createCommodity(@RequestBody CommodityParam commodityParam) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findUserByName(name);
        if (user == null) {
            log.error("[AUTH] logged user is not found <name={}>", name);
            return new ResponseResult<>(StateCode.LOGIN_ABNORMAL, null);
        }

        MultipartFile imageFile = commodityParam.getImage();
        // 如果图片存在，则保存图片
        String fileName = null;
        if (imageFile != null && imageFile.getOriginalFilename() != null) {
            fileName = FileUtils.getRandomName(imageFile.getOriginalFilename());
            File file = FileUtils.saveFile(filePath, fileName);
            try {
                imageFile.transferTo(file);
            } catch (IOException e) {
                log.error("[FILE] image saved fail <path={}>", filePath + fileName);
                return new ResponseResult<>(StateCode.FILE_SAVED_FAIL, "文件保存失败", null);
            }
            log.info("[FILE] image save success <path={}>", filePath + fileName);
        }
//        return new ResponseResult<>("success", null);
        Commodity commodity = new Commodity(commodityParam, fileName);
        commodityService.saveCommodity(commodity);
        return new ResponseResult<>("添加成功", commodity);
    }

    @GetMapping("/img")
    public void loadImage(String image, HttpServletResponse response) {
        byte[] data = FileUtils.loadFile(filePath + image);
        if (data == null) {
            return;
        }
        response.setContentType("image/jpeg");
        try (OutputStream os = response.getOutputStream()) {
            os.write(data);
        } catch (IOException ioe) {
            log.error("[HTTP] write to response fail");
        }
    }
}
