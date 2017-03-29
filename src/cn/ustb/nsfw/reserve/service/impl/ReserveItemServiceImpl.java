package cn.ustb.nsfw.reserve.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ustb.core.service.impl.BaseServiceImpl;
import cn.ustb.nsfw.reserve.dao.ReserveItemDao;
import cn.ustb.nsfw.reserve.entity.ReserveItem;
import cn.ustb.nsfw.reserve.service.ReserveItemService;

@Service("reserveItemService")
public class ReserveItemServiceImpl extends BaseServiceImpl<ReserveItem> implements
ReserveItemService {
	
	private ReserveItemDao reserveItemDao;
	@Resource
	public void setReserveItemDao(ReserveItemDao reserveItemDao) {
		super.setBaseDao(reserveItemDao);
		this.reserveItemDao = reserveItemDao;
	}

	public ReserveItemDao getReserveItemDao() {
		return reserveItemDao;
	}


	
}
