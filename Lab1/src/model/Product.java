package model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Product {
	private String code;
	private float price;
	public Lock _mutex = new ReentrantLock();
	
	public Product(String code, float price) {
		setCode(code);
		setPrice(price);
	}
	
	private void setCode(String code) {
		this.code=code;
	}
	
	private void setPrice(float price) {
		this.price=price;
	}
	
	public String getCode() {
		return code;
	}
	
	public float getPrice() {
		return price;
	}
}
