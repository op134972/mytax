package cn.ustb.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.ustb.core.constant.Constant;
import cn.ustb.core.permission.PermissionCheck;
import cn.ustb.core.permission.impl.PermissionCheckImpl;
import cn.ustb.nsfw.user.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		//判断登录请求
		String uri = request.getRequestURI();
		if(!uri.contains("sys/login_login.action")){//非登陆请求，进一步判断
			if(request.getSession().getAttribute(Constant.USER) != null){//已登录
				//判断是否访问nsfw服务系统
				if(uri.contains("/nsfw/")){
					User user = (User)request.getSession().getAttribute(Constant.USER);
					//权限判断
					PermissionCheck pc;
					WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
					pc = (PermissionCheck) context.getBean("permissionCheck");
					if(pc.isAccessable(user, "nsfw")){
						chain.doFilter(request, response);
					}else{
						//没有权限
						response.sendRedirect(request.getContextPath()+"/sys/login_toNoPermissionUI.action");
					}
				}else{
					chain.doFilter(request, response);
				}
			}else{//重定向至登录页面
				response.sendRedirect(request.getContextPath()+"/sys/login_login.action");
			}
		}else{//登陆请求，直接放行
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
