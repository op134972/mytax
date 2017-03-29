package cn.ustb.nsfw.complain.dao;

import java.util.Date;
import java.util.List;

import cn.ustb.core.dao.BaseDao;
import cn.ustb.nsfw.complain.entity.Complain;

public interface ComplainDao extends BaseDao<Complain> {

	void updateCompStateBeforeCompTime(String newState,
			String oldState, Date time);

	List<Object[]> getAnnualStatisticData(int year);


}
