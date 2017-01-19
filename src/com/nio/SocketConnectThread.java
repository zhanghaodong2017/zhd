package com.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocketConnectThread implements Runnable {

	/** selector专门用来监听client连接 **/
	protected Selector selector;
	protected SocketWorkThread workThread;
	private ServerSocketChannel server;
	private static SocketConnectThread socketConnectThread;
	private boolean stop = false;
	public static final int BLOCK = 3;// 100KB数据，多了自动断开连接
	private static Map<String, Object> black_map;

	public static SocketConnectThread newInstance(int port) {
		if (socketConnectThread == null)
			socketConnectThread = new SocketConnectThread(port);
		return socketConnectThread;
	}

	private SocketConnectThread(int port) {
		super();
		// TODO Auto-generated constructor stub
		// 初始大小10000
		black_map = new HashMap<String, Object>(10000);
		try {
			// 打开一个选择器
			selector = Selector.open();
			// 打开服务器套接字通道
			server = ServerSocketChannel.open();
			// 启用/禁用 SO_REUSEADDR 套接字选项
			server.socket().setReuseAddress(true);
			// 调整此通道的阻塞模式
			server.configureBlocking(false);
			// 将 ServerSocket 绑定到特定地址
			server.socket().bind(new InetSocketAddress(port));
			// 向给定的选择器注册此通道，返回一个选择键
			server.register(selector, SelectionKey.OP_ACCEPT);
			workThread = new SocketWorkThread();
			ThreadPool.getInstance().execute(this);
			System.out.println("open socket server->port:" + port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public static SocketConnectThread getInstance() {
        return socketConnectThread;
    }
    public static boolean inBlackList(String ip) {
        return black_map.containsKey(ip);
    }
    public static void addBlackList(String ip) {
        black_map.put(ip, null);
    }

    public static void resetBlackList() {
        // 重黑黑名单
        System.out.println("重黑黑名单");
        black_map.clear();
        try {
            List<String> list = NetTool.readBlackList();
            for (int i = 0; i < list.size(); i++) {
                String ip = list.get(i);
                addBlackList(ip);
                System.out.println("读取黑名单一条:" + ip);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


	@Override
	public void run() {
		 try {
	            processConnector();
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	}
	  public void stop() {
	        selector.wakeup();
	        stop = true;
	        workThread.stop();
	    }
	  /** 等待客户端连接 **/
	    private void processConnector() throws IOException {
	        // 选择ACCEPT连接一组键
	        ServerSocketChannel server = null;
	        SocketChannel client = null;
	        while (!stop && selector.select() > 0) {
	            // 返回此选择器的已选择键集
	            Set<SelectionKey> set = selector.selectedKeys();
	            Iterator<SelectionKey> keys = set.iterator();
	            while (keys.hasNext()) {
	                SelectionKey key = keys.next();
	                // 置空迭代器
	                keys.remove();
	                // 测试此键的通道是否已准备好接受新的套接字连接
	                try {
	                    if (key.isAcceptable()) {
	                        // 返回为之创建此键的通道
	                        server = (ServerSocketChannel) key.channel();
	                        // 接受到此通道套接字的连接
	                        client = server.accept();
	                        String ip = client.socket().getInetAddress()
	                                .getHostAddress();
	                        boolean isBlack = inBlackList(ip);
	                        System.out.println(ip + "是否黑名字:" + isBlack);
	                        if (isBlack) {
	                            server.close();
	                            client.close();
	                            key.cancel();
	                        } else {
	                            // 调整此通道的阻塞模式
	                            client.configureBlocking(false);
	                            // 向给定的选择器注册此通道，返回一个选择键
	                            client.register(workThread.getSelector(),
	                                    SelectionKey.OP_READ,
	                                    new ByteArrayOutputStream());
	                        }
	                    }
	                } catch (Exception exception) {
	                    exception.printStackTrace();
	                    if (key != null) {
	                        key.cancel();
	                        key.channel().close();
	                    }
	                }
	            }
	        }
	    }

}
