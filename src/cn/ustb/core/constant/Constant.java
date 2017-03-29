package cn.ustb.core.constant;

import java.util.HashMap;
import java.util.Map;

public class Constant {
	
	/*-----------------------用户登录常量----------------------------*/
	public static String USER = "SYS_USER";
	
	/*-------------------------系统权限集合--------------------------*/
	
	public static String PRIVATE_XZGL = "xzgl";
	public static String PRIVATE_HQFW = "hqfw";
	public static String PRIVATE_ZXXX = "zxxx";
	public static String PRIVATE_NSFW = "nsfw";
	public static String PRIVATE_SPACE = "space";
	
	public static Map<String,String> PRIVATE_MAP;
	static{
		PRIVATE_MAP = new HashMap<String, String>();
		PRIVATE_MAP.put(PRIVATE_XZGL, "行政管理");
		PRIVATE_MAP.put(PRIVATE_HQFW, "后勤服务");
		PRIVATE_MAP.put(PRIVATE_ZXXX, "在线学习");
		PRIVATE_MAP.put(PRIVATE_NSFW, "纳税服务");
		PRIVATE_MAP.put(PRIVATE_SPACE, "我的空间");
		
	}
	
	
}
