package com.paladin.framework.utils;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.paladin.framework.core.exception.BusinessException;

public class ExcelUtils {

	@SuppressWarnings("resource")
	public static Sheet getSheet(MultipartFile file) throws IOException {
		//获得文件名
		Workbook workbook = null;
		String fileName = file.getOriginalFilename();
		if(fileName.endsWith(FileExtension.XLS)){
			//2003
			workbook = new HSSFWorkbook(file.getInputStream());
		}else if(fileName.endsWith(FileExtension.XLSX)){
			//2007
			workbook = new XSSFWorkbook(file.getInputStream());
		}else{
			throw new BusinessException("不是excel文件");
		}
		Sheet sheet = workbook.getSheetAt(0);
		return sheet;
	}
	
	/**
	 * 获得Cell内容
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
		String value = "";
		if(cell!=null){
			//判断数据类型
			switch(cell.getCellType()){
			   
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				value = "";
			    break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				value = cell.getNumericCellValue()+"";
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = "非法字符";
				break;
			default:
				value = "未知类型";
				break;
			}
		}
		return value.trim();
	}
	
}
