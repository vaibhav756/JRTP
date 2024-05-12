package com.serviceimpl;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dto.CitizenDto;
import com.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.repo.CitizenPlanRepository;
import com.service.CitizenService;

import lombok.extern.slf4j.Slf4j;
@Service()
@Slf4j
public class CitizenServiceImpl implements CitizenService {

	@Autowired
	private CitizenPlanRepository repo;
	
	@Override
	public List<String> getPlanNames() {
		return repo.getPlans();
	}
	
	@Override
	public List<String> getPlanStatus() {
		return repo.getStatus();
	}
	
	@Override
	public List<CitizenDto> getCitizenData(CitizenDto dto) {
		log.info("Inside getCitizenData method of CitizenServiceImpl with dto : "+dto);
		CitizenPlan plan=new CitizenPlan();
		List<CitizenDto> dtolist=new ArrayList<CitizenDto>();
		BeanUtils.copyProperties(dto, plan);
		List<CitizenPlan> planlist=new ArrayList<CitizenPlan>();
		Pageable page = Pageable.ofSize(10);
		if(null==dto)
			planlist=repo.findAll(page).getContent();
		else
		{
			planlist=repo.findAll(Example.of(plan),page).getContent();
		}
		planlist.stream().forEach(item->{
			CitizenDto dtocopy=new CitizenDto();
			BeanUtils.copyProperties(item, dtocopy);
			dtolist.add(dtocopy);
			});	
		return dtolist;
	}
	
	@SuppressWarnings("resource")
	@Override
	public boolean exportExcel(CitizenDto dto,HttpServletResponse response) {
		//List<CitizenDto> dtoList = getCitizenData(dto);
		List<CitizenPlan> citizendata = repo.findAll();
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
			//File file=new File("C:\\Java\\citizenreport\\Report.xls");
			//file.createNewFile();
			//FileOutputStream fos=new FileOutputStream(file);
			//workbook.write(fos);			
			workbook.close();
		}catch(Exception e)
		{
			log.error("Error occured while storing data in Report.xls");
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public boolean exportPdf(CitizenDto dto,HttpServletResponse response) {
		//List<CitizenDto> dtoList = getCitizenData(dto);
		List<CitizenPlan> citizendata = repo.findAll();
		try {
			//Create Document with particular size.
			Document document=new Document(PageSize.A4);
			
			//Create PdfWriter instance and attach document object with response
			PdfWriter.getInstance(document, response.getOutputStream());
			
			//To work with PDF we need to open pdf first
			document.open();
			
			//Creating Font and setting Font style and Font size
			Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			//fontTitle.setColor(Color.CYAN);
			fontTitle.setSize(30);
			
			//Create paragraph
			Paragraph p=new Paragraph("Citizen Plans Info",fontTitle);
			p.setAlignment(Paragraph.ALIGN_CENTER);
			//Add this paragraph to ducument.
			document.add(p);
			
			//Difine how many columns required for PDF Table.
			PdfPTable table=new PdfPTable(11);
			
			table.setWidthPercentage(100f);
			
			//Add cell to the PDF
			table.addCell("Citizen Id");
			table.addCell("Citizen Name");
			table.addCell("Plan Name");
			table.addCell("Gender");
			table.addCell("Plan Status");
			table.addCell("Plan StartDate");
			table.addCell("Plan EndDate");
			table.addCell("BenefitAmt");
			table.addCell("Denial Reason");
			table.addCell("Termination Date");
			table.addCell("Termination Reason");
			
			for(CitizenPlan plan:citizendata)
			{
				table.addCell(plan.getCitizenId());
				table.addCell(plan.getCitizenName());
				table.addCell(plan.getPlanName());
				table.addCell(plan.getGender());
				table.addCell(plan.getPlanStatus());
				table.addCell(getObjData(plan.getPlanStartDate()));
				table.addCell(getObjData(plan.getPlanEndDate()));
				table.addCell(getObjData(plan.getBenefitAmt()));
				table.addCell(getObjData(plan.getDenialReason()));
				table.addCell(getObjData(plan.getTerminationDate()));
				table.addCell(getObjData(plan.getTerminationReason()));
			}
			
			//Add final table to document
			document.add(table);
			
			//At the end close the document.
			document.close();
			
		}catch(Exception e)
		{
			log.error("Error occured while creating PDF file.");
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
