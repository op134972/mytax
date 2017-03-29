package cn.ustb.nsfw.info.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import cn.ustb.core.dao.imp.BaseDaoImp;
import cn.ustb.nsfw.info.dao.InfoDao;
import cn.ustb.nsfw.info.entity.Info;

public class InfoDaoImpl extends BaseDaoImp<Info> implements InfoDao {

	@Override
	public List<Info> findLatestObjects(int i) {
		//查找i条最新，且状态为有效的
		Session session = getCurrentSession();
		Query query = session.createQuery("FROM Info WHERE state = ? ORDER BY createTime DESC");
		query.setParameter(0, "1");//发布的才会显示
		query.setFirstResult(0);
		query.setMaxResults(i);
		return query.list();
	}
}
