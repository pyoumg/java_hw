package cse3040hw17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//이름순 정렬

class MapManager {
	
	static Map<String,Double> readData(String str){
		NewMap map=new NewMap();
		
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
			
				
				map.putItem(name, price);//map에 추가
				
				}

			
			br.close();//파일닫기
			
			
		}  catch (Exception e1) {//예외처리
			return null;
		}

		return map;
	}
	
	
}

@SuppressWarnings("serial") 
class NewMap extends TreeMap<String,Double>{
	private TreeMap<String,Double> map;

	NewMap(){
		map=new TreeMap<String,Double>();
	}
	
	void putItem(String name,double price) {
		map.put(name,price);
		
	}
	
	@Override
	public String toString() {
		String str="";
		String name;
		double price;
		
		Iterator<String> names=map.keySet().iterator();
		
		while(names.hasNext()) {
			name=names.next();
			price=map.get(name);
			str+=name+" "+price+"\n";
		}
		
		
		return str;
	}
	
}


//수정금지
public class Problem17 {
	public static void main(String args[]) {
		Map<String, Double> map = MapManager.readData("input.txt");
		if (map == null) {
			System.out.println("Input file not found.");
			return;
		}
		System.out.println(map);
	}
}