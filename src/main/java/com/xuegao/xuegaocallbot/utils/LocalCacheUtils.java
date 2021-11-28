package com.xuegao.xuegaocallbot.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xuegao
 * @version 1.0
 * @date 2021/11/27 18:33
 */
public class LocalCacheUtils {
    private static final Logger log = LoggerFactory.getLogger(LocalCacheUtils.class);

    /**
     * 使用google guava缓存处理
     */
    private static Cache<String, Object> cache;

    static {
        cache = CacheBuilder.newBuilder().maximumSize(10000)
                .expireAfterWrite(7100, TimeUnit.SECONDS)
                .initialCapacity(10)
                .removalListener(c ->
                        log.info("[xuegao-call-bot][LocalCacheUtils][static initializer][被移除缓存key={}value={}]",
                                c.getKey(), c.getValue()))
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String s) throws Exception {
                        // 缓存中拿不到，会走到这里
                        throw new RuntimeException("缓存中没有找到key=" + s);
                    }
                });
    }

    /**
     * 获取缓存
     */
    public static Object get(String key) {
        return StringUtils.isNotBlank(key) ? cache.getIfPresent(key) : null;
    }

    /**
     * 放入缓存
     */
    public static void put(String key, Object value) {
        if (StringUtils.isNotBlank(key) && ObjectUtils.isNotEmpty(value)) {
            cache.put(key, value);
        }
    }

    /**
     * 移除缓存
     */
    public static void remove(String key) {
        if (StringUtils.isNotBlank(key)) {
            cache.invalidate(key);
        }
    }

    /**
     * 批量删除缓存
     */
    public static void remove(List<String> keys) {
        if (ObjectUtils.isNotEmpty(keys)) {
            cache.invalidateAll(keys);
        }
    }

}