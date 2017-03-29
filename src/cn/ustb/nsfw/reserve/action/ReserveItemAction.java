package cn.ustb.nsfw.reserve.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import cn.ustb.core.action.BaseAction;
import cn.ustb.core.exception.ActionException;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.reserve.entity.ReserveItem;
import cn.ustb.nsfw.reserve.service.ReserveItemService;
import cn.ustb.nsfw.reserve.service.ReserveService;
import cn.ustb.nsfw.user.entity.User;
import cn.ustb.nsfw.user.service.UserService;

public class ReserveItemAction extends BaseAction {
	@Resource
	private ReserveService reserveService;
	@Resource
	private UserService userService;
	@Resource
	private ReserveItemService reserveItemService;
	private ReserveItem item;
	
	private List<ReserveItem> itemList;
	
	private String strTitle;//保存查询语句
	
	//列表
	public String listUI() throws ActionException{
		QueryHelper query = new QueryHelper(ReserveItem.class, "r");
		if(item!=null){
			if(StringUtils.isNotBlank(item.getItemNo())){
				query.addCondition(" itemNo = ?", item.getItemNo());
			}
			if(StringUtils.isNotBlank(item.getItemDept())){
				query.addCondition(" itemDept = ?", item.getItemDept());
			}
			if(StringUtils.isNotBlank(item.getItemName())){
				query.addCondition(" itemName like ?", item.getItemName());
			}
		}
		query.addOrderByProperty(" itemNo",ReserveItem.ITEM_SORT_ASC);
		pageResult = reserveItemService.returnPageResult(query,pageNo,pageSize);
		return "listUI";
	}
	
	
	
	public String addUI(){
		return "addUI";
	}
	//保存新增
	public String add(){
		//保存用户
		if(item != null){
			reserveItemService.save(item);
		}
		item = null;//清空reserveItem中的信息，防止添加以后出现搜索
		return "list";
	}
	
	public void getEmp(){
		try {
			String dept = ServletActionContext.getRequest().getParameter("itemDept");
			dept = new String(dept.getBytes("iso-8859-1"),"UTF-8");
			//根据部门获取员工
			QueryHelper query = new QueryHelper(User.class, "u");
			query.addCondition(" dept = ?", dept);
			JSONObject jo = new JSONObject();
			
			jo.put("empList",userService.findObjects(query));
			
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(jo.toString().getBytes("UTF-8"));
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* 
	 * 删除和编辑和添加操作时候的条件回显问题：进行的redirectAction,页面中的title条件清空重置。在struts配置params中保存title数据解决删除操作出现的问题。
	 * 新的问题，在编辑和添加时，新添加的标题替代了搜索条件，是因为title重名，需重新定义一个变量接收搜索条件，乱码问题在struts.xml中配置encode=true
	 * 并且在action中解码。
	 * 添加后出现搜索的解决办法：在add方法完成save以后清空reserveItem的信息
	 * 编辑以后搜索条件清空的解决办法：跳转到编辑页面之前，传递条件给strTitle。编辑页面用隐藏域保存，跳转到list再取回来。
	 * 需配置<param name="reserveItem.title">${strTitle}</param>...
	 * */
	//跳转到编辑页面
	public String editUI(){
		try {
			if(item != null&&StringUtils.isNotBlank(item.getItemId())){
				item = reserveItemService.findObjectById(item.getItemId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		try {
			if(item != null){
				reserveItemService.update(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
		
	//根据id删除
	public String delete(){
		try {
			if(item != null&&StringUtils.isNotBlank(item.getItemId())){
				reserveItemService.delete(item.getItemId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	
	//批量删除
	public String deleteSelect(){
		if(selectedRow != null){
			for(String id:selectedRow){
				reserveItemService.delete(id);
			}
		}
		return "list";
	}
	
	public String getStrTitle() {
		return strTitle;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	public ReserveItem getItem() {
		return item;
	}
	public void setItem(ReserveItem item) {
		this.item = item;
	}
	public List<ReserveItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<ReserveItem> itemList) {
		this.itemList = itemList;
	}
	
}
