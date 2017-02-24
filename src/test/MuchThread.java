package test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MuchThread {
    public static void main(String[] args) {

    	// TODO Auto-generated method stub
    	System.out.println("-------"+11);
    	   ExecutorService pool = Executors.newCachedThreadPool();
    	   for (int j = 0; j < 10; j++) {
    		   final int index = j; 
    	    pool.equals(new Runnable() {
    		@Override
    		public void run() {
    			// TODO Auto-generated method stub
    			System.out.println("-------"+index);
    		}
    	});
    		}

	}
}
