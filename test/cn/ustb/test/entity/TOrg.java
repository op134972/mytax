package cn.ustb.test.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TOrg entity. @author MyEclipse Persistence Tools
 */

public class TOrg implements java.io.Serializable {

	// Fields

	private String orgId;
	private String name;
	private Set TDepts = new HashSet(0);

	// Constructors

	/** default constructor */
	public TOrg() {
	}

	/** minimal constructor */
	public TOrg(String name) {
		this.name = name;
	}

	/** full constructor */
	public TOrg(String name, Set TDepts) {
		this.name = name;
		this.TDepts = TDepts;
	}

	// Property accessors

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getTDepts() {
		return this.TDepts;
	}

	public void setTDepts(Set TDepts) {
		this.TDepts = TDepts;
	}

}