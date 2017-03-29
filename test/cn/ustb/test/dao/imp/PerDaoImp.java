package cn.ustb.test.dao.imp;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.ustb.test.dao.PersonDao;
import cn.ustb.test.entity.Person;
/**
 * 继承hibernateDaoSupport对数据库操作的封住，需要对dao注入sessionFactory。sessionFactory里面包含Database信息
 * @author Wch
 *
 */
public class PerDaoImp extends HibernateDaoSupport implements PersonDao {
	
	@Override
	public void save(Person person) {
		getHibernateTemplate().save(person);
	}

	@Override
	public void delete(String id) {
		getHibernateTemplate().delete(findById(id));
	}

	@Override//get（Class，id）
	public Person findById(Serializable id) {
		return getHibernateTemplate().get(Person.class, id);
	}

}
