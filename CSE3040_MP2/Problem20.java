package cse3040hw20;


import java.util.ArrayList;
import java.util.Collections;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


class BookReader {

	static ArrayList<BookInfo> readBooksJsoup(String str) {
		

		Document doc=null;
		
		

		try {
			doc=Jsoup.connect(str).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		ArrayList<BookInfo> books = new ArrayList<>();
		Elements bestsellers=doc.select("div.product-info-listView");
		
		Elements titles=bestsellers.select("div.product-shelf-title");
		Elements booktitles=titles.select("a[href]");
		Elements authors=bestsellers.select("div.product-shelf-author");
		Elements bookauthors=authors.select("a[href]");
		Elements prices=bestsellers.select("div.product-shelf-pricing");
		Elements bookprices=prices.select("a[title]");

		for(int i=0;i<booktitles.size();i++) {
			books.add(new BookInfo(i+1,booktitles.eq(i).text(),bookauthors.eq(i).text(),bookprices.eq(2*i+1).text()));
			
		}
		
		
	
		
		return books;
	}
}

class BookInfo implements Comparable<BookInfo> {
	private int rank;
	private String title;
	private String author;
	private String price;
	// 인스턴스 변수는 private

	BookInfo(int rank, String title, String author, String price) {
		this.rank = rank;
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public int compareTo(BookInfo e) {
		if (this.rank > e.rank)
			return -1;
		else
			return 1;
	}
	
	public String toString() {
		return "#"+rank+" "+title+", "+author+", "+price;
	}
}

public class Problem20 {
	public static void main(String[] args) {
		ArrayList<BookInfo> books;
		books = BookReader.readBooksJsoup("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		Collections.sort(books);
		for (int i = 0; i < books.size(); i++) {
			BookInfo book = books.get(i);
			System.out.println(book);
		}
	}
}