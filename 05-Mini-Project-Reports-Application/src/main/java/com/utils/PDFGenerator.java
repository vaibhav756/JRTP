package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class PDFGenerator {
	
	public boolean generatePdfFile(HttpServletResponse response,List<CitizenPlan> citizendata)
	{
				try {
					//Create Document with particular size.
					Document document=new Document(PageSize.A4);
					
					//Create PdfWriter instance and attach document object with response
					PdfWriter.getInstance(document, response.getOutputStream());
					PdfWriter.getInstance(document, new FileOutputStream(new File("CitizenPlan.pdf")));
					
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
					
					//Define how many columns required for PDF Table.
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
