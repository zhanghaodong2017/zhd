package com.dom4j;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.Element;

public class XMLUtils {
	public static String getContentByXML(Document doc) throws IOException {
		Element root = doc.getRootElement();
		List<Element> childs = root.elements();
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		for (Element element : childs) {
			String param= element.getName();
			if(!param.equals("sign")){
				paramsMap.put(element.getName(), element.getText());
			}

		}

		Iterator<String> it = paramsMap.keySet().iterator();
		String strsign = "";
		while (it.hasNext()) {
			String key =  it.next();
			String value = paramsMap.get(key);
			strsign = strsign + key + "=" + value + "&";
		}

		String content = strsign.substring(0 , strsign.length()-1);
		return content;
	}
}
