package cse3040fp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

class Book implements Comparable<Book>  {
	String title;
	String author;
	String user;

	Book(String title, String author, String user) {
		this.title = title;
		this.author = author;
		this.user = user;
	}
	
	@Override
	public int compareTo(Book o) {
		return this.title.toLowerCase().compareTo(o.title.toLowerCase());
	}
}

public class Server {
	HashMap<String, DataOutputStream> clients;
	static ArrayList<Book> books;//main()에서도 사용
	Server() {
		clients = new HashMap<>();
		Collections.synchronizedMap(clients);
	}

	

	public void start(int portNum) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(portNum);
			System.out.println("server has started.");
			while (true) {
				socket = serverSocket.accept();
				System.out.println("a new connection from [" + socket.getInetAddress() + ":" + socket.getPort() + "]");
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	void sendToAll(String msg) {
		Iterator<String> it = clients.keySet().iterator();
		while(it.hasNext()) {
		try {
		DataOutputStream out = (DataOutputStream)clients.get(it.next());
		out.writeUTF(msg);
		} catch(IOException e) { }
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 1) {
			System.out.println("Please give the port number as an argument.");
			System.exit(0);// 종료
		}
		BufferedReader br;

		books=new ArrayList<>();
		try {

			br = new BufferedReader(new FileReader("books.txt"));
			while (true) {//(이미 존재하는) books.txt읽기
				String line = br.readLine();
				if (line == null)
					break;
				String temp[] = line.split("\t");
				String title = temp[0].trim();
				String author = temp[1].trim();
				String user = temp[2].trim();
				
				books.add(new Book(title, author, user));
				
			}

			br.close();// 다 읽었으니까 닫기

		} catch (IOException e) {}//ignore

		int portNum = Integer.valueOf(args[0]);
		new Server().start(portNum);

	}
	
	void update() {//books가 변경되었기 때문에 파일을 다시써야함
		
		//books 정렬
		Collections.sort(books);
		
		//text파일 다시쓰기
		PrintWriter pw=null;
		Iterator<Book> bookiterator=books.iterator();
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter("books.txt")));
		while(bookiterator.hasNext()) {
			Book temp=bookiterator.next();
			pw.println(temp.title+"\t"+temp.author+"\t"+temp.user);
			}
			pw.close();//파일 닫기
		}
		catch(Exception e) {}//ignore
		
	}
	
	boolean findBook(String title) {
		Iterator<Book> bookiterator=books.iterator();
		String tempTitle=title.toLowerCase();
		while(bookiterator.hasNext()) {
			Book temp=bookiterator.next();
			if(temp.title.toLowerCase().equals(tempTitle)) {
				return true;
			}
			
			}
		return false;
	}
	String borrowBook(String title,String user) {
		//title이 이미 빌려졌거나 책이 없으면 false를 반환
		//아니면 책을 borrow하고 true return 
		Iterator<Book> bookiterator=books.iterator();
		String tempTitle=title.toLowerCase();
		while(bookiterator.hasNext()) {
			Book temp=bookiterator.next();
			if(temp.title.toLowerCase().equals(tempTitle)) {
				if(temp.user.equals("-")) {
					temp.user=user;//user변경하고
					return temp.title;
				}
				
				return null;
			}
			
		}
		return null;
	}
	
	String returnBook(String title,String user) {
		//title을 안빌렸거나 책이 없으면 false를 반환
		//아니면 책을 borrow하고 true return 
		Iterator<Book> bookiterator=books.iterator();
		String tempTitle=title.toLowerCase();
		while(bookiterator.hasNext()) {
			Book temp=bookiterator.next();
			if(temp.title.toLowerCase().equals(tempTitle)) {
				if(temp.user.equals(user)) {
					temp.user="-";//user변경하고
					return temp.title;
				}
				//다른사람이 빌렸거나 안빌렸으면
				return null;
			}
			
		}
		return null;//해당 책이 없음
	}
	
	class ServerReceiver extends Thread {//inner class
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		ServerReceiver(Socket socket) {//이미 만들어진 socket
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {}
			
		}
	
	public void run() {
		String name = "";
		try {
		name = in.readUTF();
		
		clients.put(name, out);
		while (in != null) {//접속 끊어지지 x
		out.writeUTF(name+">> ");
		String temp=in.readUTF().trim();//명령어
		
		//case-sensitive
	
		if(temp.equals("add")) {
			out.writeUTF("add-title> ");
			String title=in.readUTF().trim();
			String author;
			if(title.length()>0) {
				out.writeUTF("add-author> ");
				author=in.readUTF().trim();
				if(author.length()>0&&findBook(title)==false) {
					books.add(new Book(title,author,"-"));
					//default는 아무도 안빌려간거
					update();//Text랑 books업데이트
					out.writeUTF("A new book added to the list.\n");
				}
				else if(author.length()>0&&findBook(title)==true) {
					//책 title이 이미 있음
					out.writeUTF("The book already exists in the list.\n");
				}
				
			}
		}//add end
		
		else if(temp.equals("return")) {
			out.writeUTF("return-title> ");
			String title=in.readUTF().trim();
			if(title.length()>0) {//비어있는게 아니면
				String flag=returnBook(title,name);
				if(flag!=null) {//flag:books에 저장된 책제목 (실패하면  null)
					out.writeUTF("You returned a book. - "+flag+"\n");
					update();
				}
				else {//대출실패
					out.writeUTF("You did not borrow the book.\n");
				}
			}
			
			
			
		}//return end
		else if(temp.equals("search")) {
			
			String searchStr;
			while(true) {
				out.writeUTF("search-string> ");
				searchStr=in.readUTF();//공백도 유효
				if(searchStr.length()>=3||searchStr.length()==0)
					break;
				else{
					out.writeUTF("Search string must be longer than 2 characters.\n");
				}
			}
			if(searchStr.length()>=3) {
			Iterator<Book> bookiterator=books.iterator();
			int bookNum=0;//검색결과
			ArrayList<String> bookTitle=new ArrayList<>();
			while(bookiterator.hasNext()) {
				Book b=bookiterator.next();
				if(b.title.toLowerCase().indexOf(searchStr.toLowerCase())>-1||b.author.toLowerCase().indexOf(searchStr.toLowerCase())>-1) {
					bookNum++;
					bookTitle.add(b.title+", "+b.author);
					}
				}
			out.writeUTF("Your search matched "+bookNum+" results.\n");
			bookNum=1;
			for(String bookTemp:bookTitle) {
				out.writeUTF(bookNum+". "+bookTemp+"\n");
				bookNum++;
			}//search만 한거라 books에는 변함 없음
			}
			
		}//search end
		else if(temp.equals("borrow")) {
			out.writeUTF("borrow-title> ");
			String title=in.readUTF().trim();
			if(title.length()>0) {//비어있는게 아니면
				String flag=borrowBook(title,name);
				if(flag!=null) {//flag:books에 저장된 책제목 (실패하면  null)
					out.writeUTF("You borrowed a book. - "+flag+"\n");
					update();
				}
				else {//대출실패
					out.writeUTF("The book is not available.\n");
				}
			}
			
			
		}//borrow end
		else if(temp.equals("info")) {
			Iterator<Book> bookiterator=books.iterator();
			int bookNum=0;//빌린 권수
			ArrayList<String> bookTitle=new ArrayList<>();
			while(bookiterator.hasNext()) {
				Book b=bookiterator.next();
				if(b.user.equals(name)) {
					bookNum++;
					bookTitle.add(b.title+", "+b.author);
					}
				}
			out.writeUTF("You are currently borrowing "+bookNum+" books:\n");
			bookNum=1;
			for(String bookTemp:bookTitle) {
				out.writeUTF(bookNum+". "+bookTemp+"\n");
				bookNum++;
			}
		}
		else {//해당되지 않는 명령어
			out.writeUTF("[available commands]\n"+
					"add: add a new book to the list of books.\n"+
					"borrow: borrow a book from the library.\n"+
					"return: return a book to the library.\n"+
					"info: show list of books I am currently borrowing.\n"+
					"search: search for books.\n");
		}
		
	}//while end
		
		
		
		} catch(IOException e) {
		// ignore
		} finally {//접속이 끊어짐
		clients.remove(name);
		System.out.println("["+socket.getInetAddress()+":"+socket.getPort()+"]"+" has disconnected.");
		System.out.println("Current number of users: " + clients.size());
		}
		}
	}

}
