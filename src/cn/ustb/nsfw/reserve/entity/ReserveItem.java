package cn.ustb.nsfw.reserve.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TReserveitem entity. @author MyEclipse Persistence Tools
 */

public class ReserveItem implements java.io.Serializable {

	// Fields

	private String itemId;
	private String itemName;
	private String itemDept;
	private String itemEmp;
	private String itemState;
	private String itemNo;
	private Set TReserves = new HashSet(0);

	// Constructors
	
	public static String ITEM_STATE_VALID = "1";
	public static String ITEM_STATE_INVALID = "0";
	
	public static String ITEM_SORT_ASC = "ASC";
	public static String ITEM_SORT_DESC = "DESC";

	/** default constructor */
	public ReserveItem() {
	}

	/** full constructor */
	public ReserveItem(String itemName, String itemDept, String itemEmp,
			String itemState, String itemNo, Set TReserves) {
		this.itemName = itemName;
		this.itemDept = itemDept;
		this.itemEmp = itemEmp;
		this.itemState = itemState;
		this.itemNo = itemNo;
		this.TReserves = TReserves;
	}

	// Property accessors

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDept() {
		return this.itemDept;
	}

	public void setItemDept(String itemDept) {
		this.itemDept = itemDept;
	}

	public String getItemEmp() {
		return this.itemEmp;
	}

	public void setItemEmp(String itemEmp) {
		this.itemEmp = itemEmp;
	}

	public String getItemState() {
		return this.itemState;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}

	public String getItemNo() {
		return this.itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public Set getTReserves() {
		return this.TReserves;
	}

	public void setTReserves(Set TReserves) {
		this.TReserves = TReserves;
	}

}