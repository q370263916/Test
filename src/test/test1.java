package test;

import java.util.HashMap;
import java.util.Map;

public class test1 {
	
	// ׼�����������
	  private static int data = 0;
	  // ��Ÿ����̶߳�Ӧ������
	  private static Map<Thread, Integer> threadData = new HashMap<Thread, Integer>();
	 
	 public static void main(String[] args) {
//		String str="";
//		int a ;
//		int i=str.length();
//		System.out.println(i+"i=");
		 
	      //�����̹߳���ͬһ����Դ        
		 SequenceNumber creator=new SequenceNumber();
	        domeThread thread1=new domeThread(creator);
	        domeThread thread2=new domeThread(creator);
	        domeThread thread3=new domeThread(creator);
	        
	        thread1.start();
	        thread2.start();
	        thread3.start();
	}
	 
	  static class A {
		    public void get() {
		      int data = threadData.get(Thread.currentThread());
		      System.out.println("A from " + Thread.currentThread().getName()
		          + " get data :" + data);
		    }
		  }
		 
		  static class B {
		    public void get() {
		      int data = threadData.get(Thread.currentThread());
		      System.out.println("B from " + Thread.currentThread().getName()
		          + " get data :" + data);
		    }
		  }

}
