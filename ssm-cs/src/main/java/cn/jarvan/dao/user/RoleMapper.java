package cn.jarvan.dao.user;

import cn.jarvan.model.user.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    @Select("select role_name from tra_user_role,tra_role where tra_user_role.role_id=#{userId} and tra_user_role.role_id=tra_role.role_id")
    List<String> selectRoleByUserId(Long userId);
}