package demo.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

public class ReadExcelReqDb {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
		String root =System.getProperty("user.dir");
		String path = root+File.separator+"src"+File.separator+"demo"+File.separator+"excel";
		File file = new File(path,"商用包体20161114.xlsx");
		 try {
	            List<List<String>> sheetList = ExcelUtils.getSheetListXSSF(file,2, 7);
	            for (int i = 0; i < sheetList.size(); i++) {

	            	String consumercode = sheetList.get(i).get(2);
	            	Integer price = Double.valueOf(sheetList.get(i).get(4)).intValue();
	            	if(price!= null && StringUtils.isNotBlank(consumercode) && price >=100){
	            		int realPrice =price/100;
	            		System.out.println("case \""+consumercode+"\":return "+realPrice+";");
	            	}
				}
	            //System.out.println(map);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
