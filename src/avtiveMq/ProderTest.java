package avtiveMq;


import sun.applet.Main;
import test.Sample.mainThread;

public class ProderTest {
	
	public static void main(String[] args) {
		 ProderImpl p=new ProderImpl();
		  p.init();
		  p.sendMessage("leo-30");
		  for (int i = 0; i <10; i++) {
			  new exThread(p).start();
		   }
		  
	}
	
	public static class exThread extends Thread{
		private ProderImpl p;
		public exThread(ProderImpl p){
			this.p=p;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			p.sendMessage("leo-30");
		}
	}
}
