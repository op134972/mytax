package cn.ustb.nsfw.role.dao;

import cn.ustb.nsfw.role.entity.Role;

public interface RoleDao extends cn.ustb.core.dao.BaseDao<Role> {

	public void deleteRolePrivilegesByRoleId(String roleId);

}
