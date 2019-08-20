package com.example.demo.form;

public class SendMoneyForm {
	private int IDSend;
	private int IDReceive;
	private int amount;
	public int getIDSend() {
		return IDSend;
	}
	public void setIDSend(int iDSend) {
		IDSend = iDSend;
	}
	public int getIDReceive() {
		return IDReceive;
	}
	public void setIDReceive(int iDReceive) {
		IDReceive = iDReceive;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public SendMoneyForm(int iDSend, int iDReceive, int amount) {
		super();
		IDSend = iDSend;
		IDReceive = iDReceive;
		this.amount = amount;
	}
	
}
