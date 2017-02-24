package test;

import java.util.Random;

public class demoThread {
	private static ThreadLocal<Integer> t=new ThreadLocal<Integer>();
	private static int price=0;
	  public static void main(String[] args) {
		  for (int i = 0; i <3; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					price=new Random().nextInt(100);
					//t.set(price);
					new A().getInfo();
					new B().getInfo();
				}
			}).start();
		}
		
	}
	  static class A{
		  public void getInfo(){
			  
			  System.out.println(Thread.currentThread().getName()+"A的价格是："+price);
			  System.out.println(Thread.currentThread().getName()+"A的价格是："+t.get());

		  }
	  }
	  
	  static class B{
		  public void getInfo(){
			  System.out.println(Thread.currentThread().getName()+"B的价格是："+price);
			  System.out.println(Thread.currentThread().getName()+"B的价格是："+t.get());
		  }
	  }

}
