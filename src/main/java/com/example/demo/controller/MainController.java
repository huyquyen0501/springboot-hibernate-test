package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.HelloWorldDAO;
import com.example.demo.entity.HelloWorld;
import com.example.demo.exception.MoneyTransactionalErrorExceptio;
import com.example.demo.form.SendMoneyForm;
import com.example.demo.model.HelloWorldModel;

@RestController
@RequestMapping("vietcombank")
public class MainController {
	
	@Autowired
	HelloWorldDAO helloWorldDAO;
	 
	@GetMapping("/getall")
	public List<HelloWorld> getAll(){
		return helloWorldDAO.getAll();
	}
	@PostMapping("/getall/sendMoney")
	public String sendMoney(Model model, SendMoneyForm sendMoneyForm) {
		System.out.println("Send Money::" + sendMoneyForm.getAmount());
		try {
			helloWorldDAO.sendMoney(sendMoneyForm.getIDSend(),sendMoneyForm.getIDSend(),sendMoneyForm.getAmount());
			
		} catch (MoneyTransactionalErrorExceptio e) {
			// TODO: handle exception
			
		}
		return "Hoan thanh";
	}
}
