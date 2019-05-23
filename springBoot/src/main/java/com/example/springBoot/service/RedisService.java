package com.example.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 写入缓存 value 值类型 String
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存 value 值类型 List
     * @param key
     * @param value
     * @return
     */
    public boolean setList(final String key, List value) {
        boolean result = false;
        try {
            ListOperations<String, List> operations = redisTemplate.opsForList();
            operations.leftPush(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存(带有效时间)
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, long timeoutSeconds) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value, timeoutSeconds, TimeUnit.HOURS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 获取缓存
     * @param key
     * @return
     */
    public List getList(final String key) {
        List result = new ArrayList();
        try {
            ListOperations<String, List>  operations = redisTemplate.opsForList();
            result = operations.leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 删除缓存
     * @param key
     * @return
     */
    public Object delete(final String key) {
        Object result = null;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
    * 根据 key 获取当前缓存的过期时间
    * */
    public long getTimeOut(String key) {
        long time = 0l;
        try {
            time = redisTemplate.getExpire(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 消息发布
     */
    public void publishMessage() {
        stringRedisTemplate.convertAndSend("msg",String.format("我是第%o条消息", 1));
    }
}
