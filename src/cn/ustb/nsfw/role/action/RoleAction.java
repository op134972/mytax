package cn.ustb.nsfw.role.action;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import cn.ustb.core.action.BaseAction;
import cn.ustb.core.constant.Constant;
import cn.ustb.core.exception.ActionException;
import cn.ustb.core.exception.ServiceException;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.info.entity.Info;
import cn.ustb.nsfw.role.entity.Role;
import cn.ustb.nsfw.role.entity.RolePrivilege;
import cn.ustb.nsfw.role.entity.RolePrivilegeId;
import cn.ustb.nsfw.role.service.RoleService;

public class RoleAction extends BaseAction {
	/**
	 * Action的属性会放到值栈中
	 */

	@Resource
	private RoleService roleService;
	
	private Role role;
	private List<Role> roleList;//维护查询到的用户列表
	private String[] privilegeIds;//维护用户添加编辑时保存的权限集合
	private String strName;
	
	//列表
	public String listUI() throws ActionException{
		QueryHelper query = new QueryHelper(Role.class, "r");
		try {
		
			if(role != null){
				if(StringUtils.isNotBlank(role.getName())){
						role.setName(URLDecoder.decode(role.getName(), "utf-8"));
					query.addCondition("name like ?", "%"+role.getName()+"%");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageResult = roleService.returnPageResult(query,pageNo,pageSize);
		ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVATE_MAP);//加载所有的信息类型map
		return "listUI";
	}
	public String addUI(){
		//加载系统权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVATE_MAP);
		return "addUI";
	}
	
	//保存新增
	public String add(){
		if(privilegeIds!=null){
			HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
			for(String id:privilegeIds){
				set.add(new RolePrivilege(new RolePrivilegeId(id,role)));
			}
			role.setRolePrivileges(set);
		}
		roleService.save(role);//有级联操作
		role = null;
		return "list";
	}
	//跳转到编辑页面
	public String editUI(){
		//加载系统权限集合
		ActionContext.getContext().getContextMap().put("privilegeMap",Constant.PRIVATE_MAP);
		if(role != null &&StringUtils.isNotBlank(role.getRoleId())){
			if(StringUtils.isNotBlank(role.getName())){
				strName = role.getName();//存储搜索条件，下一步跳转至编辑页面，隐藏域保存
			}
			role = roleService.findObjectById(role.getRoleId());
		}
		//处理编辑页面权限的回显问题
		if(role.getRolePrivileges() != null && role.getRolePrivileges().size()>0){
			Set<RolePrivilege> rolePrivileges = role.getRolePrivileges();
			privilegeIds = new String[rolePrivileges.size()];
			int i = 0;
			for (RolePrivilege rp:rolePrivileges) {
				privilegeIds[i++] = rp.getId().getCode();
			}
		}
		return "editUI";
	}
	//保存编辑
	public String edit(){
		if(privilegeIds!=null){
			HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
			for(String id:privilegeIds){
				set.add(new RolePrivilege(new RolePrivilegeId(id,role)));
			}
			role.setRolePrivileges(set);
		}
		roleService.update(role);
		return "list";
	}
		
	//根据id删除
	public String delete(){
		if(role != null && StringUtils.isNotBlank(role.getRoleId())){
			roleService.delete(role.getRoleId());
		}
		return "list";
	}
	
	
	//批量删除
	public String deleteSelect(){
		if(selectedRow != null){
			for(String id:selectedRow){
				roleService.delete(id);
			}
		}
		return "list";
	}
	
	
	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	
}
