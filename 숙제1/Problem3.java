package cse3040hw3;

import java.util.Scanner;

public class Problem3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		System.out.print("Enter a text: ");
		String str=in.nextLine();
		String letter;
		int ans=0;
		
		while(true) {//한글자 입력받을때 까지 반복문
		System.out.print("Enter a letter: ");
		letter=in.nextLine();
		if(letter.length()==1)
			break;
		else
			System.out.println("You must enter a single letter.");
		}	
		char letterChar=letter.charAt(0);
		for(int i=0;i<str.length();i++) {
			if(letterChar==str.charAt(i))
				ans++;
		}
		
		System.out.println("There are "+ans+" "+letterChar+"'s in the text.");
		in.close();
	}

}
