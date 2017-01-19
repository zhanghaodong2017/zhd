package com.codebuilding;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import net.spy.memcached.MemcachedClient;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.lemi.utils.HttpUtil;

public class Demo3 {
	private static MemcachedClient cache = Memcaches.getMem();
	public static final Integer		exp		= 86400;
	public static String getTradeNo(String url){
		String allparam = url.substring(url.indexOf("?")+1);
		String[]  kv = allparam.split("&");
		String trade_no = "1233";
		for (int i = 0; i < kv.length; i++)
		{
			if(kv[i].contains("trade_no")){
				trade_no =kv[i].substring(kv[i].indexOf("=")+1);
			}
		}

		return trade_no;


	}
	private static  Date getDate(int index){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, index);
		return calendar.getTime();
	}

	public static void main(String[] args) throws Exception {

//		cache.set("cp-gtw-T-10-1", exp, "ACY10");
//		cache.set(Constant.APP_OWNER_POOL+"000806", exp, "ACY10");
//		cache.set(Constant.DEDUCTION_RATIO+"000806", exp, "10");
		addReqSucNum("MGYX_BPBKL20", "2", "20", "MGYX_BPBKL", "NJ12ixts5y3hf1rz", "", "000000", "M", "4j278xpdm802000000000020161226");
		String url ="http://localhost:8080/CallbackServer/custom/MGYXHJYS?timeid=1484189600&cpparam=NJ12ixts5y3hf1rz&consumeCode=006101920010&status=1800&userId=1125501907&provinceid=971&hRet=0&chid=40290000&";
		String back = HttpUtil.doGet(url);
		System.out.println(back);





	}
	public static void addReqSucNum(String gtwNo, String pro, String price,
			String fm, String orderno, String distro, String appid,
			String carrier, String userid)
	{
		try
		{
			if (StringUtils.isNotBlank(orderno))
			{

				if (price != null && !"".equals(price))
				{
					cache.set(Constant.ORDERNO_PRICE + orderno, exp, price);
				}
				if (distro != null)
				{
					cache.set(Constant.ORDERNO_DISTRO + orderno, exp, distro);
				}
				if (fm != null)
				{
					cache.set(Constant.ORDERNO_FM + orderno, exp, fm);
				}
				cache.set(Constant.ORDERNO_FROM + orderno, exp, "LM");
				if (pro != null)
				{
					cache.set(Constant.ORDERNO_PRO + orderno, exp, pro);
				}
				if (appid != null)
				{
					cache.set(Constant.ORDERNO_APPID + orderno, exp, appid);
				}
				if (userid != null)
				{
					cache.set(Constant.ORDERNO_USERID + orderno, exp, userid);
				}

			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean isPingBiTime(String from,String to){
		int from_time = Integer.valueOf(from);
		int to_time = Integer.valueOf(to);
		boolean isFlag = false;
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		System.out.println(hour);
		if (to_time < from_time) {
			if (hour >= from_time || hour < to_time) {
				isFlag = true;
			} else {
				isFlag = false;
			}
		} else if (to_time > from_time) {
			if (hour >= from_time && hour < to_time) {
				isFlag = true;
			} else {
				isFlag = false;
			}
		} else {
			isFlag = false;
		}
		return isFlag;
	}
	private static void fun21() throws Exception {
		String msg ="abc掌%wwww";
		String encodeStr = URLEncoder.encode(msg,"utf-8");
		System.out.println(encodeStr);
		String decodeStr = URLDecoder.decode(encodeStr, "utf-8");
		System.out.println(decodeStr);

	}
	private static void fun20() {
		String _url = "http://localhost:8080/day0928/AServlet?type=123";
		HttpPost post = new HttpPost(_url);
		List<BasicNameValuePair> formParams = new ArrayList<BasicNameValuePair>();
		formParams.add(new BasicNameValuePair("key", "1028"));
		formParams.add(new BasicNameValuePair("num", "1232132"));

		String re = "";
		try {
			HttpEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
			post.setEntity(entity);
			HttpClient client = HttpClients.createDefault();
		    HttpResponse response = client.execute(post);
		    re = IOUtils.toString(response.getEntity().getContent());
		    System.out.println(re);
		}catch(Exception e){

		}

	}
	private static void fun19() throws IOException {
//		Connection.Response res = Jsoup.connect("http://localhost:8080/day0928/AServlet?type=123")
//				.header("Content-Type", "text/plain")
//				.data("key","qweqew")
//			    .method(Method.POST)
//			    .execute();
//		System.out.println(res.body());

		URL url = new URL("http://localhost:8080/day0928/AServlet?type=123");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.addRequestProperty("Content-Type", "text/plain");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.getOutputStream().write("1231231".getBytes());
		conn.connect();

		String back_json = IOUtils.toString(conn.getInputStream(), "utf-8");
		conn.disconnect();
		System.out.println(back_json);
		System.out.println("sunccess");


	}
	private static void fun18() {
		String orderid ="123#abc12312";
		String regex= "[]";
		if(regex.startsWith("[") && regex.endsWith("]")){
			String realRegex = regex.substring(1,regex.length()-1);
			if(orderid.indexOf(realRegex) != -1){
				orderid = orderid.substring(orderid.indexOf(realRegex)+realRegex.length());
			}
		}else if(orderid.indexOf(regex) != -1){
			orderid = orderid.substring(orderid.indexOf(regex));
		}
		System.out.println(orderid);

	}
	private static void fun12() {
		//{fm=HZSAYZF, price=20, pro=12, carrier=T}

		/*Integer reqNum = (Integer) cache.get(Constant.MONITOR_REQ_SUC +  "HZSAYZF20_12");
		Integer reqPrice = (Integer) cache.get(Constant.MONITOR_SUC_PRICE +"HZSAYZF20_12");
		String spName = (String) cache.get(Constant.fm_ORDER_URL + "HZSAYZF20");
		Double ratio  = (Double) cache.get("sp-ratio-HZSAYZF");//结算比例
		Double arpu = getArpu(reqNum, reqPrice, ratio);
		System.out.println(arpu);*/
	}

	public static Double getArpu(Integer reqNum,Integer reqPrice,Double ratio){
		if(reqNum == null || reqNum <= 0){
			return 0d;
		}
		if(reqPrice == null){
			reqPrice =0;
		}
		if(ratio == null){
			ratio = 0.45d;
		}
		//BigDecimal out= new BigDecimal(reqPrice).multiply(new BigDecimal(ratio)).divide(new BigDecimal(reqNum));
		return (reqPrice*ratio)/reqNum;
	}
	private static void fun10() {
		HashMap<String, String> map = new HashMap<>();
		map.put("1", "1");

	}
	public static int getLeng(String cmd) {
		int len = 8;
		try {
			String index = cmd.substring(cmd.indexOf("{order}")+8,cmd.lastIndexOf("]"));
			len = Integer.valueOf(index);
		} catch (Exception e) {
		}
		return len;
	}

	private static void fun9() throws IOException {
		byte[] byteAr = new byte[]{0x78,0x56,0x34,0x12};
		ByteArrayInputStream bais = new ByteArrayInputStream(byteAr);
		DataInputStream dis = new DataInputStream(bais);
		System.out.println(Integer.toHexString(dis.readInt()));
	}
	private static void fun8() throws Exception {
		/**
		 * 将整形(int)转为字节数组（byte[]）
		 */
		int a = 0x12345678;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(a);
		byte[] b = baos.toByteArray();
		for(int i = 3; i >= 0; i--)
		{
			System.out.print(Integer.toHexString(b[i]));
		}
		System.out.println();

	}
	private static void fun7() {
		int index = 120;
		List<Integer> intList = new ArrayList<>();
		for (int i = 0; i < 1100; i++) {
			intList.add(i);
		}
		System.out.println(intList.size());
		for (int k = 0; k < 12; k++) {
			int fromIndex = k*index;
			int toIndex = (k+1)*index;
			if(fromIndex < intList.size()){
				if(toIndex >= intList.size()){
					toIndex = intList.size();
				}
				List<Integer> newList = intList.subList(fromIndex, toIndex);
				System.out.println(fromIndex+","+toIndex+","+k+":"+newList.size());
			}

		}

	}
	private static void fun6() {
		System.out.println(Integer.MAX_VALUE);
		final List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			list.add(i);
		}
		System.out.println(list.size());

		String[] str = new String[]{"a","b","c"};
		Class class1   = String[].class;
		System.out.println(class1);
		Class class2   = String.class;
		System.out.println(class2);

		Class class3   = Integer[].class;
		System.out.println(class3);

	}
	private static void fun5() {
		String str = StringUtils.join(new String[]{"a","b","c"}, "-");
		System.out.println(str);//a-b-c

		int[] intArray = { 1, 2, 3, 4, 5 };
		ArrayUtils.reverse(intArray);
		System.out.println(Arrays.toString(intArray));//[5, 4, 3, 2, 1]

		byte[] bytes = ByteBuffer.allocate(4).putInt(15).array();
		System.out.print("0x");
		for (byte t : bytes) {
		   System.out.format("%x", t);
		}
		//0x000f
	}
	private static void fun4() {
		System.out.println(System.currentTimeMillis());
		Random random = new Random(System.currentTimeMillis());
		for (int i = 0; i < 100; i++) {

			int a = random.nextInt(100);
			//System.out.println(a);
			if(a <20){
				System.out.println(a);
			}else{
				System.out.println("-----");
			}
		}
		String str ="29.4" ;
		System.out.println(getNumber(Double.valueOf(str)));
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("1", "a");
		linkedHashMap.get("1");
		linkedHashMap.remove("1");
	}
	  public static String getNumber(Double resource){
	    	DecimalFormat df = new DecimalFormat("0.00");
	    	return df.format(resource);
	    }
	private static void fun3() {
		Integer abc= null;
		if(abc >0){
			System.out.println("ok");
		}else{
			System.out.println("mo");
		}

	}

	private static void fun2() {
		/*String carrier = "M";
		String price = "8";
		String pro = "7";
		String gtwin100key = Constant.GTW_IN_100 + carrier + "-" + price + "-"
				+ pro;
		String gtwin100value = (String) cache.get(gtwin100key);
		String gtwkey = Constant.GTW + carrier + "-" + price + "-" + pro;
		String gtwout100key = Constant.GTW_OUT_100 + carrier + "-" + price
				+ "-" + pro;
		String gtwout100value = (String) cache.get(gtwout100key);

		String gtwvalue = gtwin100value + "," + gtwout100value;
		System.out.println(gtwvalue);
		System.out.println(gtwkey);
		System.out.println(cache.get(gtwkey));*/
	}

	/*private static void fun1() {
		Object obj = cache.get(Constant.DAYLIMITURL);
		cache.set(Constant.DAYLIMITURL, 0, new ArrayList<>());
		System.out.println(obj);
		String fm = "SBXHLSM";
		Integer spbili = (Integer) cache.get(Constant.SPINFO_RATIO + fm);
		if (spbili == null) {
			spbili = 0;
		}
		System.out.println("spbili:" + spbili);
		Integer allsum = (Integer) cache.get(Constant.MONITOR_ALL_SUC + fm);
		System.out.println(allsum);
		String all = (String) cache.get(Constant.SP_DAY_LIMIT + fm);
		System.out.println(all);

		Integer sum = (Integer) cache.get(Constant.MONITOR_REQ
				+ "MGYXHLXD10_18");

		System.out.println(sum);
	}*/
}
