package cn.ustb.core.dao.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustb.core.dao.BaseDao;
import cn.ustb.core.utils.PageResult;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.info.entity.Info;

public class BaseDaoImp<T> extends HibernateDaoSupport implements BaseDao<T> {

	Class<T> clazz;
	
	
	public BaseDaoImp() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
		
	}
	//自定义getcurrensession方法
	public Session getCurrentSession(){
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}

	@Override
	public void save(T t) {
		getCurrentSession().save(t);
	}

	@Override
	public void update(T t) {
		getCurrentSession().clear();
		getCurrentSession().update(t);
	}

	@Override
	public List<T> findObject() {
		//面向对象查询，session——>createQUery("from User")...
		Query query = getSessionFactory().openSession().createQuery("from "+clazz.getSimpleName());
		List<T> list = query.list();
		return list;
	}

	@Override
	public void delete(Serializable id) {
		getCurrentSession().delete(findById(id));
	}

	@Override
	public T findById(Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}
	@Override
	public List<T> findObject(String sql, List<Object> parameters) {
		Query query = getCurrentSession().createQuery(sql);
		if(parameters != null){
			for(int i = 0;i<parameters.size();i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}
	@Override
	public List<T> findObject(QueryHelper query) {
		String sql = query.getSql();
		List<Object> params = query.getParams();
		Query q = getCurrentSession().createQuery(sql);
		if(params != null){
			for(int i = 0;i<params.size();i++){
				q.setParameter(i, params.get(i));
			}
		}
		return q.list();
	}
	
	/**
	 * 利用QueryHelper和PageResult查询返回列表
	 */
	@Override
	public List<T> findObjects(QueryHelper query, PageResult pageResult) {
		String sql = query.getSql();
		List<Object> params = query.getParams();
		Query q = getCurrentSession().createQuery(sql);
		if(params != null){
			for(int i = 0;i<params.size();i++){
				q.setParameter(i, params.get(i));
			}
		}
		/**
		 * 分页查询算法：
		 * 起始页：（currPage-1）*pageSize
		 * 末页:(currPage-1)*pageSize+pageSize
		 */
		if(pageResult!=null){
			q.setFirstResult((int) ((pageResult.getCurrPage()-1)*pageResult.getPageSize()));
			q.setMaxResults(pageResult.getPageSize());
		}
		return q.list();
	}
	@Override
	public long findObjectsCount(String countSql,QueryHelper query) {
		List<Object> params = query.getParams();
		Query q = getCurrentSession().createQuery(countSql);
		if(params != null){
			for(int i = 0;i<params.size();i++){
				q.setParameter(i, params.get(i));
			}
		}
		return (Long) q.uniqueResult();//uniqueResult   HQL查询总条数
	}
	@Override
	public PageResult returnPageResult(QueryHelper query, long pageNo,int pageSize) {
		if(pageSize<=0)pageSize = PageResult.INIT_PAGESIZE;//默认pageSize
		if(pageNo<=0)pageNo = 1l;//若没穿值或者传值错误，默认为1
		
		//分页数据
		PageResult pageResult = new PageResult();
			/*
			 * 新建的pageResult 默认第1页，pageSize默认为3，最终的目标是生成和pageResult对应的sql语句。
			 * 新建findObjects（QUeryHelper,PageResul）
			 * 方法，传入封装了分页数据的pageResult对象生成对应的语句并且返回list
			 */
		pageResult.setCurrPage(pageNo);
		pageResult.setPageSize(pageSize);
		pageResult.setTotalCount(findObjectsCount(query.getCountSql(),query));//获取总记录数，根据查询条件获取。
		//queryHelper中新定义返回查询记录条数的方法
		pageResult.setPageCount();//获取到总记录数，根据pageSize和总记录数得到总页数
		
		//设置pageResult中的list
		pageResult.setItems(findObjects(query, pageResult));
		return pageResult;
	}
}
