package cn.ustb.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import cn.ustb.core.dao.imp.BaseDaoImp;
import cn.ustb.nsfw.role.entity.Role;
import cn.ustb.nsfw.user.dao.UserDao;
import cn.ustb.nsfw.user.entity.User;
import cn.ustb.nsfw.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImp<User> implements UserDao{

	

	//创建sql，创建query，查询list
	//判断账号唯一性，除了本id是否还有别的account账号
	@Override
	public List<User> findUsersByAccountAndId(String account, String id) {
		String sql = "from User where account = ?";
		if(StringUtils.isNotBlank(id)){
			//有id
			sql += "and id != ?";
		}
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter(0, account);
		if(StringUtils.isNotBlank(id)){
			query.setParameter(1,id);
		}
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getCurrentSession().clear();
		getCurrentSession().save(userRole);
		//getCurrentSession().merge(userRole);//弱没有改动，将不会save，userRole也为托管态，由于之前对userRole删除，因此会出现不改动保存数据丢失的问题。
		//getCurrentSession().save(userRole);
	}

	@Override
	public void deleteUserRoleByUserId(Serializable id) {
		Query query = getCurrentSession().createQuery("delete from UserRole where id.userId = ?");
		query.setParameter(0,id);
		query.executeUpdate();
	}

	@Override
	public List<UserRole> findUserRolesByUserId(String id) {
		Query query = getCurrentSession().createQuery("from UserRole where id.userId = ?");
		query.setParameter(0, id);
		return query.list();
	}

	@Override
	public List<User> findUsersByAccountAndPassWord(String account,
			String password) {
		Query query = getCurrentSession().createQuery("from User where account = ? and password = ? and state = ?");//状态值state
		query.setParameter(0, account);
		query.setParameter(1, password);
		query.setParameter(2, User.User_STATE_VALID);
		return query.list();
	}

}
