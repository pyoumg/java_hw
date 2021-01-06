package cse3040hw1;

import java.util.Scanner;

public class Problem1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		char letter;
		System.out.print("ASCII code teller. Enter a letter: ");
		String letters=in.nextLine();
		if(letters.length()!=1) {
			System.out.println("You must input a single uppercase or lowercase alphabet.");
		}
		else {
			letter=letters.charAt(0);
			if(letter<='z'&&letter>='a'||letter<='Z'&&letter>='A') {
				//알파벳이면
				System.out.println("The ASCII code of "+letter+" is "+(int)letter+".");
			}
			else {
				System.out.println("You must input a single uppercase or lowercase alphabet.");
			}
		}
		in.close();
		
	}

}
