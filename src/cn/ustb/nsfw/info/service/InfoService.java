package cn.ustb.nsfw.info.service;

import java.util.List;

import cn.ustb.core.service.BaseService;
import cn.ustb.nsfw.info.entity.Info;

public interface InfoService extends BaseService<Info> {

	public List<Info> findLatestObjects(int i);


}
