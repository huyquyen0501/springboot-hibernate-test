package com.example.demo.model;

public class HelloWorldModel {
	private int id;
	private String name;
	private int money;
	public HelloWorldModel(int id, String name, int money) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
	}
	public HelloWorldModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
}
