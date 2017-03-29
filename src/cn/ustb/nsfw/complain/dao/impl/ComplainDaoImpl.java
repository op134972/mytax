package cn.ustb.nsfw.complain.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import cn.ustb.core.dao.imp.BaseDaoImp;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.complain.dao.ComplainDao;
import cn.ustb.nsfw.complain.entity.Complain;

public class ComplainDaoImpl extends BaseDaoImp<Complain> implements
		ComplainDao {

	@Override
	public void updateCompStateBeforeCompTime(String newState,
			String oldState, Date time) {
		String sql = "UPDATE Complain SET state = ? WHERE　state = ? AND compTime < ?";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, newState);
		query.setParameter(1, oldState);
		query.setParameter(2, time);
		query.executeUpdate();
	}

	@Override
	public List<Object[]> getAnnualStatisticData(int year) {
		String sql = "SELECT mon,c FROM t_month LEFT JOIN (SELECT MONTH(compTime) m," +
				"COUNT(compId) c FROM complain WHERE YEAR(compTime) = "+year+
				" GROUP BY MONTH(compTime)) t ON mon = m ORDER BY mon ASC;";
		SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql);
		return sqlQuery.list();
	}


}
