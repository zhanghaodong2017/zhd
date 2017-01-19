package com.dom4j;

import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class CreateXML3 {
	public static void main(String[] args) throws IOException {
		Element root = DocumentHelper.createElement("root");
		Document document = DocumentHelper.createDocument(root);
		root.addElement("result").addText("2031");
		root.addElement("resultDescription");
		root.addElement("extensionInfo");
		System.out.println(document.asXML());
	}
}
