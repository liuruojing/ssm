package cn.jarvan.commons.jedis;



import java.util.Set;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {
    private String host = "127.0.0.1";
    private int port = 6379;
    private int expire = 0;
    private int timeout = 0;
    private String password = "";
    private static JedisPool jedisPool = null;
    private static Logger logger = Logger.getLogger(RedisManager.class);
    public RedisManager() {
    }

    public void init() {
        if(jedisPool == null) {
            if(this.password != null && !"".equals(this.password)) {
                jedisPool = new JedisPool(new JedisPoolConfig(), this.host, this.port, this.timeout, this.password);
            } else if(this.timeout != 0) {
                jedisPool = new JedisPool(new JedisPoolConfig(), this.host, this.port, this.timeout);
            } else {
                jedisPool = new JedisPool(new JedisPoolConfig(), this.host, this.port);
            }
        }

    }

    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            value = jedis.get(key);
        }
        finally{
            jedisPool.returnResource(jedis);
        }

        return value;
    }

    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            jedis.set(key, value);
        } finally {
            jedisPool.returnResource(jedis);
        }

        return value;
    }

    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            jedis.set(key, value);
            if(expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }

        return value;
    }

    public void del(byte[] key) {
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            jedis.del(key);
        } finally {
            jedisPool.returnResource(jedis);
        }

    }

    public void flushDB() {
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            jedis.flushDB();
        } finally {
            jedisPool.returnResource(jedis);
        }

    }

    public Long dbSize() {
        Long dbSize = Long.valueOf(0L);
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            dbSize = jedis.dbSize();
        } finally {
            jedisPool.returnResource(jedis);
        }

        return dbSize;
    }

    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = (Jedis)jedisPool.getResource();

        try {
            keys = jedis.keys(pattern.getBytes());
        }  finally {
            jedisPool.returnResource(jedis);
        }

        return keys;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getExpire() {
        return this.expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

