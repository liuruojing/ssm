package cn.jarvan.shiro.realm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.jarvan.dao.user.*;
import cn.jarvan.model.user.Permission;
import cn.jarvan.model.user.Role;
import cn.jarvan.model.user.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 认证.
     *
     * @param token
     * @return AuthenticationInfo
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @Override // 认证
    protected final AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usertoken = (UsernamePasswordToken) token;
        String username = usertoken.getUsername();
        User user = userMapper.selectByUsername(username);
        if (user == null) {  //账号不存在
           throw new UnknownAccountException();
        } else {  //账号存在，交给shiro比对token与user中的密码
            return new SimpleAuthenticationInfo(user, user.getUserPassword(), getName());
        }
    }

    /**
     * 授权，会在需要验证权限的时候被shiro调用，如果开启了缓存则只会第一次验证的时候被调用.
     *
     * @param principals
     * @return AuthorizationInfo
     * @author liuruojing
     * @since ${PROJECT_NAME} 0.1.0
     */
    @Override
    protected final AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {

        User user = (User) principals.getPrimaryPrincipal();
        Set<String> roles = new HashSet<>();  //角色集合
        Set<String> permissions = new HashSet<>(); //权限集合
        //根据userId查出所拥有的roleId
        List<Long> roleIds = userRoleMapper.selectByUserId(user.getUserId());
        Iterator<Long> iterator = roleIds.iterator();
        while (iterator.hasNext()) {
            Long roleId = iterator.next();
            Role role = roleMapper.selectByPrimaryKey(roleId);
            //将角色放入角色集合
            roles.add(role.getRoleName());
            //根据角色查出权限Id
            List<Long> permissionIds = rolePermissionMapper.selectByRoleId(roleId);
            Iterator<Long> pIterator = permissionIds.iterator();
            while (pIterator.hasNext()) {
                Long permissionId = (Long) pIterator.next();
                Permission permission = permissionMapper.selectByPrimaryKey(permissionId);
                //将所有权限放入权限集合
                permissions.add(permission.getPermissionName());
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles); //放入角色
        info.setStringPermissions(permissions); //放入权限
        return info;
    }
}

