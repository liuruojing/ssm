package cn.jarvan.serviceImpl;

import cn.jarvan.dao.user.PermissionMapper;
import cn.jarvan.model.user.Permission;
import cn.jarvan.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <b><code>PermissionServiceImpl</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/7/26 16:34.
 *
 * @author liuruojing
 * @since ssm 0.1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public List<Permission> getAllPermission() {
        return permissionMapper.selectAll();
    }
}
