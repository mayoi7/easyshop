package com.github.mayoi7.easyshop.server.controller;

import com.github.mayoi7.easyshop.dto.ResponseResult;
import com.github.mayoi7.easyshop.dto.ResponseResult.StateCode;
import com.github.mayoi7.easyshop.dto.TransData;
import com.github.mayoi7.easyshop.server.producer.MessageProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author LiuHaonan
 * @date 15:36 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@RestController
@RequestMapping("/shop/msg")
public class MessageController {

    @Resource
    private MessageProducer messageProducer;

    @GetMapping("send")
    public ResponseResult<Void> sendMsgTest() {
        // 构造测试数据
        double amount = 10.5;
        TransData data = new TransData(1L, BigDecimal.valueOf(amount));
        boolean isSuccess = messageProducer.sendTransData(data);
        if (isSuccess) {
            return ResponseResult.SUCCESS;
        } else {
            return new ResponseResult<>(StateCode.FAIL, "发送失败", null);
        }
    }
}
