package com.dom4j;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class DomTestb {
	public static void main(String[] args) throws Exception {
		    String  sign = "1FB8E60D6199CA0F4E8D70BFAD91C08B";
		 	File file =new File("E:\\Wrork4\\day0628\\src\\com\\dom4j","c.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element rootEle = doc.getRootElement();
			String content_sid = rootEle.elementText("content_sid");
			Element video = rootEle.element("video");
			String video_sms_num =video.elementText("sms_num");
			String video_sms =video.elementText("sms");

			Element migu = rootEle.element("migu");
			String migu_sms_num =migu.elementText("sms_num");
			String migu_sms =migu.elementText("sms");

			String content =XMLUtils.getContentByXML(doc);
			System.out.println(content);
			String signmd5 = MD5Utils.getMD5(content+"&key=1q2w3e4r5t6y7u8i9o0pasdfghjklzxc").toUpperCase();
			System.out.println(signmd5);
			System.out.println(sign);
	}
}
