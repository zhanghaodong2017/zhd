package com.dom4j;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DomTest {
	public static void main(String[] args) throws Exception {
		 SAXReader reader=new SAXReader();    //使用SAXReader方式读取XML文件
		 File file =new File("E:\\JoymetecStudio_work\\day0628\\src\\com\\dom4j","a.xml");
         Document doc=reader.read(file);        //加载XML配置文件，得到Document对象
         Element root=doc.getRootElement();  //获得根节点

         //取得某节点的单个子节点
         System.out.println("-------取得某节点的单个子节点开始----------");
         Element value=root.element("orderid");
         System.out.println(value.getText());

         java.sql.Date date =new java.sql.Date(116, 5, 38);
         System.out.println(date.toString());
	}
}
