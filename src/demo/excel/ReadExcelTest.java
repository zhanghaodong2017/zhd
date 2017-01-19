package demo.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

public class ReadExcelTest {

	public static void main(String[] args) {
		 Map<Integer, List<String>> map= new TreeMap<>(new CompareMap());
		//System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
		String root =System.getProperty("user.dir");
		String path = root+File.separator+"src"+File.separator+"demo"+File.separator+"excel";
		File file = new File(path,"商用包体20161114.xlsx");
		 try {
	            List<List<String>> sheetList = ExcelUtils.getSheetListXSSF(file,2, 7);
	        	String chid = sheetList.get(0).get(1);
            	System.out.println("String chid = \""+Double.valueOf(chid).intValue()+"\";");
            	System.out.println("String price = params.get(\"price\");");
            	System.out.println("String consumercode = \"\";");
	            for (int i = 0; i < sheetList.size(); i++) {

	            	String consumercode = sheetList.get(i).get(2);
	            	Integer price = Double.valueOf(sheetList.get(i).get(4)).intValue();
	            	if(price!= null && StringUtils.isNotBlank(consumercode) && price >=100){
	            		int realPrice =price/100;
	            		List<String> codeList = map.get(realPrice);
	            		if(codeList==null){
	            			codeList = new ArrayList<>();
	            		}
	            		codeList.add(consumercode);
	            		map.put(realPrice, codeList);
	            	}
				}
	            //System.out.println(map);
	            //生成代码
	            Set<Integer> keys =  map.keySet();
				for (Integer price : keys) {
					List<String> codeList = map.get(price);
					if(codeList.size() >1){
						StringBuffer consumer = new StringBuffer();
						consumer.append("String[] consumercode_");
						consumer.append(price);
						consumer.append("= new String[]{");
						//= new String[]{"006103733008","006103733002"};");
						for (int i = 0; i < codeList.size(); i++) {
							if(i>0){
								consumer.append(",");
							}
							consumer.append("\"");
							consumer.append(codeList.get(i));
							consumer.append("\"");
						}
						consumer.append("};");
						System.out.println(consumer.toString());
					}

				}
				System.out.println("Random rand = new Random();");
				System.out.println("switch (price) {");
				for (Integer price : keys) {
					List<String> codeList = map.get(price);
					System.out.println("case \""+price+"\":");
					if(codeList.size() >1){
						System.out.println("   consumercode = consumercode_"+price+"[rand.nextInt(consumercode_"+price+".length)];");
					}else{
						System.out.println("   consumercode = \""+codeList.get(0)+"\";");
					}
					System.out.println("   break;");

				}
				System.out.println("}");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
