package threads;

import domain.Vector;

public class Producer extends Thread{
	
	private Vector vec1;
	private Vector vec2;
	private ProducerConsumerBuffer buffer;
	
	public Producer(ProducerConsumerBuffer buffer, Vector vec1,Vector vec2) {
		this.vec1=vec1;
		this.vec2=vec2;
		this.buffer=buffer;
	}
	
	@Override
	public void run() {
		for (int i=0;i<vec1.getVector().size();i++) {
			System.out.println("PRODUCER - Sending: " + vec1.getPosition(i)+"*"+vec2.getPosition(i)+"="+vec1.getPosition(i)*vec2.getPosition(i));
			buffer.put(vec1.getPosition(i)*vec2.getPosition(i));
		}
	}
}