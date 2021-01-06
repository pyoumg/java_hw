package cse3040hw19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

class BookReader {

	static ArrayList<BookInfo> readBooks(String str) {
		ArrayList<String> lines = new ArrayList<String>();

		URL url = null;
		BufferedReader input = null;
		String line = "";
		String title = null;
		String author = null;
		String price = null;

		try {
			url = new URL(str);
			input = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((line = input.readLine()) != null) {
				if (line.trim().length() > 0)
					lines.add(line);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		int rank = 1;
		int status = 0;
		ArrayList<BookInfo> books = new ArrayList<>();
		for (int i = 0; i < lines.size(); i++) {

			String l = lines.get(i);

			if (status == 0) {
				if (l.contains("div class=\"product-shelf-title"))
					status = 1;
			} else if (status == 1) {
				if (l.contains("a title")) {

					int begin = l.indexOf("\">");
					int end = l.indexOf("</a>");
					title = l.substring(begin + 2, end);

					status = 2;// title 끝
				}
			} else if (status == 2) {// author 찾기
				if (l.contains("div class=\"product-shelf-author contributors")) {

					int begin = l.indexOf(">");
					int end = l.indexOf("</a>");
					author = l.substring(begin + 1, end);
					begin = author.indexOf(">");
					author = author.substring(begin + 1, author.length());

					status = 3;// author 끝
				}
			} else if (status == 3) {// price찾기

				if (l.contains("div class=\"product-shelf-pricing")) {
					status = 4;
				}
			} else if (status == 4) {
				if (l.contains("a title=\"\" class=\" current link")) {
					int begin = l.indexOf("$");
					int end = l.indexOf("</a>");
					price = l.substring(begin + 1, end);

					// BookInfo
					books.add(new BookInfo(rank, title, author, price));

					rank++;
					status = 0;
				}
			}
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
		return "#"+rank+" "+title+", "+author+", $"+price;
	}
}

public class Problem19 {
	public static void main(String[] args) {
		ArrayList<BookInfo> books;
		books = BookReader.readBooks("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");

		Collections.sort(books);
		for (int i = 0; i < books.size(); i++) {
			BookInfo book = books.get(i);
			System.out.println(book);
		}

	}
}