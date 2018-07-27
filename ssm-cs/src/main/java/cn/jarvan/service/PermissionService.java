package cn.jarvan.service;

import cn.jarvan.model.user.Permission;

import java.util.List;

/**
 * <b><code>PermissionService</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/7/26 16:33.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
public interface PermissionService {
    List<Permission> getAllPermission();

}
