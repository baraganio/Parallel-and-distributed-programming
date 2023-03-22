package threads;

public class Consumer extends Thread{
	public int result = 0;
	private ProducerConsumerBuffer buffer;
	public int length;
	
	public Consumer(ProducerConsumerBuffer buffer, int length) {
		this.buffer=buffer;
		this.length=length;
	}
	
	@Override
	public void run(){
		for(int i=0;i<this.length;i++) {
			result+=buffer.get();
			System.out.println("CONSUMER - Result is now "+result);
		}
		System.err.println("CONSUMER - Final result is: "+result);
	}
	
	public int getResult() {
		return this.result;
	}
	
}
