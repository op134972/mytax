package cn.ustb.test.entity;

/**
 * TLeader entity. @author MyEclipse Persistence Tools
 */

public class TLeader implements java.io.Serializable {

	// Fields

	private String empId;
	private TEmployee TEmployee;
	private String deptId;
	private String name;
	private String persition;

	// Constructors

	/** default constructor */
	public TLeader() {
	}

	/** minimal constructor */
	public TLeader(TEmployee TEmployee, String name) {
		this.TEmployee = TEmployee;
		this.name = name;
	}

	/** full constructor */
	public TLeader(TEmployee TEmployee, String deptId, String name,
			String persition) {
		this.TEmployee = TEmployee;
		this.deptId = deptId;
		this.name = name;
		this.persition = persition;
	}

	// Property accessors

	public String getEmpId() {
		return this.empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public TEmployee getTEmployee() {
		return this.TEmployee;
	}

	public void setTEmployee(TEmployee TEmployee) {
		this.TEmployee = TEmployee;
	}

	public String getDeptId() {
		return this.deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersition() {
		return this.persition;
	}

	public void setPersition(String persition) {
		this.persition = persition;
	}

}