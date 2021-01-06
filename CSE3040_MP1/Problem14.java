package cse3040hw14;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;


class ItemReader{
	static boolean fileToBox(String str,FruitBox<? extends Fruit> box) {
		BufferedReader br;
		String name;
		double price;
		
		
		try {
			br = new BufferedReader(new FileReader(str));

			while (true) {

				String line = br.readLine();
				
				if (line == null)
					break;
				String []result=line.split(" ",2);
				name=result[0].trim();
				price= Double.parseDouble(result[1].trim());//공백제거
				box.add(new Fruit(name,price));
				
				
				
				}

			
			br.close();//파일닫기
		} catch (FileNotFoundException e) {// 파일 없음
			System.out.println("Input file not found.");
			return false;
		} catch (IOException e1) {
			return false;
		}

		
		
		return true;
	}
}

class Fruit {
	private String name;
	private double price;//instance variable
	
	public Fruit(String name,double price){
		this.name=name;
		this.price=price;
	}
	public String getName() {
		return this.name;
	}
	public double getPrice() {
		return this.price;
	}
}


class Box<T> {
	private TreeMap<Double,String>box=new TreeMap<>();
	//double:key
	
	
	public void addBox(double price,String name) {
		box.put(price,name);
	}
	public double getMinPrice() {
		return box.firstKey();
	}
	public double getMaxPrice() {
		return box.lastKey();
	}
	public String getValue(double key) {
		return box.get(key);
	}
	


	public String getMinItem() {
		return getValue(getMinPrice());
		
	}
	
	public String getMaxItem() {
		return getValue(getMaxPrice());
		
		
	}
	public int getNumItems() {
		return box.size();
		
	}
	

	
	public double getAvgPrice() {
		double ans=0;
		
		Set<Double> priceSet=box.keySet();//key만 가져오기
		for(double key:priceSet) {
			ans+=key;
		}
		ans=ans/box.size();
		return ans;
		
	}
}

class FruitBox<T extends Fruit> extends Box<T> {

	void add(Fruit fruit) {
		addBox(fruit.getPrice(),fruit.getName());
		System.out.println(fruit.getName()+" "+fruit.getPrice());
		//출력
	}

}


public class Problem14 {
	public static void main(String[] args) {
		FruitBox<Fruit> box = new FruitBox<>();
		boolean rv = ItemReader.fileToBox("input_prob14.txt", box);
		if (rv == false)
			return;
		box.add(new Fruit("orange", 9.99));
		System.out.println("----------------");
		System.out.println(" Summary");
		System.out.println("----------------");
		System.out.println("number of items: " + box.getNumItems());
		System.out.println("most expensive item: " + box.getMaxItem() + " (" + box.getMaxPrice() + ")");
		System.out.println("cheapest item: " + box.getMinItem() + " (" + box.getMinPrice() + ")");
		System.out.printf("average price of items: %.2f", box.getAvgPrice());
	}
}