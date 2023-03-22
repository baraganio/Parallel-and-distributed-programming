package threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerBuffer {
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	private Queue<Integer> queue = new LinkedList<>();
	private static final int CAPACITY = 1;
	
	public void put(int value) {
		lock.lock();
		try {
			while(queue.size() == CAPACITY) {
				System.out.println("PRODUCER - Queue is full, waiting");
				cond.await();
			}
			queue.add(value);
			System.out.println("PRODUCER - " + value + " was added into the queue");
			cond.signal();
		} catch (InterruptedException e){
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public int get() {
		lock.lock();
		Integer value = null;
		try {
			while(queue.size() == 0) {
				System.out.println("CONSUMER - Queue is empty, waiting");
				cond.await();
			}
			value = queue.poll();
			if(value!=null) {
				System.out.println("CONSUMER - " + value + " was removed from the queue");
				cond.signal();
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return value;
	}
}
