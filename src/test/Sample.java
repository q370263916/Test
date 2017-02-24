package test;

import java.util.concurrent.CountDownLatch;

public class Sample {
	// 计数器
	public static CountDownLatch latch=new CountDownLatch(100);
	
	/**
	 * 工作线程类
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
			System.out.println(name+"开始");
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			latch.countDown();
			System.out.println(name+"结束");
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
			System.out.println(name+"开始");
			latch.countDown();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name+"结束");
		}
		
	}
	
	public static class mainThread extends Thread{
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			// 会阻塞在这里等待 mCountDownLatch 里的count变为0；
			// 也就是等待另外的WorkingThread调用countDown()
			 System.out.println("主线程开始");
			 try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 System.out.println("主线程结束");
			 
		}
	}
//	public static void main(String[] args) {
//		new mainThread().start();
//		new workThread("子线程1", 3000).start();;
//		new workThread2("子线程2", 3000).start();;
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
