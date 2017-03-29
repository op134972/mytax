package cn.ustb.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {

	@Test
	public void write03excel() throws Exception {
		//1、创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//2、操作工作表
		HSSFSheet sheet = workbook.createSheet("helloworld");
		//3、操作行
		HSSFRow row = sheet.createRow(3);//0开始
		//4、操作单元格
		HSSFCell cell = row.createCell(3);
		cell.setCellValue("hello world");
		String fileName = "f:/test.xls";
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fos.close();
	}
	@Test
	public void read03excel() throws Exception{
		String fileName = "f:/test.xls";
		FileInputStream fis = new FileInputStream(fileName);
		//1、读取工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		//2、读取工作表
		HSSFSheet sheet = workbook.getSheetAt(0);//第一个工作表
		//3、读取行
		HSSFRow row = sheet.getRow(3);//第4行
		//4、读取表格
		HSSFCell cell = row.getCell(3);
		System.out.println(cell.getStringCellValue());
		
		workbook.close();
		fis.close();
	}
	@Test
	public void write07excel() throws Exception {
		//1、创建工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//2、操作工作表
		XSSFSheet sheet = workbook.createSheet("helloworld");
		//3、操作行
		XSSFRow row = sheet.createRow(3);//0开始
		//4、操作单元格
		XSSFCell cell = row.createCell(3);
		cell.setCellValue("hello world");
		String fileName = "f:/test.xlsx";
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fos.close();
	}
	@Test
	public void read07excel() throws Exception{
		String fileName = "f:/test.xlsx";
		FileInputStream fis = new FileInputStream(fileName);
		//1、读取工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		//2、读取工作表
		XSSFSheet sheet = workbook.getSheetAt(0);//第一个工作表
		//3、读取行
		XSSFRow row = sheet.getRow(3);//第4行
		//4、读取表格
		XSSFCell cell = row.getCell(3);
		System.out.println(cell.getStringCellValue());
		
		workbook.close();
		fis.close();
	}
	
	@Test
	public void testPlay() throws Exception{
		String fileName = "f:/testPlay.xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("testPlay");
		FileOutputStream fos = new FileOutputStream(fileName);
		for(int i = 0 ;i<1000;i++){
			HSSFRow row = sheet.createRow(i);
			for(int j = 0;j<250;j++){
				row.createCell(j).setCellValue("*******");
			}
		}
		
		workbook.write(fos);
		workbook.close();
		fos.close();
	}
	@Test
	public void testExcelStyle() throws Exception{
		//1、创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1、1 创建合并单元格对象，合并第3行，第三列到第五列
		CellRangeAddress address = new CellRangeAddress(2,2,2,4);
		//1、2 创建单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//1、3 创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
		font.setFontHeightInPoints((short)16);//字体大小为16

		
		//样式加载字体
		style.setFont(font);
		
		//设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillBackgroundColor(HSSFColor.YELLOW.index);
		style.setFillForegroundColor(HSSFColor.RED.index);
		
		//2、操作工作表
		HSSFSheet sheet = workbook.createSheet("helloworld");
		//2、1 加载合并单元格对象
		sheet.addMergedRegion(address);
		
		
		//3、操作行
		HSSFRow row = sheet.createRow(2);//0开始
		
		
		//4、操作单元格
		HSSFCell cell = row.createCell(2);
		
		//4、1加载样式
		cell.setCellStyle(style);
		cell.setCellValue("hello world");
		String fileName = "f:/testStyle.xls";
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		
		workbook.close();
		fos.close();
	}
	
	public void testOut(){
		//1、获取用户列表
		//2、导出excel
		
		//1、创建工作簿
		//2、
		
	}

}
