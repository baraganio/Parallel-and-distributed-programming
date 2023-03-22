package model;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
	private HashMap<Product,Integer> stock;
	private double moneyEarned;
	public Lock _mutex = new ReentrantLock();
	
	public Inventory() {
		this.stock=new HashMap<>();
	}
	
	public void add(Product product, int quantity) {
		if(this.stock.containsKey(product)) {
			this.stock.replace(product, this.stock.get(product) + quantity);
		}else {
			this.stock.put(product, quantity);
		}
	}
	
	public boolean remove(Product product, int quantity) {
		
		if(this.stock.containsKey(product)) {
			if(this.stock.get(product)>=quantity) {
				this.stock.replace(product, this.stock.get(product) - quantity);
				_mutex.lock();
				moneyEarned+=product.getPrice()*quantity;
				_mutex.unlock();
				return true;
			}
		}
		return false;
	}
	
	public int getQuantity(Product product) {
		return this.stock.get(product);
	}
	
	public HashMap<Product, Integer> getStock() {
		return this.stock;
	}
	
	public double getMoneyEarned() {
		return this.moneyEarned;
	}
}
