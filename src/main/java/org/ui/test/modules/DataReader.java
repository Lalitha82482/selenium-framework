package org.ui.test.modules;

import org.apache.poi.ss.usermodel.*;
import java.io.*;


public class DataReader {

	private String fileName;
	private final Workbook workbook;

	public DataReader() {
		try{
			File file = new File(".\\src\\test\\resources\\DataSheet.xlsx");
			this.fileName=file.getAbsolutePath();
			FileInputStream excelFile = new FileInputStream(new File(this.fileName));
			this.workbook = WorkbookFactory.create(excelFile);
		}catch (Exception ex){
			throw new RuntimeException("File not found in ExcelReaderWriter.class");
		}
	}

	public String readConfigValue(String fieldName)
	{
		try {
			Sheet sh= workbook.getSheet("config");
			DataFormatter df = new DataFormatter();			
			int row = sh.getLastRowNum();			
			for(int i=1;i<=row;i++)
			{
				if(df.formatCellValue(sh.getRow(i).getCell(0)).equals(fieldName))
				{
					return df.formatCellValue(sh.getRow(i).getCell(1));
				}
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public String readLocatorValue(String fieldName)
	{
		try {
			Sheet sh= workbook.getSheet("testdata");
			DataFormatter df = new DataFormatter();			
			int row = sh.getLastRowNum();			
			for(int i=1;i<=row;i++)
			{
				if(df.formatCellValue(sh.getRow(i).getCell(0)).equals(fieldName))
				{
					return df.formatCellValue(sh.getRow(i).getCell(1));
				}
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}	
	
	public String readDataValue(String fieldName)
	{
		try {
			Sheet sh= workbook.getSheet("testdata");
			DataFormatter df = new DataFormatter();			
			int row = sh.getLastRowNum();			
			for(int i=1;i<=row;i++)
			{
				if(df.formatCellValue(sh.getRow(i).getCell(0)).equals(fieldName))
				{
					return df.formatCellValue(sh.getRow(i).getCell(2));
				}
			}			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}	
}