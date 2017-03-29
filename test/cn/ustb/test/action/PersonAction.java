package cn.ustb.test.action;

import cn.ustb.test.service.PersonService;

import com.opensymphony.xwork2.ActionSupport;

public class PersonAction extends ActionSupport{
	PersonService personService;

	public PersonService getPersonService() {
		return personService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	public String execute(){
		personService.say();
		return SUCCESS;
	}
}
