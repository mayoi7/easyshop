package com.github.mayoi7.easyshop.server.producer;

import com.github.mayoi7.easyshop.dto.TransData;
import com.github.mayoi7.easyshop.server.message.MessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LiuHaonan
 * @date 15:28 2020/5/18
 * @email acerola.orion@foxmail.com
 */
@Service
public class MessageProducer {

    @Resource
    private MessageSource source;

    /**
     * 发送交易消息
     * @param transData 交易数据
     * @return 返回发送消息是否成功
     */
    public boolean sendTransData(TransData transData) {
        return source.transData().send(MessageBuilder.withPayload(transData).build());
    }
}
