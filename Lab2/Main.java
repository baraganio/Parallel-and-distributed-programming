import java.util.Random;

import domain.Vector;
import threads.Consumer;
import threads.Producer;
import threads.ProducerConsumerBuffer;

public class Main {
	private static final int DIMENSIONS = 10;
	private static Vector vec1 = new Vector();
	private static Vector vec2  = new Vector();
	private static ProducerConsumerBuffer buffer = new ProducerConsumerBuffer();
	private static Producer producer;
	private static Consumer consumer;

	public static void main(String[] args) {

		for(int i=0;i<DIMENSIONS;i++) {
			vec1.add(new Random().nextInt(10));
			vec2.add(new Random().nextInt(10));
		}
		
		
		producer = new Producer(buffer,vec1,vec2);
		consumer = new Consumer(buffer,vec1.getVector().size());
		
		System.out.println("Vector 1:" + vec1.toString());
		System.out.println("Vector 2:" + vec2.toString());
		
		producer.start();
		consumer.start();
		
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("VERIFYING");
		if(verify()) {
			System.err.println("SUCCESFULL");
		}else {
			System.err.println("FAILED");
		}
		
	}
	
	private static boolean verify() {
		int result = 0;
		
		for(int i=0; i<DIMENSIONS;i++) {
			result += vec1.getPosition(i)*vec2.getPosition(i);
		}
		if(result==consumer.getResult()) {
			return true;
		}
		return false;
	}

}