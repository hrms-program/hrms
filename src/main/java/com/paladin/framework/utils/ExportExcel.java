package com.paladin.framework.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {
	
	/**
	 * 
	 * 
	 * @param fileName 文件名
	 * @param headers 表格属性列名数组
	 * @param dataset 需要显示的数据集合
	 * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook exportExcel(HttpServletRequest request, HttpServletResponse response, String fileName,
	        List<String> headers, List<Object[]> dataset, String pattern) throws IOException {
	    // 设置请求
	    response.setHeader("Content-disposition",
	            "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
	    response.setContentType("application/msexcel;charset=UTF-8");
	    // 创建一个工作薄
	    HSSFWorkbook workbook = new HSSFWorkbook();
	    // 生成一个表格
	    HSSFSheet sheet = workbook.createSheet("表单导出");
	    // 产生表格标题行
	    HSSFRow row = sheet.createRow(0);
	    for (short i = 0; i < headers.size(); i++) {
	        HSSFCell cell = row.createCell(i);
	        HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
	        cell.setCellValue(text);
	    }
	    // 遍历集合数据，产生数据行
	    Iterator<Object[]> it = dataset.iterator();
	    int index = 0;
	    while (it.hasNext()) {
	        index++;
	        // 从第1行开始创建
	        row = sheet.createRow(index);
	        Object[] obj = (Object[]) it.next();
	        for (short i = 0; i < obj.length; i++) {
	            HSSFCell cell = row.createCell(i);
	            Object value = obj[i];
	            String textValue = null;
	            if (!"".equals(value) && value != null) {
	                if (value instanceof Integer) {
	                    int intValue = (Integer) value;
	                    cell.setCellValue(intValue);
	                } else if (value instanceof Float) {
	                    float fValue = (Float) value;
	                    cell.setCellValue(fValue);
	                } else if (value instanceof Double) {
	                    double dValue = (Double) value;
	                    cell.setCellValue(dValue);
	                } else if (value instanceof Long) {
	                    long longValue = (Long) value;
	                    cell.setCellValue(longValue);
	                } else if (value instanceof Date) {
	                    Date date = (Date) value;
	                    if (null == pattern || pattern.equals("")) {
	                        pattern = "yyyy-MM-dd";
	                    }
	                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	                    textValue = sdf.format(date);
	                    cell.setCellValue(textValue);
	                } else {
	                    // 其它数据类型都当作字符串简单处理
	                    textValue = value.toString();
	                    // 设置单元格的值
	                    cell.setCellValue(textValue);
	                }
	            } else {
	                cell.setCellValue("");
	            }
	        }
	    }
	    // 让列宽随着导出的列长自动适应
	    for (int colNum = 0; colNum < headers.size(); colNum++) {
	        int columnWidth = sheet.getColumnWidth(colNum) / 256;
	        for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
	            HSSFRow currentRow;
	            // 当前行未被使用过
	            if (sheet.getRow(rowNum) == null) {
	                currentRow = sheet.createRow(rowNum);
	            } else {
	                currentRow = sheet.getRow(rowNum);
	            }
	            if (currentRow.getCell(colNum) != null) {
	                HSSFCell currentCell = currentRow.getCell(colNum);
	                if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
	                    int length = currentCell.getStringCellValue() != null
	                            ? currentCell.getStringCellValue().getBytes().length : 10;
	                    if (columnWidth < length) {
	                        columnWidth = length;
	                    }
	                }
	            }
	        }
	        if (colNum == 0) {
	            sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
	        } else {
	            sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
	        }
	    }
	    OutputStream outputStream = response.getOutputStream();// 打开流
	    workbook.write(outputStream);// HSSFWorkbook写入流
	    workbook.close();// HSSFWorkbook关闭
	    outputStream.flush();// 刷新流
	    outputStream.close();// 关闭流
	    return workbook;
	}
}
