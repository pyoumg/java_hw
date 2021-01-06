package cse3040hw18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//이름순 정렬

class Element implements Comparable<Element> {
	private String name;
	private double price;// 인스턴스 변수

	Element(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String toString() {

		return this.name + " " + this.price;
	}

	@Override
	public int compareTo(Element a) {
		if (a.price > this.price)
			return -1;
		else if (a.price < this.price)
			return 1;
		else// 가격이 같으면
			return this.name.compareTo(a.name);
	}

}

class MapManager {
	static Map<String, Double> readData(String str) {
		NewMap map = new NewMap();

		BufferedReader br;
		String name;
		double price;
		try {
			br = new BufferedReader(new FileReader(str));

			while (true) {

				String line = br.readLine();

				if (line == null)
					break;
				String[] result = line.split(" ", 2);// name,price
				name = result[0].trim();
				price = Double.parseDouble(result[1].trim());// 공백제거

				map.putItem(name, price);// map에 추가

			}

			br.close();// 파일닫기

		} catch (Exception e1) {// 예외처리
			return null;
		}

		return map;
	}

}

@SuppressWarnings("serial") 
class NewMap extends HashMap<String, Double> {
	private HashMap<String, Double> map;

	NewMap() {
		map = new HashMap<String, Double>();
	}

	void putItem(String name, double price) {
		map.put(name, price);

	}

	@Override
	public String toString() {
		String str = "";
		String name;
		double price;
		List<Element> list = new ArrayList<>();
		Iterator<String> it = map.keySet().iterator();// 같은 이름을 갖는 것은 없음

		while (it.hasNext()) {
			name = it.next();
			price = map.get(name);
			list.add(new Element(name, price));
		}
		Collections.sort(list);// 정렬

		Iterator<Element> it2 = list.iterator();
		while (it2.hasNext()) {
			Element temp = it2.next();
			str += temp.toString() + "\n";
		}

		return str;
	}

}

public class Problem18 {
	public static void main(String args[]) {
		Map<String, Double> map = MapManager.readData("input.txt");
		if (map == null) {
			System.out.println("Input file not found.");
			return;
		}
		System.out.println(map);
	}
}