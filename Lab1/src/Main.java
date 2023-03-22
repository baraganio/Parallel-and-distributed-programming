

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import model.Inventory;
import model.Product;
import model.Transaction;

public class Main {
	private static final int NUMBER_OF_THREADS = 5;
	private static Inventory inventory;
	private static Inventory initialInventory;
	private static List<Product> products;
	private static List<Transaction> transactions;
	
	public static void main(String[] args) {
		inventory = new Inventory();
		initialInventory = new Inventory();
		products = new ArrayList<>();
		transactions = new ArrayList<>();
		
		WriteInFile();
		ReadFromFile();
		
		float start = System.nanoTime() / 1000000;
		
		for (int i = 0; i<NUMBER_OF_THREADS; i++) {
			Transaction t = new Transaction(inventory, "t" + i);
			
			int numProducts = new Random().nextInt(1,15);
			for(int j=0;j<numProducts;j++){
				Product pro = products.get(new Random().nextInt(products.size()));
				int quan = new Random().nextInt(1,5);
				t.addOperations(pro,quan);
			}
			
			transactions.add(t);
		}
		
		for(Transaction tran : transactions) {
			tran.start();
		}
		for(Transaction tran : transactions) {
			try {
				tran.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		float end = System.nanoTime() / 1000000;
		System.out.println("\n End work: " + (end-start) / 1000 + " seconds");
		verify();
	}
	
	private static void verify() {
		boolean fail=false;
		
		double transactionsMoneySpent = 0;
		for(Transaction tran:transactions) {
			transactionsMoneySpent+=tran.getTotalPrice();
			tran.debugRemove(initialInventory);
		}
		
		if(transactionsMoneySpent == inventory.getMoneyEarned()) {
			Set<Product> initialSet = initialInventory.getStock().keySet();
			
						
			for(Product pro : initialSet) {
				if(initialInventory.getStock().get(pro)!=inventory.getStock().get(pro)) {
					fail=true;
				}
			}
		}else {
			fail=true;
		}
		if(fail) {
			System.err.println("VERIFICATION FAILED");
		}else {
			System.err.println("VERIFICATION SUCCESFULL");
		}
	}
	
	private static void WriteInFile() {
        int i = 0;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("files/product.txt"));
            int numProducts = 1000;
            while ( i < numProducts ) {
                Random r = new Random();
                Integer quantity = r.nextInt(1,20);
                String s = new RandomString3().generateRandomString() +  ' ' + r.nextDouble() + ' ' +  quantity + '\n';
                writer.write(s);
                i += 1;
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void ReadFromFile() {
    	String line;
    	String[] articleData=null;
    	try {
    		@SuppressWarnings("resource")
			BufferedReader file = new BufferedReader(new FileReader("files/product.txt"));
    		while(file.ready()) {
    			line=file.readLine();
    			articleData = line.split(" ");
    			Product p = new Product(articleData[0], Float.parseFloat(articleData[1]));
                products.add(p);
                inventory.add(p, Integer.parseInt(articleData[2]));
                initialInventory.add(p, Integer.parseInt(articleData[2]));
    		}
    	}catch (FileNotFoundException fnfe) {
  	      System.out.println("File has not been found");
  	    }
  	    catch (IOException ioe) {
  	      new RuntimeException("Input/Output Error");
  	    } 
    }

}

class RandomString3 {
    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;

    String generateRandomString(){
        StringBuilder randStr = new StringBuilder();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
    private int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
}
