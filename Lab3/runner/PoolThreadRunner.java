package runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.Matrix;
import utils.Utils;

public class PoolThreadRunner {
	
	public static void run(Matrix a, Matrix b, Matrix c, int numThreads, String type){
		
		// Creates a threadpool with the given number of threads
        ExecutorService service = Executors.newFixedThreadPool(numThreads);
        if(type.equals("Row")) {
        	for (int i=0;i<numThreads;i++)
        		// Submit a runnable task (Like threadsList.add(threadName))
                service.submit(Utils.initRowThread(i, a, b, c, numThreads));
        }else if(type.equals("Column")) {
        	for (int i=0;i<numThreads;i++)
        		// Submit a runnable task (Like threadsList.add(threadName))
                service.submit(Utils.initColThread(i, a, b, c, numThreads));
        }
        // Executes the tasks previously submitted (Like forEach thread -> thread.start())
        service.shutdown();
        try {
        	// Wait to all threads to have finished (Like forEach thread -> thread.join())
        	// Return true if everything finishes before the specified time
            if (!service.awaitTermination(300, TimeUnit.SECONDS)) {
            	// Kill every thread and finished instantly 
                service.shutdownNow();
            }
        } catch (InterruptedException ex) {
            service.shutdownNow();
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}