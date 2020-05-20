package com.github.mayoi7.easyshop.statistic.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author LiuHaonan
 * @date 16:39 2020/5/18
 * @email acerola.orion@foxmail.com
 */
public interface DataStatisticSink {

    @Input("trans-data-topic")
    SubscribableChannel transData();
}
