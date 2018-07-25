package cn.jarvan.dao.user;

import cn.jarvan.model.user.RolePermission;

import java.util.List;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long rolePermissionId);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long rolePermissionId);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    List<Long> selectByRoleId(Long roleId);
}