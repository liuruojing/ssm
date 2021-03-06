package cn.jarvan.shiro;

import cn.jarvan.commons.Serializable.SerializableUtils;
import cn.jarvan.commons.jedis.RedisManager;
import org.apache.log4j.Logger;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.SimplePrincipalCollection;

import java.util.Collection;
import java.util.Set;

/**
 * <b><code>ShiroRedisCache</code></b>
 * <p>
 * 自定义缓存的操纵类.
 * <p>
 * <b>Creation Time:</b> 2018/7/30 11:49.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public class ShiroRedisCache<K,V> implements Cache<K,V>{
    //shiro缓存名字前缀，避免和其他缓存混淆
    private static final String CACHE_NAME = "shiro_cache:";
    //缓存的名字
    private String name;
    private static Logger logger = Logger.getLogger(Cache.class);
    public ShiroRedisCache(){}
    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
        this.redisManager.init();
    }

    private RedisManager redisManager;
    public ShiroRedisCache(String name,RedisManager redisManager) {
        this.name=name;
        setRedisManager(redisManager);
    }
    @Override
    public V get(K key) throws CacheException {
        try {
            byte[] keyByte;
            if(key instanceof String) {
                keyByte = buildCacheKey(key).getBytes();
            }
            else{
                keyByte = SerializableUtils.serialize(key);
            }
            byte[] valueByte;
            valueByte = redisManager.get(keyByte); //查找缓存
            return (V) SerializableUtils.deserialize(valueByte); //反序列化成对象
        }
        catch(Exception e){
            logger.error(e);
            throw new CacheException(e);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        try {
            V previos=get(key); //获取之前的value
            byte[] keyByte;
            if(key instanceof String) {
                keyByte = buildCacheKey(key).getBytes();
            }
            else {
                keyByte = SerializableUtils.serialize(key);
            }

            byte[] keyValue = SerializableUtils.serialize(value);
            redisManager.set(keyByte, keyValue);
            return previos;
        }
        catch (Exception e){
            logger.error(e);
            throw  new CacheException(e);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        try {
            //获取上一次存的value
            V previous = get(key);
            byte[] keyByte;
            if(key instanceof String){
                keyByte=buildCacheKey(key).getBytes();
            } else {
                keyByte=SerializableUtils.serialize(key);
            }
            redisManager.del(keyByte);
            return previous;
        }
        catch(Exception e) {
            logger.error(e);
            throw new CacheException(e);
        }
    }

    @Override
    public void clear() throws CacheException {
        // 以下方法无须重写 不会被授权调用 无关紧要
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    /**
     * 构建一个key的名字.
     *
     * @param key
     * @return String
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    private String buildCacheKey(K key){
        return CACHE_NAME+name+":"+key;
    }
}