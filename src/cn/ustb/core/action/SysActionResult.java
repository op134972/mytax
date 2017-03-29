package cn.ustb.core.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class SysActionResult extends StrutsResultSupport {

	@Override
	protected void doExecute(String finalLocation,ActionInvocation invocation)
			throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BaseAction action = (BaseAction) invocation.getAction();
		
		
		//do sth
		//权限鉴定，跳转页面，跳转action
		System.out.println("进入了SysActionResult");
	}
}
