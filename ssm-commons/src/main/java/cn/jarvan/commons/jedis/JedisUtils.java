package cn.jarvan.commons.jedis;

import redis.clients.jedis.Jedis;

import java.io.InputStream;
import java.util.Properties;

/**
 * <b><code>JedisUtils</code></b>
 * <p>
 * jedis工具类.
 * <p>
 * <b>Creation Time:</b> 2018/7/30 15:46.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public class JedisUtils {
    private static String host;
    private static int port;
    private static String auth;

    /**
     *
     * 初始化加载jedis配置信息.
     *
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    static{
    Properties prop = new Properties();
        try {
            ClassLoader classLoader =JedisUtils.class.getClassLoader();// 读取属性文件xxxxx.properties
            InputStream in = classLoader.getResourceAsStream("jedis.properties");
            prop.load(in); /// 加载属性列表
            host = prop.getProperty("host");
            port = Integer.parseInt(prop.getProperty("port"));
            auth = prop.getProperty("auth");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        }

    /**
     * 存入key-value.
     *
     * @param key
     * @param value
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static void put(byte[] key,byte[] value){
        Jedis jedis = new Jedis(host,port); // 指定端口
        if(auth!=null&&!auth.equals("")) {
            jedis.auth(auth);  //指定密码
        }
        jedis.set(key, value);
        if(jedis != null) {
            jedis.close();
        }
    }

    /**
     * 获取指定key.
     *
     * @param  key
     * @return byte[]
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static byte[] get(byte[] key){
        Jedis jedis = new Jedis(host,port); // 指定端口
        if(auth!=null&&!auth.equals("")) {
            jedis.auth(auth);  //指定密码
        }
        byte[] bytes = jedis.get(key);
        if(jedis != null) {
            jedis.close();
        }
        return bytes;
    }

    /**
     * 删除指定key.
     *
     * @param key
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static void remove(byte[] key){
        Jedis jedis = new Jedis(host,port); // 指定端口
        if(auth!=null&&!auth.equals("")) {
            jedis.auth(auth);  //指定密码
        }
        jedis.del(key);
        if(jedis != null) {
            jedis.close();
        }
    }
}
