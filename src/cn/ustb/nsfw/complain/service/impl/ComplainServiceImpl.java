package cn.ustb.nsfw.complain.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ustb.core.service.impl.BaseServiceImpl;
import cn.ustb.nsfw.complain.dao.ComplainDao;
import cn.ustb.nsfw.complain.entity.Complain;
import cn.ustb.nsfw.complain.service.ComplainService;

@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements
		ComplainService {

	private ComplainDao complainDao;

	@Resource
	public void setComplainDao(ComplainDao complainDao) {
		super.setBaseDao(complainDao);
		this.complainDao = complainDao;
	}

	// 每月投诉自动失效
	public void doMonthDeal() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		complainDao.updateCompStateBeforeCompTime(
				Complain.COMPLAIN_STATE_REPLIED, Complain.COMPLAIN_STATE_DONE,
				c.getTime());
	}

	@Override
	public List<Map<String, Object>> getAnnualStatisticData(int year) {
		List<Object[]> list = complainDao.getAnnualStatisticData(year);
		// 组装{["":"","":""][]...}
		// List<Object[Map<String,String>]> compData = new ArrayList<E>();
		List<Map<String, Object>> compData = new ArrayList<Map<String, Object>>();
		// 判断是否是当年
		int currYear = Calendar.getInstance().get(Calendar.YEAR);
		int currMon = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if (currYear == year) {// 是当年
			for (Object[] o : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (Integer.parseInt(o[0]+"") <= currMon) {// 没超过当月的数据
																	// 设置为0
					map.put("label", o[0] + "月");
					if (o[1] == null) {
						map.put("value", "0");
					} else {
						map.put("value", o[1] + "");
					}
					compData.add(map);
				} else {// 超过了当月的数据 设置为null
					map.put("label", o[0] + "月");
					map.put("value", "null");
					compData.add(map);
				}
			}
		} else {// 不是当年 直接加数据 null = 0
			for (Object[] o : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("label",o[0] + "月");
				if (o[1] == null) {
					map.put("value", "0");
				} else {
					map.put("value",o[1]+"");
				}
				compData.add(map);
			}
		}
		return compData;
		//老师的代码
		/*//1、根据年度查询投诉数
		List<Object[]> list = complainDao.getAnnualStatisticData(year);
		//2、解析并返回符合fusionchart格式的json字符串需要的对象
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		if(list != null){
			Map<String, Object> map = null;
			Calendar cal = Calendar.getInstance();
			int curMonth = cal.get(Calendar.MONTH) + 1;//当前月份
			boolean isCurrentYear = (year == cal.get(Calendar.YEAR));//是否当前年度
			int temMonth = 0;
			for(Object[] obj: list){
				map = new HashMap<String, Object>();
				temMonth = Integer.parseInt(obj[0] + "");//月份
				map.put("label", temMonth + "月");
				if(isCurrentYear){//当前年度：将未到的月份的投诉数改为空字符串
					if(temMonth <= curMonth){//已过月份
						map.put("value", obj[1]!=null?obj[1]:0);
					} else {//未到月份，将月份的投诉数改为空字符串
						map.put("value", "");
					}
				} else {//非当前年度；将投诉数出现Null值获取空值的话置为0
					map.put("value", obj[1]!=null?obj[1]:0);
				}
				resultList.add(map);
			}
		}
		return resultList;*/
	}

	public ComplainDao getComplainDao() {
		return complainDao;
	}


}