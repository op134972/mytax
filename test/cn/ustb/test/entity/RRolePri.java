package cn.ustb.test.entity;

/**
 * RRolePri entity. @author MyEclipse Persistence Tools
 */

public class RRolePri implements java.io.Serializable {

	// Fields

	private RRolePriId id;

	// Constructors

	/** default constructor */
	public RRolePri() {
	}

	/** full constructor */
	public RRolePri(RRolePriId id) {
		this.id = id;
	}

	// Property accessors

	public RRolePriId getId() {
		return this.id;
	}

	public void setId(RRolePriId id) {
		this.id = id;
	}

}