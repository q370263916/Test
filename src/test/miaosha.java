package test;

import java.util.concurrent.CountDownLatch;

import test.Sample.excuThread;


public class miaosha {
	public static CountDownLatch latch=new CountDownLatch(100);
	private static   Long total=0l;
	private static   Integer countThread=0;
	public static  class excuThread extends Thread{
		private Long amount;
		public excuThread(Long amount){
			this.amount=amount;
		}
		@Override
		public void run() {
		// TODO Auto-generated method stub
			 try {	
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if((total+5)<=100){
				// synchronized (total) {
						total+=amount;
						countThread+=1;
				//}
						System.out.println(Thread.currentThread().getName()+"������Ʒ");
			 }
			
		}
		}
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i <1000; i++) {
			Thread t=new Thread(new excuThread(5l));
			t.start();
			latch.countDown();
		}
		
		Thread.currentThread().sleep(3000);
		System.out.println("һ��100 ����Ʒ ��������"+total);
		System.out.println("��������"+countThread);
		
	}

}
