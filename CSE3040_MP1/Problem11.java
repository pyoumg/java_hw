package cse3040hw11;



class PalindromeChecker{
	
	public static void check(String str){
		//string type
		int len=str.length();
		for(int i=0;i<len/2;i++) {
			if(str.charAt(i)!=str.charAt(len-i-1)){
				System.out.println(str+" is not a palindrome.");
				return;
			}
		}
		System.out.println(str+" is a palindrome.");
		return;
	
	}
	
	public static void check(int intNum) {
		// integer type (메소드 오버로딩)
		String str=Integer.toString(intNum);
		check(str);
	
	}
	

}




public class Problem11 {
	public static void main(String[] args) {
		PalindromeChecker.check("abcde");
		PalindromeChecker.check("abcba");
		PalindromeChecker.check(1234);
		PalindromeChecker.check(12321);
	}
}