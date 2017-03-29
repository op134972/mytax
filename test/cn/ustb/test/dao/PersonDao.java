package cn.ustb.test.dao;

import java.io.Serializable;

import cn.ustb.test.entity.Person;

public interface PersonDao {
	public void save(Person person);
	public void delete(String id);
	public Person findById(Serializable id);
}
