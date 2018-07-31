package cn.jarvan.jedis;

import cn.jarvan.commons.Serializable.SerializableUtils;
import cn.jarvan.commons.jedis.JedisUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * <b><code>JedisUtilsTest</code></b>
 * <p>
 * 测试jedis连接工具类.
 * <p>
 * <b>Creation Time:</b> 2018/7/30 16:02.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public class JedisUtilsTest {
    @Test
    public void test() throws Exception {
        JedisUtils.put(SerializableUtils.serialize("username"), SerializableUtils.serialize("liuruojing"));
        byte[] value=JedisUtils.get(SerializableUtils.serialize("username"));
        Assert.assertEquals("liuruojing",(String)SerializableUtils.deserialize(value));
    }
}
