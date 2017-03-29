package cn.ustb.core.utils;

import java.util.ArrayList;
import java.util.List;



/**
 * 查询助手
 * @author Wch
 *
 */
public class QueryHelper {
	
	
	private String fromClause = "";
	private String whereClause = "";
	private String orderClause = "";
	
	private String alias;
	
	private List<Object> params;

	//排序常量
	public static String ORDER_QUERY_DESC = "DESC";
	public static String ORDER_QUERY_ASC = "ASC";
	
	/**
	 * 构造方法，确定从哪个表查询
	 * @param clazz
	 * @param alias
	 */
	public QueryHelper(Class clazz,String alias){
		this.fromClause = "FROM "+clazz.getSimpleName()+" "+alias;
		this.alias = alias;
	}
	
	public String getSql() {
		return fromClause+whereClause+orderClause;
	}
	
	//返回查询记录条数的方法，用户设置pagResult中的总记录条数
	public String getCountSql(){
		return "SELECT COUNT(*) "+fromClause+whereClause;
	}
	/**
	 * 条件 限制
	 * @param condition eg:title = ?//已经设置了别名不需添加
	 * @param param eg:%title%
	 */
	public void addCondition(String condition,Object param){
		if(whereClause.length()>0){//非第一个where语句
			whereClause += " AND "+alias+"."+condition;
		}else{//第一个
			whereClause = " WHERE "+alias+"."+condition;
		}
		if(params == null){
			params = new ArrayList<Object>();
		}
		params.add(param);
	}

	/**
	 * 排序语句
	 * @param property eg:createTime
	 * @param mode eg:模式asc，desc
	 */
	public void addOrderByProperty(String property,String mode){
		if(orderClause.length()>0){//非第一个排序
			orderClause += ","+alias+"."+property+" "+mode;
		}else{
			orderClause = " ORDER BY "+alias+"."+property+" "+mode;
		}
	}
	public List<Object> getParams() {
		return params;
	}
	
}
