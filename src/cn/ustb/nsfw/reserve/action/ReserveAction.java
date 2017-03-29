package cn.ustb.nsfw.reserve.action;

import java.net.URLDecoder;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import cn.ustb.core.action.BaseAction;
import cn.ustb.core.exception.ActionException;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.reserve.entity.Reserve;
import cn.ustb.nsfw.reserve.service.ReserveService;

public class ReserveAction extends BaseAction {
	@Resource
	private ReserveService reserveService;
	
	
	private Reserve reserve;
	
	private String strTitle;//保存查询语句
	
	//列表
	public String listUI() throws ActionException{
		QueryHelper query = new QueryHelper(Reserve.class, "r");
		try {
			if(reserve != null){
				if(StringUtils.isNotBlank(reserve.getReserveNo())){
					//存在查询条件
					reserve.setReserveNo(URLDecoder.decode(reserve.getReserveNo(), "utf-8"));
						query.addCondition("title like ?", "%"+reserve.getReserveNo()+"%");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "listUI";
	}
	
	
	
	public String addUI(){
		//加载角色列表
		return "addUI";
	}
	/* 
	 * 删除和编辑和添加操作时候的条件回显问题：进行的redirectAction,页面中的title条件清空重置。在struts配置params中保存title数据解决删除操作出现的问题。
	 * 新的问题，在编辑和添加时，新添加的标题替代了搜索条件，是因为title重名，需重新定义一个变量接收搜索条件，乱码问题在struts.xml中配置encode=true
	 * 并且在action中解码。
	 * 添加后出现搜索的解决办法：在add方法完成save以后清空reserve的信息
	 * 编辑以后搜索条件清空的解决办法：跳转到编辑页面之前，传递条件给strTitle。编辑页面用隐藏域保存，跳转到list再取回来。
	 * 需配置<param name="reserve.title">${strTitle}</param>...
	 * */
	//保存新增
	public String add(){
		//保存用户
		if(reserve != null){
			reserveService.save(reserve);
		}
		reserve = null;//清空reserve中的信息，防止添加以后出现搜索
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		return "editUI";
	}
	//保存编辑
	public String edit(){
		try {
			if(reserve != null){
				reserveService.update(reserve);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
		
	//根据id删除
	public String delete(){
		return "list";
	}
	
	
	//批量删除
	public String deleteSelect(){
		if(selectedRow != null){
			for(String id:selectedRow){
				reserveService.delete(id);
			}
		}
		return "list";
	}
	
	public Reserve getReserve() {
		return reserve;
	}
	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}
	public String getStrTitle() {
		return strTitle;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	
	
	
	
	
}
