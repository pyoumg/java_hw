package cse3040hw13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Text {
	private int[] numAlphabet;//인스턴수 변수는 private으로

	Text() {//생성자

		numAlphabet = new int['z' - 'a' + 1];//알파벳 수만큼
		Arrays.fill(numAlphabet, 0);//초기화
		
	}

	boolean readTextFromFile(String str) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(str));

			while (true) {

				String line = br.readLine();
				if (line == null)
					break;
				line = line.toLowerCase();//소문자로 변환
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) <= 'z' && line.charAt(i) >= 'a')
						numAlphabet[line.charAt(i) - 'a']++;
					//알파벳이면 배열의 숫자 증가
				}

			}
			br.close();//파일 닫기
		} catch (FileNotFoundException e) {// 파일 없음
			System.out.println("Input file not found.");
			return false;
		} catch (IOException e1) {//다른 에러
			return false;
		}

		return true;

	}

	int countChar(char c) {
		return numAlphabet[c - 'a'];
	}
}

public class Problem13 {
	public static void main(String[] args) {
		Text t = new Text();
		if (t.readTextFromFile("input_prob13.txt")) {
			for (char c = 'a'; c <= 'z'; c++) {
				System.out.println(c + ": " + t.countChar(c));
			}
		}
	}
}