package cn.ustb.core.dao;


/**
 * 增删改查的抽取
 */
import java.io.Serializable;
import java.util.List;

import cn.ustb.core.utils.PageResult;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.info.entity.Info;

public interface BaseDao<T> {
	//增加
	public void save(T t);
	//更新
	public void update(T t);
	//查询
	public List<T> findObject();
	//根据id删除
	public void delete(Serializable id);
	//根据id查询
	public T findById(Serializable id); 
	
	
	//根据搜索条件查询
	public List<T> findObject(String sql, List<Object> parameters);
	
	public List<T> findObject(QueryHelper query);
	
	//利用分页返列表
	public List<T> findObjects(QueryHelper query, PageResult pageResult);
	
	
	//返回记录总条数
	public long findObjectsCount(String countSql, QueryHelper qh);
	
	
	public PageResult returnPageResult(QueryHelper query, long pageNo,
			int pageSize);


}
