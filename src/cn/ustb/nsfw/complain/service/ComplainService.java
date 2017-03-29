package cn.ustb.nsfw.complain.service;

import java.util.List;
import java.util.Map;

import cn.ustb.core.service.BaseService;
import cn.ustb.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain>{
	
	public void doMonthDeal();

	public List<Map<String,Object>> getAnnualStatisticData(int year);

}
