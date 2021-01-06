package cse3040fp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	static class ClientSender extends Thread {
		Socket socket;
		DataOutputStream out;
		String name;

		ClientSender(Socket socket, String name) {
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				this.name = name;
			} catch (Exception e) {}
		}
		

		@SuppressWarnings("all")
		public void run() {
			Scanner scanner = new Scanner(System.in);
			
			try {
				if(out!=null) {
					out.writeUTF(name);
				}
				while (out != null) {//프로토콜
					out.writeUTF(scanner.nextLine());
				}
				
			} catch (IOException e) {}
			catch(Exception e1) {}//ignore
			
		}
	}//ClientSender end

	static class ClientReceiver extends Thread {//inner class
		Socket socket;
		DataInputStream in;

		ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {}
		}

		public void run() {
			while (in != null) {
				try {
					System.out.print(in.readUTF());
				} catch (IOException e) {}
			}
		}
	}//ClientReceiver end
	
	static String getName() {
		@SuppressWarnings("resource")
		Scanner in=new Scanner(System.in);
		String name=null;
		while(true) {
			System.out.print("Enter userID>> ");
			name=in.nextLine();
			if(name.length()>0) {
				for(int i=0;i<name.length();i++) {
					char temp=name.charAt(i);
					if(temp>='a'&&temp<='z'||temp>='0'&&temp<='9') {//lower case랑 숫자
						if(i==name.length()-1) {//마지막
							System.out.println("Hello "+name+"!");
							return name;
						}
					}
					else {
						System.out.println("UserID must be a single word with lowercase alphabets and numbers.");
						break;//다시 입력받기
					}
					
				}
			}
			else {//그냥 아무것도 입력안함
				System.out.println("UserID must be a single word with lowercase alphabets and numbers.");
			}
		}
	
	}//getName() end
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length != 2) {
			System.out.println("Please give the IP address and port number as arguments.");
			System.exit(0);// 종료
		}
		
		String serverIp=args[0].trim();
		int portNum=Integer.valueOf(args[1].trim());
		try {
			Socket socket = new Socket(serverIp, portNum);
			String name=getName();//아이디 입력받기
			Thread sender = new Thread(new ClientSender(socket, name));
			Thread receiver = new Thread(new ClientReceiver(socket));
			sender.start();
			receiver.start();
			} catch(ConnectException ce) {//연결오류
				System.out.println("Connection establishment failed.");
				System.exit(0);
			} catch(Exception e) {}
			

	}

}
