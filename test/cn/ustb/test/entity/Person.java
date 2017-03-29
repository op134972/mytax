package cn.ustb.test.entity;

import java.io.Serializable;

public class Person implements Serializable{
	private String name;
	private String id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	
}
