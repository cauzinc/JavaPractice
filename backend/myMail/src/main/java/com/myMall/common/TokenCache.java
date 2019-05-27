package com.myMall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TokenCache {
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);
    public static final String TOKEN_PREFIX = "token_";
    // LRU算法
    // 最后Build中要实现一个匿名实现类
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                // 默认的数据加载，当调用get取值的时候，如果Key没有对应的值，就用这个方法来加载
                @Override
                public String load(String s) throws Exception {
                    // 有时候我们会用返回值来执行equals等方法，为了避免空指针错误，这里不返回null，而是字符串的null
                    return "null";
                }
            });

    public static void setKey(String key, String value) {
        localCache.put(key, value);
    }

    public static String getValue(String key) {
        try {
            String value = localCache.get(key);
            if("null".equals(value)) {
                return null;
            }
            return value;
        } catch(Exception e) {
            logger.error("localCache get an error", e);
        }
        return null;
    }
}
