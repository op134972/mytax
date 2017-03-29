package cn.ustb.nsfw.role.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ustb.core.service.impl.BaseServiceImpl;
import cn.ustb.nsfw.role.dao.RoleDao;
import cn.ustb.nsfw.role.entity.Role;
import cn.ustb.nsfw.role.service.RoleService;
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {
	
	private RoleDao roleDao;

	/**
	 * set方式注入roleDao，将继承的BaseServiceImpl中维护的BaseDao也注入。
	 * @param roleDao
	 */
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}
	public RoleDao getRoleDao() {
		return roleDao;
	}
	
	/**
	 * 重写update方法
	 */
	@Override
	public void update(Role role) {
		//更新之前先清除角色权限，update底层里面会getSession操作数据，在保存完之后，role会和数据库中的数据完全同步，
		//简单粗暴；将该角色对应的所有权限删除
		roleDao.deleteRolePrivilegesByRoleId(role.getRoleId());
		
		
		roleDao.update(role);
	}
	
	/**
	 * delete操作不需要deleteRolePrivilegesByRoleId，因为配置了级联操作。再删除role的同时会删除rolePrivilege表中的数据。
	 * cascade和inverse没有冲突操作
	 */
	
	
	
	
}
