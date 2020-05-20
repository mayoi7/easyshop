package com.github.mayoi7.easyshop.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数校验工具类（主要用于validated结果处理）
 * @author LiuHaonan
 * @date 9:25 2020/5/20
 * @email acerola.orion@foxmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidateUtils {

    /**
     * 处理并返回第一个错误的响应消息
     * @param bindingResult 错误集
     * @return 如果没有错误，则返回null，否则返回第一个错误的响应消息
     */
    public static String validateFirst(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors().get(0).getDefaultMessage();
        } else {
            return null;
        }
    }

    /**
     * 处理并返回所有错误的响应消息
     * @param bindingResult 错误集
     * @return 如果没有错误，则返回null，否则返回所有错误的响应消息列表
     */
    public static List<String> validateAll(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>(bindingResult.getErrorCount());
            bindingResult.getAllErrors().forEach(error -> {
                errors.add(error.getDefaultMessage());
            });
            return errors;
        } else {
            return null;
        }
    }
}
