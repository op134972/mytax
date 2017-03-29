package cn.ustb.test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TRole entity. @author MyEclipse Persistence Tools
 */

public class TRole implements java.io.Serializable {

	// Fields

	private String roleId;
	private String name;
	private Set RRolePris = new HashSet(0);
	private Set REmpRoles = new HashSet(0);

	// Constructors

	/** default constructor */
	public TRole() {
	}

	/** minimal constructor */
	public TRole(String name) {
		this.name = name;
	}

	/** full constructor */
	public TRole(String name, Set RRolePris, Set REmpRoles) {
		this.name = name;
		this.RRolePris = RRolePris;
		this.REmpRoles = REmpRoles;
	}

	// Property accessors

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getRRolePris() {
		return this.RRolePris;
	}

	public void setRRolePris(Set RRolePris) {
		this.RRolePris = RRolePris;
	}

	public Set getREmpRoles() {
		return this.REmpRoles;
	}

	public void setREmpRoles(Set REmpRoles) {
		this.REmpRoles = REmpRoles;
	}

}