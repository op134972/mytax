package cn.ustb.nsfw.info.dao;

import java.util.List;

import cn.ustb.core.dao.BaseDao;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.info.entity.Info;

public interface InfoDao extends BaseDao<Info> {

	public List<Info> findLatestObjects(int i);



}
