package cn.jarvan.shiro;

import cn.jarvan.dao.user.PermissionMapper;
import cn.jarvan.model.user.Permission;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <b><code>MyShiroFilterFactoryBean</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/7/25 9:11.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean{
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public void setFilterChainDefinitions(String definitions){
//        Map<String,String> filterChainDefinitionMap= new HashMap<>();
//        List<Permission> permissions = permissionMapper.selectAll();
//        Iterator<Permission> it = permissions.iterator();
//        Permission permission = null;
//        while (it.hasNext()) {
//            permission = it.next();
//            //将需要权限的路径放入拦截链(特定的权限,系统所有的链接全放到permission表，一个链接对应一个权限，系统初始化录入时录入所有初始数据)
//            filterChainDefinitionMap.put(permission.getPerimissionUrl(),"perms["+permission.getPermissionName()+"]");
//        }
//            //未录入的路径则不需要任何权限，也不需要登录
//            filterChainDefinitionMap.put("/**","anon");
        super.setFilterChainDefinitions(definitions);
    }


}
