package cn.jarvan.shiro;

import cn.jarvan.commons.jedis.RedisManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * <b><code>RedisCacheManager</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/7/30 11:43.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public class RedisCacheManager implements CacheManager {
    private RedisManager redisManager;

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroRedisCache<K,V>(name,redisManager);
    }

}
