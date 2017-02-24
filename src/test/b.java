package test;

import java.lang.reflect.Field;

public class b {
 
 
	
public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Integer a=1  ,b=2;
		System.out.println("before a="+a+"b="+b);
		change(a,b);
		System.out.println("alfer a="+a+"b="+b);
//		  Field[] f = Integer.class.getDeclaredFields();  
//          for(Field field : f){  
//              field.setAccessible(true);  
//              System.out.println(field.getName()+":");  
	
}


public static void change(Integer i1,Integer i2) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
	Field  f=Integer.class.getDeclaredField("value");
	f.setAccessible(true);
	Integer temp=new Integer(i1.intValue()) ;
	f.set(i1, i2.intValue());
	f.set(i2, temp);
	System.out.println("alfer a="+i2+"b="+i1);
	System.exit(2);
}
}