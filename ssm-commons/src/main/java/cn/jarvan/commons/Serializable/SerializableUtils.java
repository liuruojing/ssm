package cn.jarvan.commons.Serializable;

import java.io.*;

/**
 * <b><code>SerializableUtils</code></b>
 * <p>
 * 用于对象的序列化和反序列化的工具类.
 * <p>
 * <b>Creation Time:</b> 2018/7/30 15:19.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public class SerializableUtils {
    /**
     * 序列化.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static <E>  byte[] serialize(E e) throws IOException{
        if(e!=null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(e);
            byte[] bytes= bos.toByteArray();
            bos.close();
            oos.close();
            return bytes;
        } else {
            return null;
        }

        }

    /**
     * 反序列化.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    public static Object deserialize(byte[] bytes) throws Exception{
        if (bytes!=null) {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Object object = ois.readObject();
            bis.close();
            ois.close();
            return object;
        } else{
            return null;
        }

        }

}