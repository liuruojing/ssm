package cn.jarvan.serializable;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;

/**
 * <b><code>SerializableUtilsTest</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/7/30 15:33.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public class SerializableUtilsTest {

    @Test
    public void Test() throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("username","jarvan");
        byte[] bytes=cn.jarvan.commons.Serializable.SerializableUtils.serialize(map);
        Map<String,String> secondMap=(Map<String,String>)cn.jarvan.commons.Serializable.SerializableUtils.deserialize(bytes);
        System.out.println(secondMap.get("username"));
        Assert.assertEquals(true,map.equals(secondMap));

    }
}
