package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils {

	static String projectPath ;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	//constructor
	public excelUtils(String excelPath,String sheetName){

		try{
			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet(sheetName);
		}catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getStackTrace());
		}
	}

	public static int getRowCount(){

		return sheet.getPhysicalNumberOfRows();

	}

	public static String getCellData(int rowNum,int colNum){

		String cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		return cellData;			

	}

}
