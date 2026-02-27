package com.orangehrm.utils;

import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.orangehrm.config.Config;

public class ExcelUtil {
	private static XSSFSheet excelWSheet;
	private static XSSFWorkbook excelWBook;

	public static void setExcelFile(String filePath, String sheetName) {
		try {
			FileInputStream excelFile = new FileInputStream(filePath);
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);

		} catch (Exception e) {

			LoggerUtil.logError("Error while opening TestData file: " + e);
		}
	}

	public static String getCellData(int rowNo, int colNo) {
		String cellData = null;

		try {
			FormulaEvaluator evaluator = excelWBook.getCreationHelper().createFormulaEvaluator();
			XSSFCell cell = excelWSheet.getRow(rowNo).getCell(colNo);
			cell.setCellType(CellType.STRING);
			CellValue cellValue = evaluator.evaluate(cell);
			cellData = cellValue.getStringValue();
			if (cellData.isEmpty())
				cellData = "";

		} catch (Exception e) {
			cellData = "";
		}

		return cellData;
	}

	public static int getFirstRowContainsTestCaseId(String testCaseId, int colNo) {
		int rowNo;
		int rowCount = excelWSheet.getLastRowNum();
		for (rowNo = 1; rowNo <= rowCount; rowNo++) {
			if (getCellData(rowNo, colNo).equalsIgnoreCase(testCaseId))
				break;
		}

		return rowNo;
	}

	public static int getLastRowContainsTestCaseId(String testCaseId, int colNo) {
		int rowNo;
		int startIteration;
		int endIteration = 0;

		int rowCount = excelWSheet.getLastRowNum();
		startIteration = getFirstRowContainsTestCaseId(testCaseId, colNo); 

		for (rowNo = startIteration; rowNo <= rowCount; rowNo++) {
			if (getCellData(rowNo, colNo).equalsIgnoreCase(testCaseId))
				endIteration = rowNo;
			else
				break;
		}

		return endIteration;
	}

	public static int getColContains(String colName) {
		int colNo;
		int colCount = excelWSheet.getRow(0).getLastCellNum();

		for (colNo = 0; colNo < colCount; colNo++) {
			if (getCellData(0, colNo).equals(colName)) {
				break;
			}
		}

		return colNo;
	}

	public static String getModuleName(String className) {
		return className.split("_")[0];
	}

	public static String getTestCaseId(String className) {
		return (className.split("_")[1]);
	}

	public static HashMap<Integer, HashMap<String, String>> getTestData(String className) {
		String sheetName;
		String testCaseId;
		int startIteration;
		int endIteration;
		int startCol = 2;
		int totalCols;

		HashMap<String, String> testData = null;
		HashMap<Integer, HashMap<String, String>> testDataIterations = new HashMap<Integer, HashMap<String, String>>();

		sheetName = getModuleName(className); // OrangeHRMTest_4
		testCaseId = getTestCaseId(className);

		setExcelFile(Config.TEST_DATA_FILE_PATH, sheetName);
		startIteration = getFirstRowContainsTestCaseId(testCaseId, 0); //7
		endIteration = getLastRowContainsTestCaseId(testCaseId, 0); //8

		totalCols = excelWSheet.getRow(0).getLastCellNum();

		int iteration = 1;
		for (int i = startIteration; i <= endIteration; i++) {
			if (getCellData(i, 2).equalsIgnoreCase("Y")) {
				testData = new HashMap<String, String>();

				for (int j = startCol; j < totalCols; j++) {
					testData.put(getCellData(startIteration - 1, j), getCellData(i, j));
				}

				testDataIterations.put(iteration, testData);
				iteration = iteration + 1;
			}
		}

		return testDataIterations;
	}

}