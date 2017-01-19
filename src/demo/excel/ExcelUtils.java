package demo.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	 public static String getValue(Cell hssfCell) {
         if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
             // 返回布尔类型的值
             return String.valueOf(hssfCell.getBooleanCellValue());
         } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
             // 返回数值类型的值
             return String.valueOf(hssfCell.getNumericCellValue());
         } else {
             // 返回字符串类型的值
             return String.valueOf(hssfCell.getStringCellValue());
         }
     }

	 public static List<List<String>> getSheetListXSSF(File file,int sheetAt,int columnLen) throws FileNotFoundException, IOException{
		 XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		 XSSFSheet sheet = workbook.getSheetAt(sheetAt);
		 List<List<String>> sheetList = new ArrayList<>();
		 for (Row row : sheet) {
     		if(row!=null){
     			List<String> rowList = new ArrayList<>();
     			for (int i = 0; i < columnLen; i++) {
     				if(row.getCell(i)!=null){
     					rowList.add(getValue(row.getCell(i)));
     				}
				}
     			if(rowList.size()>0){
     				sheetList.add(rowList);
     			}
     		}
     	}


		return sheetList;

	 }

	 public static List<List<String>> getSheetListHSSF(File file,int sheetAt,int columnLen) throws FileNotFoundException, IOException{
		 HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
		 HSSFSheet sheet = workbook.getSheetAt(sheetAt);
		 List<List<String>> sheetList = new ArrayList<>();
		 for (Row row : sheet) {
     		if(row!=null){
     			List<String> rowList = new ArrayList<>();
     			for (int i = 0; i < columnLen; i++) {
     				if(row.getCell(i)!=null){
     					rowList.add(getValue(row.getCell(i)));
     				}
				}
     			if(rowList.size()>0){
     				sheetList.add(rowList);
     			}
     		}
     	}


		return sheetList;

	 }
}

