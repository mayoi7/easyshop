package com.github.mayoi7.easyshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author LiuHaonan
 * @date 12:42 2020/5/14
 * @email acerola.orion@foxmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public static final ResponseResult<Void> SUCCESS = new ResponseResult<>(20000, "Success", null);

    public ResponseResult(T data) {
        this.code = StateCode.OK.getCode();
        this.msg = StateCode.OK.getMsg();
        this.data = data;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(StateCode stateCode, T data) {
        this.code = stateCode.getCode();
        this.msg = stateCode.getMsg();
        this.data = data;
    }

    public ResponseResult(StateCode stateCode, String msg, T data) {
        this.code = stateCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    @AllArgsConstructor
    @Getter
    public enum StateCode {
        /** 请求成功 */
        OK(20000, "Success"),
        /** 请求失败 */
        FAIL(50000, "Fail"),
        /** token不合法 */
        ILLEGAL_TOKEN(50008, "Illegal token"),
        /** 其他客户端登陆 */
        OTHER_CLIENT_LOGGED(50012, "Other client logged in"),
        /** token过期 */
        TOKEN_EXPIRE(50014, "Token expire");

        private int code;
        private String msg;
    }
}
