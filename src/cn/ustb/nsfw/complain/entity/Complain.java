package cn.ustb.nsfw.complain.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Complain entity. @author MyEclipse Persistence Tools
 */

public class Complain implements java.io.Serializable {

	// Fields

	private String compId;
	private String compName;
	private String compPhone;
	private Timestamp compTime;
	private Boolean isAnony;
	private String toCompDept;
	private String toCompName;
	private String compTitle;
	private String compComtent;
	private String state;
	private String compDept;
	private Set replies = new HashSet(0);
	
	public static String COMPLAIN_STATE_UNDONE ="0";
	public static String COMPLAIN_STATE_DONE ="1";
	public static String COMPLAIN_STATE_REPLIED ="2";
	//受理状态
	public static Map<String, String> COMPLAIN_STATE_MAP = new HashMap<String,String>();
	static {
		COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_UNDONE, "未受理");
		COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_DONE, "已受理");
		COMPLAIN_STATE_MAP.put(COMPLAIN_STATE_REPLIED, "已回复");
	}
	

	// Constructors

	/** default constructor */
	public Complain() {
	}

	/** full constructor */
	public Complain(String compName, String compPhone, Timestamp compTime,
			Boolean isAnony, String toCompDept, String toCompName,
			String compTitle, String compComtent, String state,
			String compDept, Set replies) {
		this.compName = compName;
		this.compPhone = compPhone;
		this.compTime = compTime;
		this.isAnony = isAnony;
		this.toCompDept = toCompDept;
		this.toCompName = toCompName;
		this.compTitle = compTitle;
		this.compComtent = compComtent;
		this.state = state;
		this.compDept = compDept;
		this.replies = replies;
	}

	// Property accessors

	public String getCompId() {
		return this.compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getCompName() {
		return this.compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompPhone() {
		return this.compPhone;
	}

	public void setCompPhone(String compPhone) {
		this.compPhone = compPhone;
	}

	public Timestamp getCompTime() {
		return this.compTime;
	}

	public void setCompTime(Timestamp compTime) {
		this.compTime = compTime;
	}

	public Boolean getIsAnony() {
		return this.isAnony;
	}

	public void setIsAnony(Boolean isAnony) {
		this.isAnony = isAnony;
	}

	public String getToCompDept() {
		return this.toCompDept;
	}

	public void setToCompDept(String toCompDept) {
		this.toCompDept = toCompDept;
	}

	public String getToCompName() {
		return this.toCompName;
	}

	public void setToCompName(String toCompName) {
		this.toCompName = toCompName;
	}

	public String getCompTitle() {
		return this.compTitle;
	}

	public void setCompTitle(String compTitle) {
		this.compTitle = compTitle;
	}

	public String getCompComtent() {
		return this.compComtent;
	}

	public void setCompComtent(String compComtent) {
		this.compComtent = compComtent;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCompDept() {
		return this.compDept;
	}

	public void setCompDept(String compDept) {
		this.compDept = compDept;
	}

	public Set getReplies() {
		return this.replies;
	}

	public void setReplies(Set replies) {
		this.replies = replies;
	}

}