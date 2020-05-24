package com.github.mayoi7.easyshop.server.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author LiuHaonan
 * @date 15:24 2020/5/18
 * @email acerola.orion@foxmail.com
 */
public interface MessageSource {

    @Output("trans-data-topic")
    MessageChannel transData();

    @Input("order-topic")
    SubscribableChannel handleOrder();

    @Output("order-send-topic")
    MessageChannel orderRequest();
}
