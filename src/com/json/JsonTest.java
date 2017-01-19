package com.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.xpath.DefaultXPath;

import com.htest.HttpUtil;

public class JsonTest {
	public static void main(String[] args) throws Exception {
		File file = new File(System.getProperty("user.dir")
				+ "\\src\\com\\json", "json.txt");
		System.out.println(file.getPath());
		FileInputStream input = new FileInputStream(file);

		/*SAXReader reader = new SAXReader();
		Document doc = reader.read(input);
		String webId = getHeaderEleText(doc,"webID");
		String traceUniqueID = getHeaderEleText(doc,"traceUniqueID");
		String updateType = getBodyEleText(doc,"updateType");
		System.out.println(webId+traceUniqueID+updateType);*/
		HttpUtil.doPostByString("http://localhost:8080/CallbackServer/callback/FHBJHY", IOUtils.toString(input));

		String xml = IOUtils.toString(input);
		String webId = getPattern(xml, "ns1:webID");
		String traceUniqueID = getPattern(xml, "ns1:traceUniqueID");
		String updateType = getPattern(xml, "ns2:updateType");
		System.out.println(webId+traceUniqueID+updateType);

	}


	private static String getHeaderEleText(Document doc,String ele) {
		DefaultXPath xpath = new DefaultXPath("//ns1:"+ele);
		xpath.setNamespaceURIs(Collections.singletonMap("ns1" , "http://www.huawei.com.cn/schema/common/v2_1"));

		List list = xpath.selectNodes(doc);
		String text = "LM";
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Element node = (Element) iterator.next();
			text = node.getText();
		}
		return text;
	}
	private static String getBodyEleText(Document doc,String ele) {
		DefaultXPath xpath = new DefaultXPath("//ns2:"+ele);
		xpath.setNamespaceURIs(Collections.singletonMap("ns2" , "http://www.csapi.org/schema/parlayx/data/sync/v1_0/local"));

		List list = xpath.selectNodes(doc);
		String text = "LM";
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			Element node = (Element) iterator.next();
			text = node.getText();
		}
		return text;
	}

	public static String getPattern(String xml ,String ele){
		String start = "<"+ele+">";
		String end = "</"+ele+">";
		String text = "";
		if(xml.indexOf(start) >0 && xml.indexOf(end) >0){
			text =xml.substring(xml.indexOf(start)+start.length(), xml.indexOf(end));
		}
		return text;
	}

}
