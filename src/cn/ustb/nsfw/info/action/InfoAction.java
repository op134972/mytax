package cn.ustb.nsfw.info.action;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.ustb.core.action.BaseAction;
import cn.ustb.core.exception.ActionException;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.info.entity.Info;
import cn.ustb.nsfw.info.service.InfoService;

import com.opensymphony.xwork2.ActionContext;

public class InfoAction extends BaseAction {

	@Resource
	private InfoService infoService;
	
	private Info info;
	
	private String strTitle;//保存查询语句
	
	//列表
	public String listUI() throws ActionException{
		//String sql = "from Info i";//别名
		QueryHelper query = new QueryHelper(Info.class, "i");
		try {
		
			if(info != null){
				if(StringUtils.isNotBlank(info.getTitle())){
					//存在查询条件
					/*sql += " where i.title like ?";
					params.add("%"+info.getTitle()+"%");
					sql += " order by i.createTime desc";*/
					info.setTitle(URLDecoder.decode(info.getTitle(), "utf-8"));
					query.addCondition("title like ?", "%"+info.getTitle()+"%");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//String sql = query.getSql();
		//List<Object> params = query.getParams();
		//创建QueryHelper对象，传入（类名，title条件），-->返回sql语句和param参数
		//或者创建新的findObject(QueryHelperf)方法
		query.addOrderByProperty("createTime", QueryHelper.ORDER_QUERY_DESC);
		
		//infoList = infoService.findObjects(query,pageResult);
		//infoList = infoService.findObjects(query);
		//infoList = infoService.findObjects(sql, params);
		
		pageResult = infoService.returnPageResult(query,pageNo,pageSize);
		ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);//加载所有的信息类型map
		return "listUI";
	}
	
	
	
	public String addUI(){
		//加载角色列表
		try {
			ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);//加载所有的信息类型map
			info = new Info();
			info.setCreateTime(new Timestamp(new Date().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addUI";
	}
	/* 
	 * 删除和编辑和添加操作时候的条件回显问题：进行的redirectAction,页面中的title条件清空重置。在struts配置params中保存title数据解决删除操作出现的问题。
	 * 新的问题，在编辑和添加时，新添加的标题替代了搜索条件，是因为title重名，需重新定义一个变量接收搜索条件，乱码问题在struts.xml中配置encode=true
	 * 并且在action中解码。
	 * 添加后出现搜索的解决办法：在add方法完成save以后清空info的信息
	 * 编辑以后搜索条件清空的解决办法：跳转到编辑页面之前，传递条件给strTitle。编辑页面用隐藏域保存，跳转到list再取回来。
	 * 需配置<param name="info.title">${strTitle}</param>...
	 * */
	//保存新增
	public String add(){
		//保存用户
		if(info != null){
			System.out.println(info.getCreator());
			infoService.save(info);
		}
		info = null;//清空info中的信息，防止添加以后出现搜索
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		try {
			ActionContext.getContext().getContextMap().put("infoTypeMap",Info.INFO_TYPE_MAP);//加载所有的信息类型map
			//通过url传入id，获取到对应的info，跳转到edit页面，通过struts标签回显数据
			if(info != null&&StringUtils.isNotBlank(info.getInfoId())){
				if(StringUtils.isNotBlank(info.getTitle())){
					strTitle = info.getTitle();//存储搜索条件，下一步跳转至编辑页面，隐藏域保存
				}
				info = infoService.findObjectById(info.getInfoId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		try {
			if(info != null){
				infoService.update(info);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
		
	//根据id删除
	public String delete(){
		if(info != null && StringUtils.isNotBlank(info.getInfoId())){
			strTitle = info.getTitle();
			infoService.delete(info.getInfoId());
		}
		return "list";
	}
	
	
	//批量删除
	public String deleteSelect(){
		if(selectedRow != null){
			for(String id:selectedRow){
				infoService.delete(id);
			}
		}
		return "list";
	}
	
	//动态修改状态值
	public void changeState(){
		if(info != null && StringUtils.isNotBlank(info.getInfoId())){
			try {
				Info tem = infoService.findObjectById(info.getInfoId());
				tem.setState(info.getState());
				infoService.update(tem);

				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("更新成功".getBytes("utf-8"));
				
				outputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String getStrTitle() {
		return strTitle;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	
}
