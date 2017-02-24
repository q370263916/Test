package avtiveMq;

import avtiveMq.ProderTest.exThread;

public class ConsumerTest {
	
	public static void main(String[] args) {
		CustomImpl cust=new CustomImpl();
		cust.init();
		cust.getMessage("leo-30");
		for (int i = 0; i <10; i++) {
			  new exThread(cust).start();
		   }
	}
	
	public static class exThread extends Thread{
		private CustomImpl c;
		public exThread(CustomImpl c){
			this.c=c;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			c.getMessage("leo-30");
		}
	}

}
