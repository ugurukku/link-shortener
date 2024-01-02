package com.ugurukku.linkshortener.service.redis;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService<T> {

    private final RedissonClient redissonClient;

    public void set(String key, T value, long minutes){
        set(key, value, Duration.ofMinutes(minutes));
    }

    public void set(String key, T value, Duration duration) {
        RBucket<T> testKey = redissonClient.getBucket(key);
        testKey.set(value, duration);
    }

    public T get(String key) {
        RBucket<T> testKey1 = redissonClient.getBucket(key);
        return testKey1.get();
    }

    public void delete(String otp) {
        RBucket<Object> bucket = redissonClient.getBucket(otp);
        bucket.delete();
    }

}
