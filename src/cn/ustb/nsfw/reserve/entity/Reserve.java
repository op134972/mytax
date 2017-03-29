package cn.ustb.nsfw.reserve.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * TReserve entity. @author MyEclipse Persistence Tools
 */

public class Reserve implements java.io.Serializable {

	// Fields

	private String reserveId;
	private ReserveItem TReserveitem;
	private String reserveItem;
	private String reserveName;
	private Timestamp reserveTime;
	private String reserveAddress;
	private String reserveDecl;
	private String reserveNo;
	private Set TReservereplies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Reserve() {
	}

	/** minimal constructor */
	public Reserve(ReserveItem TReserveitem, Timestamp reserveTime) {
		this.TReserveitem = TReserveitem;
		this.reserveTime = reserveTime;
	}

	/** full constructor */
	public Reserve(ReserveItem TReserveitem, String reserveItem,
			String reserveName, Timestamp reserveTime, String reserveAddress,
			String reserveDecl, String reserveNo, Set TReservereplies) {
		this.TReserveitem = TReserveitem;
		this.reserveItem = reserveItem;
		this.reserveName = reserveName;
		this.reserveTime = reserveTime;
		this.reserveAddress = reserveAddress;
		this.reserveDecl = reserveDecl;
		this.reserveNo = reserveNo;
		this.TReservereplies = TReservereplies;
	}

	// Property accessors

	public String getReserveId() {
		return this.reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

	public ReserveItem getTReserveitem() {
		return this.TReserveitem;
	}

	public void setTReserveitem(ReserveItem TReserveitem) {
		this.TReserveitem = TReserveitem;
	}

	public String getReserveItem() {
		return this.reserveItem;
	}

	public void setReserveItem(String reserveItem) {
		this.reserveItem = reserveItem;
	}

	public String getReserveName() {
		return this.reserveName;
	}

	public void setReserveName(String reserveName) {
		this.reserveName = reserveName;
	}

	public Timestamp getReserveTime() {
		return this.reserveTime;
	}

	public void setReserveTime(Timestamp reserveTime) {
		this.reserveTime = reserveTime;
	}

	public String getReserveAddress() {
		return this.reserveAddress;
	}

	public void setReserveAddress(String reserveAddress) {
		this.reserveAddress = reserveAddress;
	}

	public String getReserveDecl() {
		return this.reserveDecl;
	}

	public void setReserveDecl(String reserveDecl) {
		this.reserveDecl = reserveDecl;
	}

	public String getReserveNo() {
		return this.reserveNo;
	}

	public void setReserveNo(String reserveNo) {
		this.reserveNo = reserveNo;
	}

	public Set getTReservereplies() {
		return this.TReservereplies;
	}

	public void setTReservereplies(Set TReservereplies) {
		this.TReservereplies = TReservereplies;
	}

}