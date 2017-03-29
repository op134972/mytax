package cn.ustb.nsfw.user.dao;

import java.io.Serializable;
import java.util.List;

import cn.ustb.core.dao.BaseDao;
import cn.ustb.nsfw.user.entity.User;
import cn.ustb.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {
	// 根据帐号或id查询用户记录,可能有多个，用list接收
	public List<User> findUsersByAccountAndId(String account,String id);
	// 保存用户角色
	public void saveUserRole(UserRole userRole);
	//删除该用户对应的所有角色
	public void deleteUserRoleByUserId(Serializable id);
	// 根据用户Id查询该用户对应的所有用户角色
	public List<UserRole> findUserRolesByUserId(String id);
	// 根据用户帐号和密码查询用户记录
	public List<User> findUsersByAccountAndPassWord(String account,String password);
}
