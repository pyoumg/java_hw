package cse3040hw7;

import java.util.Scanner;


interface IntSequenceStr {
	boolean hasNext();

	int next();
}


class BinarySequenceStr implements IntSequenceStr  {
	private int decNumber,bPow=0;
	//bPow: 2진수로 변환하기 위해 사용되는 수 
	public BinarySequenceStr(int num) {
		decNumber=num;
		while(Math.pow(2,bPow+1)<=decNumber)
			bPow++;
	}
	public boolean hasNext() {
		if(bPow<0)
			return false;
		else 
			return true;
	}

	public int next() {
		if(Math.pow(2, bPow)<=decNumber)
		{
			decNumber-=Math.pow(2, bPow);
			bPow--;
			return 1;
		}
		else {
			bPow--;
			return 0;
		}
	}
}



public class Problem07 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a positive integer: ");
		String str = in.nextLine();
		int num = Integer.parseInt(str);
		in.close();
		System.out.println("Integer: " + num);
		IntSequenceStr seq = new BinarySequenceStr(num);
	
		System.out.print("Binary number: ");
		while (seq.hasNext())
			System.out.print(seq.next());
		System.out.println(" ");
	}
}