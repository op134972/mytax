package cn.ustb.nsfw.role.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Role implements Serializable {
	private String name;
	private String roleId;
	private String state;
	private Set<RolePrivilege> rolePrivileges;
	
	public Role(String roleId2) {
		this.roleId = roleId2;
	}
	//状态常量
	private static String ROLE_STATE_VALID = "1";
	private static String ROLE_STATE_INVALID = "0";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}
	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Role() {
		super();
	}
	public Role(String name, String roleId, String state,
			Set<RolePrivilege> rolePrivileges) {
		super();
		this.name = name;
		this.roleId = roleId;
		this.state = state;
		this.rolePrivileges = rolePrivileges;
	}
}
