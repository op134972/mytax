package cn.ustb.core.permission;

import cn.ustb.nsfw.user.entity.User;

public interface PermissionCheck {
	
	
	
	
	public boolean isAccessable(User user,String code);
}
