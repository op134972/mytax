package cn.ustb.test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ustb.test.entity.Person;
import cn.ustb.test.service.PersonService;

public class TestMerge {

	private ClassPathXmlApplicationContext context;
	
	
	@Before
	public void loadContext(){
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	
	
	/**
	 * 测试hibernate配置是否起效，要得到sessionFactory
	 */
	@Test
	public void testHibernate() {
		Person a = new Person();
		a.setName("用户1");

		SessionFactory sf = (SessionFactory) context.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(a);
		tx.commit();
		session.close();
	}
	
	@Test
	public void testMerge(){
		Person a = new Person();
		a.setName("mike");
		
		PersonService p = (PersonService) context.getBean("personService");
		p.save(a);
	}
	
	@Test
	public void testTransactionReadOnly(){
		Person a = new Person();
		a.setName("mike");
		PersonService p = (PersonService) context.getBean("personService");
		Person person = p.findPersonById("5e9cbcbe58f7e7360158f7e737a60000");
		System.out.println(person.getName());
	}
	
	@Test
	public void testTransactionRollback(){
		Person a = new Person();
		a.setName("roobackTest");
		PersonService p = (PersonService) context.getBean("personService");
		p.save(a);
	}
	
	@Test
	public void testSpring(){
		PersonService p = (PersonService) context.getBean("personService");
		p.say();
		
	}

}
