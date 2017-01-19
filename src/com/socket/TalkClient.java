package com.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TalkClient {

	public static void main(String args[]) {
		exSocket();
	}

	public static void exSocket() {
		try {
			long start = System.currentTimeMillis();
			InetAddress addr = InetAddress.getByName("localhost");
			Socket socket = new Socket(addr, 10086);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			for (int i = 0; i < 1; i++) {
				String readline = "carrier=M&fm=ZHQYVIP&price=20&pro=24";
				; // sin.readLine(); //从系统标准输入读入一字符串
				// 将从系统标准输入读入的字符串输出到Server
				out.println(readline);
				// 刷新输出流，使Server马上收到该字符串
				out.flush();
				System.out.println("Client:" + readline);

				System.out.println("Servlet :" + in.readLine());
			}
			socket.close(); // 关闭Socket

			long end = System.currentTimeMillis();
			System.out.println(end -start);
		} catch (Exception e) {

			System.out.println("Error" + e); // 出错，则打印出错信息

		}

	}

}