package cn.ustb.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import cn.ustb.core.service.impl.BaseServiceImpl;
import cn.ustb.core.utils.ExcelUtil;
import cn.ustb.nsfw.role.entity.Role;
import cn.ustb.nsfw.user.dao.UserDao;
import cn.ustb.nsfw.user.entity.User;
import cn.ustb.nsfw.user.entity.UserRole;
import cn.ustb.nsfw.user.entity.UserRoleId;
import cn.ustb.nsfw.user.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	private UserDao userDao;
	
	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);//注入baseServiceImpl父类中的baseDao。只有set注入方式才能设置多行代码。
		this.userDao = userDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @return something
	 */
	public List<User> findByAccountAndId(String account, String id) {
		return userDao.findUsersByAccountAndId(account, id);
	}

	@Override
	public void saveUserAndRole(User user, String... roleIds) {
		//1、保存用户
		save(user);
		/**
		 * session缓存中已经同步了user，所以user.getId()能取到值
		 */
		//2、保存与用户角色
		if(roleIds != null){
			for(String roleId:roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}


	@Override
	public List<UserRole> findUserRolesByUserId(String id) {
		
		return userDao.findUserRolesByUserId(id);
	}

	@Override
	public List<User> findUserByAccountAndPass(String account, String password) {
		return userDao.findUsersByAccountAndPassWord(account, password);
	}

	@Override
	public void exportExcel(List<User> userList,ServletOutputStream outputStream) {
		ExcelUtil.exportExcel(userList, outputStream);
	}

	

	@Override
	public void importExcel(File userExcel) {
		try {
			FileInputStream fis = new FileInputStream(userExcel);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheetAt(0);
			if(sheet.getPhysicalNumberOfRows()>2){
				//存在用户内容
				User user = null;
				for(int i = 2;i<=sheet.getPhysicalNumberOfRows();i++){//i=2,第三行开始
					/**
					 * 列表模板：姓名，用户名，部门，性别，邮箱，电话，生日
					 */
					user = new User();
					String name = sheet.getRow(i).getCell(0).getStringCellValue();
					user.setName(name);
					String account = sheet.getRow(i).getCell(1).getStringCellValue();
					user.setAccount(account);
					String dept = sheet.getRow(i).getCell(2).getStringCellValue();
					user.setDept(dept);
					String gender = sheet.getRow(i).getCell(3).getStringCellValue();
					user.setGender("男".equals(gender));
					String email = sheet.getRow(i).getCell(4).getStringCellValue();
					user.setEmail(email);
					
					String mobile;
					try {//phone可能在表中是文本或者数字
						mobile = sheet.getRow(i).getCell(5).getStringCellValue();
					} catch (Exception e) {
						//科学计数方式的数值
						double dMobile = sheet.getRow(i).getCell(5).getNumericCellValue();
						//BigDecimal将科学计数方式的数值转为一个正常的数值并转为字符串
						mobile = BigDecimal.valueOf(dMobile).toString();
					}
					user.setMobile(mobile);
					
					Date birthday = sheet.getRow(i).getCell(6).getDateCellValue();
					user.setBirthday(birthday);
					
					
					//设置别的属性
					user.setState(User.User_STATE_VALID);
					user.setPassword("123456");
					save(user);
				}
			}
			fis.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 没有了级联，删除需要重写
	 */
	@Override
	public void delete(Serializable id) {
		//删除对应的角色
		userDao.deleteUserRoleByUserId(id);
		//删除用户
		super.delete(id);
	}

	
	/**
	 * UserService接口特有的更新方法
	 */
	@Override
	public void updateUserAndRole(User user,String... roleIds) {
		//1、去掉该用户对应的所有角色
		userDao.deleteUserRoleByUserId(user.getId());
		//2、更新用户
		super.update(user);
		//3、保存用户角色（没有级联）
		if(roleIds != null){
			for(String id:roleIds){
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(id),user.getId())));
			}
		}
	}

	


}
