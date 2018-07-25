package cn.jarvan.dao.user;

import cn.jarvan.model.user.PermissionType;

public interface PermissionTypeMapper {
    int insert(PermissionType record);

    int insertSelective(PermissionType record);
}