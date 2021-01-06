package cse3040hw15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



class MyFileReader{
	static boolean readDataFromFile(String str,ArrayList<Item> list) {
		BufferedReader br;
		boolean find;
		
		try {
			br = new BufferedReader(new FileReader(str));

			while (true) {

				String line = br.readLine();
				
				if (line == null)
					break;
								
				String []words=line.split(" ");
				
				for(String word:words)
					{
					find=false;//초기화
					word=word.trim();//앞뒤 공백 제거
					if(word.length()>0) {//공백이 아니면
						word=word.toLowerCase();//소문자로 바꾸기
						for (Item item:list) {
							if(item.getWord().equals(word)){//이미 list에 그 단어가 있는 객체가 있으면
								item.plusNum();
								find=true;
								break;
							}
						}
						if(find==false) {//못찾았으면 새로 만들기
							list.add(new Item(word));
						}
						
						
						
					}
					}
				
				}

			
			br.close();//파일닫기
		} catch (IOException e) {
			return false;//파일 없다는 내용은 main에서 출력함
		}

		
		
		return true;
	}
}


class Item{
	private String word;//해당 단어
	private int num;//몇개있는지
	public Item(String word){
		this.word=word;
		this.num=1;//처음에 만들때 하나 있는 상태
	}
	
	void plusNum() {
		this.num++;
		return;
	}
	public String getWord() {
		return this.word;
	}
	
	public String toString() {
		return this.word+" "+this.num;
	}
	
}




//수정금지
public class Problem15 {
	public static void main(String[] args) {
		ArrayList<Item> list = new ArrayList<>();
		boolean rv = MyFileReader.readDataFromFile("input_prob15.txt", list);
		if (rv == false) {
			System.out.println("Input file not found.");
			return;
		}
		for (Item it : list)
			System.out.println(it);
	}
}
