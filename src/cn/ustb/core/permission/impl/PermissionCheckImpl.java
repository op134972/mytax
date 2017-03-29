package cn.ustb.core.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.ustb.core.permission.PermissionCheck;
import cn.ustb.nsfw.role.entity.Role;
import cn.ustb.nsfw.role.entity.RolePrivilege;
import cn.ustb.nsfw.user.entity.User;
import cn.ustb.nsfw.user.entity.UserRole;
import cn.ustb.nsfw.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {

	@Resource
	private UserService userService;
	
	
	@Override
	public boolean isAccessable(User user, String code) {
		//1、获取用户对因的所有权限集合
		//多次访问数据库，影响效率,应在user中维护userRole，
		//并且在登陆时候查询一次放入session中
		List<UserRole> userRoles = user.getUserRoles();
		if(userRoles != null){
			userRoles = userService.findUserRolesByUserId(user.getId());
		}
		//2、将code和集合比对
		Role role = null;
		for(UserRole ur:userRoles){
			role = ur.getId().getRole();
			for(RolePrivilege rp:role.getRolePrivileges()){
				if(code.equals(rp.getId().getCode())){
					//有权限
					return true;
				}
			}
		}
		return false;
	}

}
