package com.data.source;

/**
 * @author Tran Viet Duc
 * 
 * @version 0.1
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.selenium.common.Global;

public class ExcelData {

	protected static XSSFSheet sheet;
	protected static XSSFWorkbook workbook;
	// private static Map<String, Integer> mapTestCol = new HashMap<String,Integer>();

	private static String SHEET_DATA = "Data";
	
	private static String SHEET_DATA_1="Data1";

	private static XSSFRow row;
	private static XSSFCell cell;
	
	String search;
	
	
//	public static void main(String[] args) throws IOException{
//		
//		loadFile(Global.CurrDataFileName);
//		
//		//System.out.println(getCellValueData("Predix_search"));
//		
//		//readXLSXfile("Predix_search");
//		
//		System.out.println(getRowIndex("Predix_search"));
//		
//		System.out.println(getCellValue("Predix_search"));
//		
//				 }
	
//	public ExcelData(){
//		super();
//		this.search = search;	
//	}

	public static void loadFile(String fileName) throws FileNotFoundException {
		loadFile(fileName, SHEET_DATA);
	}

	public static void loadFile(String fileName, String sheetName)
			throws FileNotFoundException {

		File file = new File(fileName);
		file.canRead();
		FileInputStream fis = new FileInputStream(file);

		try {

			workbook = new XSSFWorkbook(fis);
			//XSSFWorkbook test = new XSSFWorkbook(); 
			sheet = workbook.getSheet(sheetName);
			//fis.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getCellValueData(String fieldName)
			throws FileNotFoundException {
		loadFile(Global.CurrDataFileName);
		
		return getCellValue(fieldName);
		
		
	}
	
	public static String getCellValue(String fieldName) {
		
		String r = "";
		
		int iRow = 0;

		//iRow = findRow(fieldName);

		iRow = getRowIndex(fieldName);
		
		if (iRow > 0) {
			
			r = sheet.getRow(iRow).getCell(1).getStringCellValue();
			
			}
		
	return r;	
	
	}
	
	public static int findRow(int iCol, String searchText) {
		for (Row row : sheet) {
			Cell cell = row.getCell(iCol);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				if (cell.getRichStringCellValue().getString().equals(searchText)) {
					return row.getRowNum();
				}
			}
		}
		return 0;
	}
	
	public static int findCol (int iRow, String searchText){
		
		Row row = sheet.getRow(iRow);
		for (Cell cell : row) {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				if (cell.getRichStringCellValue().getString().contains(searchText)) {
					return cell.getColumnIndex();
				}
			}
		}		
		return 0;
	}
	
	public static int getRowIndex(String search_text){
		
		Iterator<Row> itr = sheet.iterator();

		while (itr.hasNext())
		{
			Row row = itr.next();
			
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while (cellIterator.hasNext())
			{	
				Cell cell = cellIterator.next();
		
				if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				{
					if (cell.getRichStringCellValue().getString().trim().equals(search_text)) {
						
						//row.getRowNum();
						                     
                        return row.getRowNum();
					}
				}
				else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
			
	}
		return 0;

	/**
	 * public static int findCol(XSSFSheet sheet, int iRow, String searchText) {
	 * Row row = sheet.getRow(iRow); for (Cell cell : row) { if
	 * (cell.getCellType() == Cell.CELL_TYPE_STRING) { if
	 * (cell.getRichStringCellValue().getString() .equals(searchText)) { return
	 * cell.getColumnIndex(); } } } return 0; }
	 **/
	}
	
	public static void writeXLSXfile() throws IOException{
		
		sheet = workbook.createSheet(SHEET_DATA_1);

		//iterating r number of rows
		for (int r=0;r < 5; r++ )
		{
			row = sheet.createRow(r);

			//iterating c number of columns
			for (int c=0;c < 5; c++ )
			{
				cell = row.createCell(c);
	
				cell.setCellValue("Cell "+r+" "+c);
			}
		}

		FileOutputStream fileOut = new FileOutputStream(Global.CurrDataFileName);

		//write this workbook to an Outputstream.
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
}