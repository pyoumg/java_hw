package cse3040hw4;

import java.util.Scanner;

public class Problem4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		System.out.print("Enter a text: ");
		String str=in.nextLine();
		String str2;
		int ans=0;
		
		while(true) {//한글자 입력받을때 까지 반복문
		System.out.print("Enter a string: ");
		str2=in.nextLine();
		if(str2.length()!=0)
			break;
		else
			System.out.println("You must enter a string.");
		}	
		for(int i=0;i<str.length()-str2.length()+1;i++) {
			for(int j=0;j<str2.length();j++) {
				if(str2.charAt(j)!=str.charAt(i+j))
					break;
				else if(j==str2.length()-1)
					ans++;
			}
		}
		
		System.out.println("There are "+ans+" instances of \""+str2+"\".");
		in.close();
	}

}
