package cn.ustb.nsfw.role.dao.impl;

import org.hibernate.Query;

import cn.ustb.core.dao.imp.BaseDaoImp;
import cn.ustb.nsfw.role.dao.RoleDao;
import cn.ustb.nsfw.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImp<Role> implements RoleDao {

	
	//roleDao中特有的方法
	@Override
	public void deleteRolePrivilegesByRoleId(String roleId) {
		Query query = getCurrentSession().createQuery("delete from RolePrivilege where id.role.roleId=?");
		query.setParameter(0, roleId);
		query.executeUpdate();
	}

}
