package cn.ustb.home.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.ustb.core.constant.Constant;
import cn.ustb.core.utils.PageResult;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.complain.entity.Complain;
import cn.ustb.nsfw.complain.service.ComplainService;
import cn.ustb.nsfw.info.entity.Info;
import cn.ustb.nsfw.info.service.InfoService;
import cn.ustb.nsfw.user.entity.User;
import cn.ustb.nsfw.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {//不需继承BAseAction

	private String dept;
	
	
	@Resource
	private UserService userService;
	
	@Resource
	private ComplainService complainService;
	@Resource
	private InfoService infoService;
	
	//投诉信息
	private Complain comp;
	
	private Map<String,Object> return_map;
	
	//信息列表
	private List<Info> infoList;
	//单条信息
	private Info info;
	//维护用户的投诉列表
	private List<Complain> compList;
	
	//跳转到系统首页
	public String execute() throws Exception {
		//////////infoTypeMap
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		//跳转首页之前加载infoList,获取最新五条:新建方法
		infoList = infoService.findLatestObjects(5);
		
		//获取投诉信息表
		ActionContext.getContext().getContextMap().put("compStateMap", Complain.COMPLAIN_STATE_MAP);
		//根据用户名查询对应的投诉
			//1、获取用户名
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute(Constant.USER);
		String name = user.getName();
		QueryHelper query = new QueryHelper(Complain.class,"c");
		query.addCondition(" compName = ?", name);
		query.addOrderByProperty(" compTime",QueryHelper.ORDER_QUERY_DESC);
		PageResult pageResult = new PageResult();
		pageResult.setPageSize(5);
		compList = complainService.findObjects(query, pageResult);
		return "home";
	}
	
	//complainViewUI
	public String complainViewUI(){
		if(comp!=null &&StringUtils.isNotBlank(comp.getCompId())){
			comp = complainService.findObjectById(comp.getCompId());
		}
		return "complainViewUI";
	}
	//跳转infoViewUI之前加载Info信息
	public String infoViewUI(){
		ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
		String id = ServletActionContext.getRequest().getParameter("infoId");
		info = infoService.findObjectById(id);
		return "infoViewUI";
	}
	
	public void getEmp(){
		try {
			JSONObject jo = new JSONObject();
			if(dept!=null){
				QueryHelper qh = new QueryHelper(User.class, "u");
				qh.addCondition("dept = ?", dept);
				List<User> list = userService.findObjects(qh);
				jo.put("msg","success");
				jo.accumulate("list",list);
			}else{
				jo.put("msg","false");
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(jo.toString().getBytes("UTF-8"));
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 有struts框架时候
	 *
	 */
	public String getEmp2(){
		try {
			if(dept!=null){
				return_map = new HashMap<String, Object>();
				QueryHelper qh = new QueryHelper(User.class, "u");
				qh.addCondition("dept = ?", dept);
				List<User> list = userService.findObjects(qh);
				return_map.put("msg", "success");
				return_map.put("list", list);
			}else{
				return_map.put("msg","fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	//保存投诉信息
	public void save(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			ServletOutputStream outputStream = response.getOutputStream();
			if(comp!=null){
				Date a = new Date();
				comp.setCompTime(new Timestamp(a.getTime()));
				complainService.save(comp);
				outputStream.write("success".getBytes("UTF-8"));
			}else{
				outputStream.write("fail".getBytes("UTF-8"));
			}
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String complainAddUI(){
		return "complainAddUI";
	}
	public String reserveAddUI(){
		return "reserveAddUI";
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Complain getComp() {
		return comp;
	}
	public void setComp(Complain comp) {
		this.comp = comp;
	}
	public Map<String, Object> getReturn_map() {
		return return_map;
	}
	public void setReturn_map(Map<String, Object> return_map) {
		this.return_map = return_map;
	}


	public List<Complain> getCompList() {
		return compList;
	}

	public void setCompList(List<Complain> compList) {
		this.compList = compList;
	}

	public List<Info> getInfoList() {
		return infoList;
	}


	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}
	
}