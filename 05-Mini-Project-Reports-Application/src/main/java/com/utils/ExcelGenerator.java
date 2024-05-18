package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.CitizenPlan;
import com.repo.CitizenPlanRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ExcelGenerator {

	@Autowired
	private CitizenPlanRepository repo;
	
	public boolean generateExcelFile(HttpServletResponse response,List<CitizenPlan> citizendata)
	{
		//Workbook is an interface and HSSFWorkbook is an implementation class
		//Workbook is nothing but Excel sheet
		//If we have HSSFWorkbook impl then file extension would be .xls
		//Use for Older version microsoft version 13
		Workbook workbook=new HSSFWorkbook();
		
		
		//If we have XSSFWorkbook impl then file extension would be .xlsx
		//Use for Newer version microsoft version 17
		XSSFWorkbook xssfworkbook = new XSSFWorkbook();
		
		//Sheet is separate excel file for every operation.We can have nth no of sheets in single workbook.
		Sheet sheet = workbook.createSheet("Report Data");
		
		//We need to create row for adding header and data.
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Citizen Id");
		header.createCell(1).setCellValue("Citizen Name");
		header.createCell(2).setCellValue("Plan Name");
		header.createCell(3).setCellValue("Gender");
		header.createCell(4).setCellValue("Plan Status");
		header.createCell(5).setCellValue("Plan StartDate");
		header.createCell(6).setCellValue("Plan EndDate");
		header.createCell(7).setCellValue("BenefitAmt");
		header.createCell(8).setCellValue("Denial Reason");
		header.createCell(9).setCellValue("Termination Date");
		header.createCell(10).setCellValue("Termination Reason");
		
		int rowindex=1;
		for(CitizenPlan plan:citizendata)
		{
			Row datarow = sheet.createRow(rowindex);
			datarow.createCell(0).setCellValue(plan.getCitizenId());
			datarow.createCell(1).setCellValue(plan.getCitizenName());
			datarow.createCell(2).setCellValue(plan.getPlanName());
			datarow.createCell(3).setCellValue(plan.getGender());
			datarow.createCell(4).setCellValue(plan.getPlanStatus());
			datarow.createCell(5).setCellValue(getObjData(plan.getPlanStartDate()));
			datarow.createCell(6).setCellValue(getObjData(plan.getPlanEndDate()));
			datarow.createCell(7).setCellValue(getObjData(plan.getBenefitAmt()));
			datarow.createCell(8).setCellValue(getObjData(plan.getDenialReason()));
			datarow.createCell(9).setCellValue(getObjData(plan.getTerminationDate()));
			datarow.createCell(10).setCellValue(getObjData(plan.getTerminationReason()));
			
			rowindex++;
		}
		
		try
		{
			ServletOutputStream responseoutstream = response.getOutputStream();
			workbook.write(responseoutstream);
			responseoutstream.close();
			File file=new File("Report.xls");
			FileOutputStream fos=new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();
		}catch(Exception e)
		{
			log.error("Error occured while storing data in Report.xls");
			e.printStackTrace();
		}
		return true;
	}

	public String getObjData(Object obj)
	{
		if(null==obj)
		return "";
		else
		return obj.toString();
	}
	
}
