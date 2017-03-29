package cn.ustb.nsfw.user.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.ustb.core.service.BaseService;
import cn.ustb.nsfw.user.entity.User;
import cn.ustb.nsfw.user.entity.UserRole;

public interface UserService extends BaseService<User>{
	//增加特有的方法
	
	//根据账户和id查询用户，检测用户唯一性
	public List<User> findByAccountAndId(String account,String id);
	
	//保存用户及其对应的角色
	public void saveUserAndRole(User user,String... roleIds);//0个或者多个
	//更新用户及其对应的角色
	public void updateUserAndRole(User user,String... roleIds);
	
	//根据用户id查询该用户所有的对应角色
	public List<UserRole> findUserRolesByUserId(String id);
	//根据用户账号和密码查询用户记录
	public List<User> findUserByAccountAndPass(String account,String password);
	
	//导出用户列表
	public void exportExcel(List<User> userList,ServletOutputStream outputStream);
	//导入用户列表
	public void importExcel(File userExcel);
}
