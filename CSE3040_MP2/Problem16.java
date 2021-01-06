package cse3040hw16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;




class Element implements Comparable<Element>{
	private String name;
	private double price;//인스턴스 변수
	Element(String name,double price){
		this.name=name;
		this.price=price;
	}
	
	public String toString() {
		
		return this.name+" "+this.price;
	}
	
	@Override
	public int compareTo(Element a) {
		if(a.price>this.price)
			return -1;
		else if(a.price<this.price)
			return 1;
		else//가격이 같으면
			return this.name.compareTo(a.name);
	}
	
}

class ElementReader{
	static ArrayList<Element> readElements(String str) {
		ArrayList <Element> list=new ArrayList<>();
		BufferedReader br;
		String name;
		double price;
		try {
			br = new BufferedReader(new FileReader(str));

			while (true) {

				String line = br.readLine();
				
				if (line == null)
					break;
				String []result=line.split(" ",2);//name,price
				name=result[0].trim();
				price= Double.parseDouble(result[1].trim());//공백제거
				list.add(new Element(name,price));//list에 추가
				}

			
			br.close();//파일닫기
			
			
		}  catch (Exception e1) {//예외처리
			return null;
		}

		
		
		return list;
	}
	
	
	
}

//수정 금지
public class Problem16 {
	public static void main(String args[]) {
		ArrayList<Element> list = ElementReader.readElements("input.txt");
		if (list == null) {
			System.out.println("Input file not found.");
			return;
		}
		Collections.sort(list);
		Iterator<Element> it = list.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
}