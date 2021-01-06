package cse3040hw5;

import java.util.Scanner;

public class Problem5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int first=0,second=0,temp=0;
		int firstIndex=0,secondIndex=0;
		Scanner in=new Scanner(System.in);
		
		System.out.println("Enter exam scores of each student.");
		for(int i=0;i<5;i++) {
			System.out.printf("Score of student %d: ",i+1);
			temp=in.nextInt();
			if(i==0) {
				first=temp;
				firstIndex=0;
				}
			else if(i==1) {
				if(temp>first) {
					second=first;
					first=temp;
					secondIndex=0;
					firstIndex=1;
					}
				else {
					second=temp;
					secondIndex=1;
				}
				
				}
			else
			{
				if(temp>first)
					{
					second=first;
					first=temp;
					secondIndex=firstIndex;
					firstIndex=i;
					
					}
				else if(temp>second)
					{
					second=temp;
					secondIndex=i;
					}
			}
			
		}
		System.out.printf("The 1st place is student %d with %d points.\n",firstIndex+1,first);
		System.out.printf("The 2nd place is student %d with %d points.\n",secondIndex+1,second);
		
		in.close();
		
	}

}
