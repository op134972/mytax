package cn.ustb.nsfw.user.action;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.ustb.core.action.BaseAction;
import cn.ustb.core.exception.ActionException;
import cn.ustb.core.exception.ServiceException;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.info.entity.Info;
import cn.ustb.nsfw.role.service.RoleService;
import cn.ustb.nsfw.user.entity.User;
import cn.ustb.nsfw.user.entity.UserRole;
import cn.ustb.nsfw.user.service.UserService;

public class UserAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1713794961687429855L;

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;
	
	private User user;
	private List<User> userList;//维护查询到的用户列表
	private String strName;
	
	//头像
	private File headImg;
	private String headImgFileName;
	private String headImgContentType;
	
	//导入的excel
	private File userExcel;
	private String userExcelFileName;
	private String userExcelContentType;
	
	//接受角色id
	private String[] roleIds;
	
	public String listUI() throws ActionException{
		QueryHelper query = new QueryHelper(User.class, "u");
		try {
			if(user != null){
				if(StringUtils.isNotBlank(user.getName())){
						user.setName(URLDecoder.decode(user.getName(), "utf-8"));
					query.addCondition("name like ?", "%"+user.getName()+"%");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult = userService.returnPageResult(query,pageNo,pageSize);
		return "listUI";
	}
	public String addUI(){
		//加载角色列表
		try {
			ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "addUI";
	}
	
	//保存新增
	public String add(){
		try {
			//保存用户
			if(user != null){
				userService.saveUserAndRole(user, roleIds);//用户映射没有维护用户角色，不能通过级联操作保存用户角色数据了。
				user = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	//异步显示头像
	public void ajaxUpload(){
		try {
			//处理用户头像
			if(headImg != null){
				String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");//保存路径
				//保存文件名 uuid+后缀
				String fileName = UUID.randomUUID().toString().replace("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf('.'));
				FileUtils.copyFile(headImg,new File(filePath,fileName));
				String headImg = "user/"+fileName;//user/开头
				
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(headImg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//跳转到编辑页面
	public String editUI(){
		try {
			ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
			//通过url传入id，获取到对应的user，跳转到edit页面，通过struts标签回显数据
			if(user != null&&StringUtils.isNotBlank(user.getId())){
				if(StringUtils.isNotBlank(user.getName())){
					strName = user.getName();
				}
				user = userService.findObjectById(user.getId());
			}
			
			//用户角色回显
			List<UserRole> userRoles = userService.findUserRolesByUserId(user.getId());
			if(userRoles != null&& userRoles.size()>0){//有角色
				//设置到roleIds中
				roleIds = new String[userRoles.size()];
				int i = 0;
				for(UserRole role:userRoles){
					roleIds[i++] = role.getId().getRole().getRoleId();
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		try {
			if(user != null){
				if(headImg != null){
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");//保存路径
					//保存文件名 uuid+后缀
					String fileName = UUID.randomUUID().toString().replace("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf('.'));
					FileUtils.copyFile(headImg,new File(filePath,fileName));
					//删除之前的头像文件
					if(StringUtils.isNotBlank(user.getHeadImg())){
						File oldImg = new File(filePath,user.getHeadImg().substring(user.getHeadImg().indexOf("user/")+5));
						oldImg.delete();
					}
					//覆盖新的头像保存信息
					user.setHeadImg("user/"+fileName);
				}
				if(roleIds != null){
					userService.updateUserAndRole(user, roleIds);
				}
				userService.update(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
		
	//根据id删除
	public String delete(){
		if(user != null && StringUtils.isNotBlank(user.getId())){
			userService.delete(user.getId());
		}
		return "list";
	}
	
	
	//批量删除
	public String deleteSelect(){
		if(selectedRow != null){
			for(String id:selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	
	public void exportExcel(){
		
		try {
			//获取列表
			userList = userService.findObjects();
			//获取response
			HttpServletResponse response = ServletActionContext.getResponse();
			//响应头
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+new String("用户列表.xls".getBytes(),"iso-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			//调用service方法
			userService.exportExcel(userList,outputStream);
			
			//关闭流，先判空（防止service里面已经关闭导致空指针异常）
			if(outputStream != null){
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String importExcel(){
		//先判断是否上传文件
		if(userExcel != null){
			//获取传入的excel,判断文件类型
			if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")){
				userService.importExcel(userExcel);
			}
		}
		return "list";
	}
	
	/**
	 *  检测用户唯一性
	 */
	public void verifyAccount(){
		try {
			if(user != null && StringUtils.isNotBlank(user.getAccount())){
				//设置头文件
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				ServletOutputStream outputStream = response.getOutputStream();
				
				List<User> list = userService.findByAccountAndId(user.getAccount(),user.getId());
				if(list.size()>0){
					outputStream.write("true".getBytes());
				}
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public File getUserExcel() {
		return userExcel;
	}
	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}
	public String getUserExcelFileName() {
		return userExcelFileName;
	}
	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}
	public String getUserExcelContentType() {
		return userExcelContentType;
	}
	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	
}
