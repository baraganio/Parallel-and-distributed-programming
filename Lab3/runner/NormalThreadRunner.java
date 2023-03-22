package runner;

import java.util.ArrayList;
import java.util.List;

import model.Matrix;
import utils.Utils;

public final class NormalThreadRunner {
	
	public static void run(Matrix a, Matrix b, Matrix result, int numThreads, String type) {
		
		List<Thread> threadsList = new ArrayList<>();
		
		// Add threads to the threads' list
		if(type.equals("Row")) {
			for(int i=0;i<numThreads;i++) {
				threadsList.add(Utils.initRowThread(i, a, b, result, numThreads));
			}
		}else if(type.equals("Column")) {
			for(int i=0;i<numThreads;i++) {
				threadsList.add(Utils.initColThread(i, a, b, result, numThreads));
			}
		}
		
		// Start each thread
		for(Thread thread : threadsList) {
			thread.start();
		}
		
		// Wait until all threads have finished
		for(Thread thread : threadsList){
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
