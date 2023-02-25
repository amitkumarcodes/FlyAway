package com.simplilearn.demo;

import java.util.Date;

public class FlightList {
	private int id;
	private String from;
	private String to;
	private Date depTime;
	private Date arrTime;
	private int price;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Date getDepTime() {
		return depTime;
	}
	public void setDepTime(Date depTime) {
		this.depTime = depTime;
	}
	public Date getArrTime() {
		return arrTime;
	}
	public void setArrTime(Date arrTime) {
		this.arrTime = arrTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
