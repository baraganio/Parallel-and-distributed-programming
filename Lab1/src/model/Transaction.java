package model;

import java.util.HashMap;
import java.util.Set;

public class Transaction extends Thread{
	private String threadName;
	private Inventory inventory;
	private HashMap<Product,Integer> operations;
	private double totalPrice;
	
	public Transaction(Inventory inventory, String threadName) {
		this.operations=new HashMap<Product,Integer>();
		this.inventory=inventory;
		this.threadName=threadName;
		
	}
	
	public void addOperations(Product product,int quantity) {
		operations.put(product, quantity);
	}

	@Override
	public void run() {
		Set<Product> set = operations.keySet();
		for(Product pro : set) {
			remove(pro, operations.get(pro));
		}
		System.err.println("Thread: "+threadName + " spent " + getTotalPrice() + "€");
	}	
	
	private void remove(Product product, int quantity) {
		product._mutex.lock();
		if(this.inventory.remove(product, quantity)) {
			totalPrice+=product.getPrice()*quantity;
			System.out.println("Thread: " + threadName + " bought: " +quantity + " units of "+ product.getCode());
		}
		product._mutex.unlock();
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public HashMap<Product, Integer> getOperations() {
		return this.operations;
	}
	
	public void debugRemove(Inventory inve) {
		Set<Product> set = operations.keySet();
		for(Product pro : set) {
			inve.remove(pro, operations.get(pro));
		}
	}
}
