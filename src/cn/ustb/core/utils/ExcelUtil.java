package cn.ustb.core.utils;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import cn.ustb.nsfw.user.entity.User;

public class ExcelUtil {
	
	
	public static void exportExcel(List<User> userList,ServletOutputStream outputStream){
		//1、创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.1、创建合并单元格对象
		CellRangeAddress rangeAddress = new CellRangeAddress(0,0,0,4);
		//1.2、创建头标题行样式并创建字体(16号，加粗)
		HSSFCellStyle style1 = createStyle(workbook,(short)16, true);
		//1.3、创列标题样式（13号，加粗）
		HSSFCellStyle style2 = createStyle(workbook,(short)13, true);
		//1、4 正文样式12号不加粗
		HSSFCellStyle style3 = createStyle(workbook, (short)12, false);
		
		//
		//2、创建工作表
		HSSFSheet sheet = workbook.createSheet();
		//2.1 设置默认列宽
		sheet.setDefaultColumnWidth(15);
		
		//2.1、加载合并单元格对象
		sheet.addMergedRegion(rangeAddress);
		//
		//3、创建行
		//3.1、创建头标题行并写入头标题
		HSSFRow row1 = sheet.createRow(0);
		HSSFCell cell1 = row1.createCell(0);
		cell1.setCellValue("用户列表");
		style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style1.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
		cell1.setCellStyle(style1);
		
		//3.2、创建列标题并写入列标题
		HSSFRow row2 = sheet.createRow(1);
		String[] titles = {"用户名","账号","部门","性别","邮箱"};
		for(int i = 0;i<titles.length;i++){
			HSSFCell cell = row2.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style2);
		}
		//4、创建单元格，写入用户数据到excel
		if(userList != null ){
			for(int i = 0;i<userList.size();i++){
				HSSFRow row = sheet.createRow(i+2);
				User user = userList.get(i);
				row.createCell(0).setCellValue(user.getName());
				row.createCell(1).setCellValue(user.getAccount());
				row.createCell(2).setCellValue(user.getDept());
				row.createCell(3).setCellValue(user.isGender()? "男":"女");
				row.createCell(4).setCellValue(user.getEmail());
				row.setRowStyle(style3);
			}
		}
		//5、输出
		try {
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * //可以抽取style创建方法，传入workbook和字号，加粗是否（默认居中）
	 * @param workbook 
	 * @param size 字号
	 * @param isBold 是否加粗
	 * @return 样式
	 */
	
	public static HSSFCellStyle createStyle(HSSFWorkbook workbook,short size,boolean isBold){
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(size);
		if(isBold){
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		}
		style.setFont(font);//字体也是样式的一种，最后统一通过style作用于workbook
		return style;
	}
}
