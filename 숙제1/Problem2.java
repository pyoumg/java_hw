package cse3040hw2;

import java.util.Scanner;

public class Problem2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int randomNum=(int)(Math.random()*100+1);//1~100
		int tryNum=1;//1부터 시작하니까
		int startNum=1,endNum=100;
		int guessNum;
		
		
		Scanner in=new Scanner(System.in);
		
		
		while(true) {
		
			System.out.printf("[%d] Guess a number (%d-%d): ",tryNum,startNum,endNum );
			guessNum=in.nextInt();
			if(randomNum==guessNum) {
				System.out.println("Correct! Number of guesses: "+tryNum);
				break;
				
			}
			else if(guessNum>endNum||guessNum<startNum) {
				System.out.println("Not in range!");
			}
			else if(randomNum>guessNum) {
				System.out.println("Too small!");
				tryNum++;
				startNum=guessNum+1;
			}
			else {
				System.out.println("Too large!");
				tryNum++;
				endNum=guessNum-1;
			}
			
			
		}
		in.close();
		
	}

}
