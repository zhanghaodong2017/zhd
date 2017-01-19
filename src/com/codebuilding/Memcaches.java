package com.codebuilding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import net.spy.memcached.MemcachedClient;

public class Memcaches {

	static MemcachedClient cache = null;
	public static String host="";
	public static int port = 11211;
	public static MemcachedClient getMem() {

		if (cache == null) {
			synchronized (Memcaches.class) {
				if (cache == null) {
					String osUser=System.getProperty("os.name");
					if(osUser != null && osUser.equals("Linux")){
						  host = "218.244.139.230";
					}else{
						  host = "127.0.0.1";
					}
					try {
						cache = new MemcachedClient(
								new BinaryConnectionFactory(),
								AddrUtil.getAddresses(host + ":" + port));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return cache;
	}


	public static String[] getAllKeys() {
		String[] keys = allkeys(Memcaches.host, Memcaches.port).split("\n");
		Arrays.sort(keys);
		return keys;
	}

	public static String allkeys(String host, int port) {
		StringBuffer r = new StringBuffer();
		try {
			Socket socket = new Socket(host, port);
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			os.println("stats items");
			os.flush();
			String l;
			while (!(l = is.readLine()).equals("END")) {
				r.append(l).append("\n");
			}
			String rr = r.toString();
			Set<String> ids = new HashSet<String>();
			if (rr.length() > 0) {
				r = new StringBuffer();// items
				rr.replace("STAT items", "");
				for (String s : rr.split("\n")) {
					ids.add(s.split(":")[1]);
				}
				if (ids.size() > 0) {
					r = new StringBuffer();//
					for (String s : ids) {
						os.println("stats cachedump " + s + " 0");
						os.flush();
						while (!(l = is.readLine()).equals("END")) {
							r.append(l.split(" ")[1]).append("\n");
						}
					}
				}
			}

			os.close();
			is.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
		return r.toString();
	}

	public static String telnet(String host, int port, String cmd) {
		StringBuffer r = new StringBuffer();
		try {
			Socket socket = new Socket(host, port);
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			BufferedReader is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			os.println(cmd);
			os.flush();
			String l;
			while (!(l = is.readLine()).equals("END")) {
				r.append(l).append("\n");
			}
			os.close();
			is.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
		return r.toString();
	}
	public static void main(String args[]) {
		String[] keys = allkeys("218.244.139.230", 11211).split("\n");
		Arrays.sort(keys);
		for (String s : keys) {
			System.out.println(s);
		}
		// System.out.println(telnet("localhost", 11211, "stats"));
	}
}
