package cn.ustb.core.service.impl;

import java.io.Serializable;
import java.util.List;

import cn.ustb.core.dao.BaseDao;
import cn.ustb.core.exception.ServiceException;
import cn.ustb.core.service.BaseService;
import cn.ustb.core.utils.PageResult;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.info.entity.Info;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	//维护一个BaseDao
	private BaseDao<T> baseDao;
	
	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	public BaseServiceImpl(BaseDao<T> baseDao) {
		super();
		this.baseDao = baseDao;
	}

	public BaseServiceImpl() {
		super();
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public List<T> findObjects() throws ServiceException{
		/**
		 * 假设有业务异常
		 */
		try {
			//int i = 1/0;
		} catch (Exception e) {
			//抛出业务层异常。交给上一层即action判断
			throw new ServiceException("业务操作失败!"+e.getMessage());
		}
		return baseDao.findObject();
	}

	@Override
	public T findObjectById(Serializable id) {
		return baseDao.findById(id);
	}

	//根据搜索条件查询列表
	@Deprecated
	public List<T> findObjects(String sql,List<Object> parameters){
		return baseDao.findObject(sql, parameters);
	}
	
	public List<T> findObjects(QueryHelper query){
		return baseDao.findObject(query);
	};
	
	
	//利用分页返列表
		public List<T> findObjects(QueryHelper query, PageResult pageResult){
			return baseDao.findObjects(query,pageResult);
	}
		
	//返回记录总条数
	public long findObjectsCount(String countSql,QueryHelper qh){
		return baseDao.findObjectsCount(countSql,qh);
	}
	//返回pageResult
	public PageResult returnPageResult(QueryHelper query, long pageNo, int pageSize){
		return baseDao.returnPageResult( query,  pageNo,  pageSize);
	};



}
