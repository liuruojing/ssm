package cn.jarvan.shiro;

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
    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return new ShiroRedisCache<K,V>(name);
    }

}
