package cn.ustb.login.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import cn.ustb.core.constant.Constant;
import cn.ustb.nsfw.user.entity.User;
import cn.ustb.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private User user;
	
	@Resource
	private UserService userService;
	
	private String loginResult;
	
	
	//跳转到登录页面
	public String toLoginUI(){
		
		return "loginUI";
	}
	
	//登录
	public String login(){
		//1.1、获取帐号、密码
		//1.2、根据帐号、密码查询用户记录
		//1.3、登录成功（有用户记录）
		//1.3.1、获取用户信息
		
		//1.3.3、将用户信息存入session
		//1.3.4、将用户登录信息记录到日志文件
		//1.3.5、跳转到系统首页（重定向）
		if(user != null){
			if(StringUtils.isNotBlank(user.getAccount())&&StringUtils.isNotBlank(user.getPassword())){
				List<User> list = userService.findUserByAccountAndPass(user.getAccount(),user.getPassword());
				if(list !=null&&list.size()>0){//成功
					user = list.get(0);
					//1.3.2、***、、、根据用户id将对应的用户角色设置到用户中
					user.setUserRoles(userService.findUserRolesByUserId(user.getId()));
					
					ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
					Log log = LogFactory.getLog(getClass());
					log.info("name"+user.getName()+"登录");
					return "home";
				}else{//失败
					loginResult = "登录失败：账号或者密码错误！";
				}
			}else{
				loginResult = "用户密码不能为空！";
			}
		}else{
			loginResult = "用户密码不能为空！";
		}
		return toLoginUI();
	}
	
	//注销、退出
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
		return toLoginUI();
	}
	
	public String toNoPermissionUI(){
		return "noPermissionUI";
	}
	
	
	
	
	
	

	public String getLoginResult() {
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	};
	
}
