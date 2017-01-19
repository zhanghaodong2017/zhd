package com.dom4j;

import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class CreateXML {
	public static void main(String[] args) throws IOException {
		  Element root = DocumentHelper.createElement("Person");
	        Document document = DocumentHelper.createDocument(root);

	        //给根节点添加孩子节点
	        Element element1 = root.addElement("学生");
	        element1.addElement("姓名").addText("小章");
	        element1.addElement("年龄").addText("21");

	        Element element2 = root.addElement("学生");
	        element2.addElement("姓名").addText("小红");
//	        element2.addElement("姓名").addAttribute("婚姻", "单身").addText("小红").addElement("爱好").addText("唱歌");
	        element2.addElement("年龄").addText("22");

	        System.out.println(document.asXML());
	        Element root2 = DocumentHelper.createElement("root");
	        Document document2 = DocumentHelper.createDocument(root2);
	        root2.addElement("result").addText("2031");
	        root2.addElement("resultDescription").addText("success");
	        System.out.println(document2.asXML());

	        //把生成的xml文档存放在硬盘上  true代表是否换行
	      /*  OutputFormat format = new OutputFormat(" ",true);
	        format.setEncoding("UTF-8");//设置编码格式
	        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("D:\\Person.xml"),format);

	        xmlWriter.write(document);
	        xmlWriter.close();*/
	        System.out.println("2_1234567_abdasb123123".hashCode());
	        System.out.println(Long.toString(System.currentTimeMillis(), 36).substring(7));
	}
}
