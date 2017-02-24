package test;

public class domeThread  extends Thread{
	private  SequenceNumber  creator;
	public  domeThread(SequenceNumber creator){
		this.creator=creator;
	}
	@Override
	public void run() {
		 
	       for (int i = 0; i < 3; i++) 
	        {
	            System.out.println(Thread.currentThread().getName()+ "print num:"+creator.getNextNum());            
	        }  
	}
	
 
}


