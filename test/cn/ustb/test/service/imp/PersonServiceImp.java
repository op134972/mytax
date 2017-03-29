package cn.ustb.test.service.imp;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ustb.test.dao.PersonDao;
import cn.ustb.test.entity.Person;
import cn.ustb.test.service.PersonService;

@Service("personService")
public class PersonServiceImp implements PersonService {
	
	@Resource(name="personDao")
	private PersonDao personDao;//从容器中获取，通过xml已经注入了
	
	
	public PersonDao getPersonDao() {
		return personDao;
	}
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	@Override
	public void say() {
		System.out.println("say something...");
	}

	@Override
	public void save(Person person) {
		personDao.save(person);
	}

	@Override
	public Person findPersonById(Serializable id) {
		return personDao.findById(id);
	}

}
