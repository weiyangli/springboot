package com.example.springBoot;

import com.example.springBoot.mq.RedisMessage;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 消息订阅类
 */
//@Configuration
//@AutoConfigureAfter({RedisMessage.class})
public class SubscriberConfig {

    /**
     * 消息监听适配器，注入接受消息方法，输入方法名字 反射方法
     *
     * @param redisMessage
     * @return
     */
    @Bean
    public MessageListenerAdapter getMessageListenerAdapter(RedisMessage redisMessage) {
        //当没有继承MessageListener时需要写方法名字
        return new MessageListenerAdapter(redisMessage);
    }

    /**
     * 创建消息监听容器
     *
     * @param redisConnectionFactory
     * @param messageListenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.addMessageListener(messageListenerAdapter, new PatternTopic("msg"));
        return redisMessageListenerContainer;
    }
}
