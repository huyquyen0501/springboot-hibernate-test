package com.example.demo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.HelloWorld;
import com.example.demo.exception.MoneyTransactionalErrorExceptio;

@Repository
@Transactional
public class HelloWorldDAO {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session = sessionFactory.getCurrentSession();

	// khoi tao class
	public HelloWorldDAO() {

	}

	public HelloWorld findById(int Id) {

		return session.get(HelloWorld.class, Id);
	}

	public List<HelloWorld> getAll() {
		String sql = "select from customer";
		return session.createQuery(sql, HelloWorld.class).getResultList();
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void addMoney(int id, int money) throws MoneyTransactionalErrorExceptio {
		HelloWorld helloWorld = this.findById(id);
		if (helloWorld == null) {
			throw new MoneyTransactionalErrorExceptio("Not found" + id);
		} else if (helloWorld.getMoney() < money) {
			throw new MoneyTransactionalErrorExceptio("Ngheo con doi chuyen tien");
		} else {
			helloWorld.setMoney(helloWorld.getMoney()-money);
		}

	}
	 @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = MoneyTransactionalErrorExceptio.class)
	    public void sendMoney(int fromAccountId, int toAccountId, int amount) throws MoneyTransactionalErrorExceptio {
	 
	        addMoney(toAccountId, amount);
	        addMoney(fromAccountId, -amount);
	    }
	
}
