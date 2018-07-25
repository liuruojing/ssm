package cn.jarvan.dao.user;

import cn.jarvan.model.user.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long userRoleId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long userRoleId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<Long> selectByUserId(Long userId);
}