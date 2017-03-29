package cn.ustb.nsfw.reserve.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ustb.core.service.impl.BaseServiceImpl;
import cn.ustb.nsfw.reserve.dao.ReserveDao;
import cn.ustb.nsfw.reserve.entity.Reserve;
import cn.ustb.nsfw.reserve.service.ReserveService;

@Service("reserveService")
public class ReserveServiceImpl extends BaseServiceImpl<Reserve> implements
		ReserveService {
	private ReserveDao reserveDao;
	@Resource
	public void setReserveDao(ReserveDao reserveDao) {
		super.setBaseDao(reserveDao);
		this.reserveDao = reserveDao;
	}
	public ReserveDao getReserveDao() {
		return reserveDao;
	}
	
}
