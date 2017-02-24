package test;

import java.util.concurrent.CountDownLatch;

public class Sample {
	// ������
	public static CountDownLatch latch=new CountDownLatch(100);
	
	/**
	 * �����߳���
	 * 
	 */
	public static class workThread extends Thread{
		private static String name;
		private static int sleepTime;
		
		public workThread(String name,int sleepTime){
			this.name=name;
			this.sleepTime=sleepTime;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(name+"��ʼ");
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			latch.countDown();
			System.out.println(name+"����");
		}
		
	}
	public static class workThread2 extends Thread {
		private static String name;
		private static int sleepTime;
		
		public workThread2(String name,int sleepTime){
			this.name=name;
			this.sleepTime=sleepTime;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(name+"��ʼ");
			latch.countDown();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+"����");
		}
		
	}
	
	public static class mainThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// ������������ȴ� mCountDownLatch ���count��Ϊ0��
			// Ҳ���ǵȴ������WorkingThread����countDown()
			 System.out.println("���߳̿�ʼ");
			 try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 System.out.println("���߳̽���");
			 
		}
	}
//	public static void main(String[] args) {
//		new mainThread().start();
//		new workThread("���߳�1", 3000).start();;
//		new workThread2("���߳�2", 3000).start();;
//	}
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
					total+=amount;
					countThread+=1;
		
			
				 
			 }
		}
		
	
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i <100; i++) {
			Thread t=new Thread(new excuThread(7l));
			t.start();
			latch.countDown();
		}
		Thread.currentThread().sleep(3000);
		System.out.println("aa"+total);
		System.out.println("bb"+countThread);
		
	}

}
