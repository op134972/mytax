package cn.ustb.nsfw.reserve.entity;

/**
 * TReservereply entity. @author MyEclipse Persistence Tools
 */

public class Reservereply implements java.io.Serializable {

	// Fields

	private String replyId;
	private Reserve TReserve;
	private String replyEmp;
	private String replyDept;
	private String replyContent;

	// Constructors

	/** default constructor */
	public Reservereply() {
	}

	/** minimal constructor */
	public Reservereply(Reserve TReserve) {
		this.TReserve = TReserve;
	}

	/** full constructor */
	public Reservereply(Reserve TReserve, String replyEmp, String replyDept,
			String replyContent) {
		this.TReserve = TReserve;
		this.replyEmp = replyEmp;
		this.replyDept = replyDept;
		this.replyContent = replyContent;
	}

	// Property accessors

	public String getReplyId() {
		return this.replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public Reserve getTReserve() {
		return this.TReserve;
	}

	public void setTReserve(Reserve TReserve) {
		this.TReserve = TReserve;
	}

	public String getReplyEmp() {
		return this.replyEmp;
	}

	public void setReplyEmp(String replyEmp) {
		this.replyEmp = replyEmp;
	}

	public String getReplyDept() {
		return this.replyDept;
	}

	public void setReplyDept(String replyDept) {
		this.replyDept = replyDept;
	}

	public String getReplyContent() {
		return this.replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

}