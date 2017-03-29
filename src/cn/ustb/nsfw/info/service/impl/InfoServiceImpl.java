package cn.ustb.nsfw.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ustb.core.service.impl.BaseServiceImpl;
import cn.ustb.core.utils.QueryHelper;
import cn.ustb.nsfw.info.dao.InfoDao;
import cn.ustb.nsfw.info.entity.Info;
import cn.ustb.nsfw.info.service.InfoService;

@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {
	private InfoDao infoDao;

	
	@Resource
	public void setInfoDao(InfoDao infoDao){//@resource不能少
		super.setBaseDao(infoDao);
		this.infoDao = infoDao;
	}
	public InfoDao getInfoDao() {
		return infoDao;
	}
	@Override
	public List<Info> findObjects(QueryHelper query) {
		return infoDao.findObject(query);
	}
	//返回最新i条数据
	@Override
	public List<Info> findLatestObjects(int i) {
		return infoDao.findLatestObjects(i);
	}
	
}
