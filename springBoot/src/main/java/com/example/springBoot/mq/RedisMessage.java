package com.example.springBoot.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;


@Component
public class RedisMessage implements MessageListener {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
        String msg = serializer.deserialize(message.getBody());
        System.out.println("接收到的消息是：" + msg);
    }
}

