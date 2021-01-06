package cse3040hw12;

import java.util.Arrays;

class SubsequenceChecker {

	public static void check(String str1, String str2) {

		int[] index = new int[str2.length()];
		Arrays.fill(index, -1);
		// index 저장할 배열

		check(str1, str2, 0, 0, index);// recursive한 형태로 구현하기 위해

		if (index[str2.length() - 1] == -1)
			System.out.println(str2 + " is not a subsequence of " + str1);
		else {// 마지막까지 해당하는 인덱스가 있는경우
			System.out.println(str2 + " is a subsequence of " + str1);
			for (int i = 0; i < str2.length(); i++)
				System.out.print(index[i] + " ");
			System.out.println();
		}
		return;

	}

	public static void check(String str1, String str2, int nowIndex, int startIndex, int[] index) {
		// nowIndex:지금 탐색중인 인덱스
		// startIndex:시작지점인 인덱스

		int temp = 0;
		temp = checkChar(str1, str2.charAt(nowIndex), startIndex);
		if (temp != -1) {//인덱스를 찾았으면
			index[nowIndex++] = temp;//index[]에 저장
			if (nowIndex < str2.length() ) {
				//아직 다 확인하지 않았으면
				check(str1, str2, nowIndex, temp + 1, index);
			}//recursive
		}

		return;

	}

	public static int checkChar(String str1, char a, int startIndex) {

		for (int i = startIndex; i < str1.length(); i++) {
			// startIndex부터 끝까지 단어가 같은 index있는지 조사(indexOf()와 유사)
			if (str1.charAt(i) == a)// 찾으면
				return i;// 인덱스 return
		}
		return -1;// 못찾을때
	}

}

public class Problem12 {
	public static void main(String[] args) {
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "pads");
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "padsx");
	}
}
