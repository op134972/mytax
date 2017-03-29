package cn.ustb.test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TPrivilege entity. @author MyEclipse Persistence Tools
 */

public class TPrivilege implements java.io.Serializable {

	// Fields

	private String priId;
	private String name;
	private Set RRolePris = new HashSet(0);

	// Constructors

	/** default constructor */
	public TPrivilege() {
	}

	/** minimal constructor */
	public TPrivilege(String name) {
		this.name = name;
	}

	/** full constructor */
	public TPrivilege(String name, Set RRolePris) {
		this.name = name;
		this.RRolePris = RRolePris;
	}

	// Property accessors

	public String getPriId() {
		return this.priId;
	}

	public void setPriId(String priId) {
		this.priId = priId;
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

}