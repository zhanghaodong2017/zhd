package com.lemi.utils;

import java.io.File;
import java.io.FileOutputStream;

public class JoyUtils {
	public static String getSPro(int iPro) {
		String sPro = "";
		switch (iPro) {
		case 1:
			sPro = "上海";
			break;
		case 2:
			sPro = "云南";
			break;
		case 3:
			sPro = "内蒙古";
			break;
		case 4:
			sPro = "北京";
			break;
		case 5:
			sPro = "吉林";
			break;
		case 6:
			sPro = "四川";
			break;
		case 7:
			sPro = "天津";
			break;
		case 8:
			sPro = "宁夏";
			break;
		case 9:
			sPro = "安徽";
			break;
		case 10:
			sPro = "山东";
			break;
		case 11:
			sPro = "山西";
			break;
		case 12:
			sPro = "广东";
			break;
		case 13:
			sPro = "广西";
			break;
		case 14:
			sPro = "新疆";
			break;
		case 15:
			sPro = "江苏";
			break;
		case 16:
			sPro = "江西";
			break;
		case 17:
			sPro = "河北";
			break;
		case 18:
			sPro = "河南";
			break;
		case 19:
			sPro = "浙江";
			break;
		case 20:
			sPro = "海南";
			break;
		case 21:
			sPro = "湖北";
			break;
		case 22:
			sPro = "湖南";
			break;
		case 23:
			sPro = "甘肃";
			break;
		case 24:
			sPro = "福建";
			break;
		case 25:
			sPro = "西藏";
			break;
		case 26:
			sPro = "贵州";
			break;
		case 27:
			sPro = "辽宁";
			break;
		case 28:
			sPro = "重庆";
			break;
		case 29:
			sPro = "陕西";
			break;
		case 30:
			sPro = "青海";
			break;
		case 31:
			sPro = "黑龙江";
			break;
		}
		return sPro;
	}

	public static String getSPro(String iPro) {
		return getSPro(Integer.valueOf(iPro));
	}
	public static void saveMsg(String fileName,String msg) {
		try {
			File file = null;
			String osName = System.getProperty("os.name");
			if (osName != null && osName.equals("Linux")) {
				file = new File("/root/"+fileName);
			} else {
				file = new File("E:\\", fileName);
			}
			FileOutputStream out = new FileOutputStream(file, true);
			out.write((msg+"\r\n").getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
