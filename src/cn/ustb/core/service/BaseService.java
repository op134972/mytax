package cn.ustb.core.service;

import java.io.Serializable;
import java.util.List;

import cn.ustb.core.exception.ServiceException;
import cn.ustb.core.utils.PageResult;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.info.entity.Info;

/**
 * 抽取的XXXService的公共方法
 * @param <T>
 */
public interface BaseService<T> {
	//增
	public void save(T entity);
	//根据id删
	public void delete(Serializable id);
	//更新
	public void update(T entity);
	//查询<T>列表
	public List<T> findObjects() throws ServiceException;
	//根据id查询T
	public T findObjectById(Serializable id);
	//根据搜索条件查询列表
	@Deprecated
	public List<T> findObjects(String sql,List<Object> parameters);
	
	public List<T> findObjects(QueryHelper query);
	
	
	//利用分页返列表
	public List<T> findObjects(QueryHelper query, PageResult pageResult);
	
	//返回查询总条数
	public long findObjectsCount(String countSql,QueryHelper qh);
	
	//返回pageresult
	public PageResult returnPageResult(QueryHelper query, long pageNo, int pageSize);


}
