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
     * @Description:
     * 分类管理缓存
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
        Properties commandStats = (Properties) redisTemplate.execute((RedisCallback<Object>) connection -> connection.info("commandstats"));
        Object dbSize = redisTemplate.execute((RedisCallback<Object>) connection -> connection.dbSize());

        Map<String, Object> result = new HashMap<>(3);
        result.put("info", info);
        result.put("dbSize", dbSize);

        List<Map<String, String>> pieList = new ArrayList<>();
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

    @GetMapping("/cache/metrics/getNames")
    public Set<String> cache() {
        // 获取所有缓存信息
        final HashSet<String> cacheNames = new HashSet<>();
        for (String cacheName : cacheManager.getCacheNames()) {
            cacheNames.add(cacheName);
        }
        return cacheNames;
    }

    @GetMapping("/getKeys/{cacheName}")
    public Set<String> getCacheKeys(@PathVariable String cacheName) {
        Set<String> cacheKyes = redisTemplate.keys(cacheName + "*");
        for (Cache.Entry<Object, Object> entry : cacheManager.getCache(cacheName)) {
            final Object key = entry.getKey();
            final Object value = entry.getValue();
            log.info("获取缓存key key: {}, value:{}", key, value);
        }
        return cacheKyes;
    }

    @GetMapping("/getValue/{cacheName}/{cacheKey}")
    public SysCacheDTO getCacheValue(@PathVariable String cacheName, @PathVariable String cacheKey) {
        String cacheValue = redisTemplate.opsForValue().get(cacheKey);
        SysCacheDTO sysCache = new SysCacheDTO(cacheName, cacheKey, cacheValue);
        return sysCache;
    }

    @DeleteMapping("/clearCacheName/{cacheName}")
    public void clearCacheName(@PathVariable String cacheName) {
        Collection<String> cacheKeys = redisTemplate.keys(cacheName + "*");
        redisTemplate.delete(cacheKeys);
    }

    @DeleteMapping("/clearCacheKey/{cacheKey}")
    public void clearCacheKey(@PathVariable String cacheKey) {
        redisTemplate.delete(cacheKey);
    }

    @DeleteMapping("/clearCacheAll")
    public void clearCacheAll() {
        Collection<String> cacheKeys = redisTemplate.keys("*");
        redisTemplate.delete(cacheKeys);
    }
}
