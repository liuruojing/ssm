package cn.jarvan.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * <b><code>MyShiroFilterFactoryBean</code></b>
 * <p>
 * 继承shiro的ShiroFilterFactoryBean，实现权限动态加载.
 * <p>
 * <b>Creation Time:</b> 2018/7/25 9:11.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean{
    /**
     * 得到数据库连接.
     *
     * @param
     * @return Connection
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    private Connection getConnection(){
        Properties prop = new Properties();
        Connection conn=null;
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();// 读取属性文件xxxxx.properties
            InputStream in = classLoader.getResourceAsStream("db.properties");
            prop.load(in); /// 加载属性列表
            String driver=prop.getProperty("driver");
            String url=prop.getProperty("url");
            String username=prop.getProperty("username");
            String password=prop.getProperty("password");
            Class.forName(driver);
            conn= DriverManager.getConnection(url, username, password);

        }
        catch (Exception e){
             e.printStackTrace();
        }
        return conn;

    }

    /**
     * 动态加载数据库的权限信息.
     *
     * @param
     * @return
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @Override
    public void setFilterChainDefinitions(String definitions){

        Connection conn=getConnection();
        Map<String,String> filterChainDefinitionMap= new LinkedHashMap<>();/*坑点！！！HashMap是无序的,不能用！！！*/
        Statement pre= null;
        ResultSet rs=null;
        try {
            pre = conn.createStatement();
            rs=pre.executeQuery("select * from tra_permission");
            while(rs.next()){
                filterChainDefinitionMap.put(rs.getString("perimission_url"),"perms["+rs.getString("permission_name")+"]");
            }
            filterChainDefinitionMap.put("/**","anon");
            if(rs != null) {
                rs.close();
            }
            if(pre!=null){
                pre.close();
            }
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


}
