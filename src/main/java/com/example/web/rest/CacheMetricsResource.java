package com.example.web.rest;

import com.example.service.dto.SysCacheDTO;
import java.util.*;
import javax.cache.Cache;
import javax.cache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CacheMetricsResource {

    private static final Logger log = LoggerFactory.getLogger(CacheMetricsResource.class);

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * @data: 2022/7/1-上午11:00
     * @User: zhaozhiwei
     * @method:
     * @param null :
     * @return:
     * @Description: 分类管理缓存
     * 1. 如某个bean, 某个对象放一起
     * 2. 配置信息放一起SystemParam
     */
    private final CacheManager cacheManager;

    public CacheMetricsResource(RedisTemplate<String, String> redisTemplate, CacheManager cacheManager) {
        this.redisTemplate = redisTemplate;
        this.cacheManager = cacheManager;
    }

    @GetMapping("/cache/metrics")
    public Map getInfo() throws Exception {
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info());
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());
        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        // 命令使用频次, 百分比, 饼图
        List<Map<String, String>> pieList = new ArrayList<>();
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
        commandStats
            .stringPropertyNames()
            .forEach(key -> {
                Map<String, String> data = new HashMap<>(2);
                String property = commandStats.getProperty(key);
                data.put("name", StringUtils.removeStart(key, "cmdstat_"));
                data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
                pieList.add(data);
            });
        result.put("commandStats", pieList);
        return result;
    }

    /**
     * @data: 2022/7/1-下午10:19
     * @User: zhaozhiwei
     * @method: cache
     * @return: java.util.HashSet<com.example.service.dto.SysCacheDTO>
     * @Description: 所有缓存都要在com.example.config.CacheConfiguration#cacheManagerCustomizer()注册
     * 方便统一管理
     */
    @GetMapping("/cache/metrics/getNames")
    public HashSet<SysCacheDTO> cache() {
        // 获取所有缓存信息
        final HashSet<SysCacheDTO> cacheNames = new HashSet<>();
        for (String cacheName : cacheManager.getCacheNames()) {
            cacheNames.add(new SysCacheDTO(cacheName, cacheName));
        }
        return cacheNames;
    }

    @GetMapping("/cache/metrics/getKeys/{cacheName}")
    public Set<String> getCacheKeys(@PathVariable String cacheName) {
        //        Set<String> cacheKeys = redisTemplate.keys(cacheName + "*");
        final HashSet<String> cacheKeys = new HashSet<>();
        for (Cache.Entry<Object, Object> entry : cacheManager.getCache(cacheName)) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            log.info("获取缓存key key: {}, value:{}", key, value);
            cacheKeys.add(key.toString());
        }
        return cacheKeys;
    }

    @GetMapping("/cache/metrics/getValue/{cacheName}/{cacheKey}")
    public SysCacheDTO getCacheValue(@PathVariable String cacheName, @PathVariable String cacheKey) {
        for (Cache.Entry<Object, Object> entry : cacheManager.getCache(cacheName)) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            log.info("获取缓存key key: {}, value:{}", key, value);
            if (String.valueOf(key).equals(cacheName + "#" + cacheKey)) {
                // value是hibernate的缓存对象, 不能直接用redisTemplate读取成String
                //        String cacheValue = redisTemplate.opsForValue().get(cacheKey);
                //                class org.hibernate.cache.internal.CacheKeyImplementation cannot be cast to class java.lang.String
                return new SysCacheDTO(cacheName, cacheKey, value.toString());
            }
        }
        return null;
    }

    @DeleteMapping("/cache/metrics/clearCacheName/{cacheName}")
    public void clearCacheName(@PathVariable String cacheName) {
        Collection<String> cacheKeys = redisTemplate.keys(cacheName + "*");
        redisTemplate.delete(cacheKeys);
    }

    @DeleteMapping("/cache/metrics/clearCacheKey/{cacheKey}")
    public void clearCacheKey(@PathVariable String cacheKey) {
        redisTemplate.delete(cacheKey);
    }

    @DeleteMapping("/cache/metrics/clearCacheAll")
    public void clearCacheAll() {
        Collection<String> cacheKeys = redisTemplate.keys("*");
        redisTemplate.delete(cacheKeys);
    }
}
