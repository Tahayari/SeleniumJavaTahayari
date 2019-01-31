package utils;

public class excelUtilsDemo {

	public static void main(String[] args) {
		
		String projectPath = System.getProperty("user.dir");
		excelUtils excel = new excelUtils(projectPath+"/excel/Data.xlsx", "Sheet1");
		
		System.out.println(excelUtils.getCellData(0, 0));
		excel.getCellData(1, 0);
	
	}

}
