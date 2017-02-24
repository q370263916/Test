package test;

public class SequenceNumber  {
	
	 private int nextNum;

	public  SequenceNumber (){
		nextNum=0;
	}
	public int  getNextNum() {
		return nextNum++;
	}
	 
}
