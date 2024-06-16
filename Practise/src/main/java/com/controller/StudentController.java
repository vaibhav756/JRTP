

package com.controller;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Controller
public class StudentController {

	@Autowired
	private JavaMailSender mailsender;
	
	@GetMapping("/")
	private String index()
	{
		return "index";
	}
	
	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response)
	{
		Workbook workbook=new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Student Details");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Student Id");
		header.createCell(1).setCellValue("Student Name");
		header.createCell(2).setCellValue("Student Roll No");
		
		Row row1 = sheet.createRow(1);
		row1.createCell(0).setCellValue("101");
		row1.createCell(1).setCellValue("Vaibhav Yadav");
		row1.createCell(2).setCellValue("62");
		
		Row row2 = sheet.createRow(2);
		row2.createCell(0).setCellValue("202");
		row2.createCell(1).setCellValue("Rohan Vele");
		row2.createCell(2).setCellValue("11");
		
		try
		{
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			workbook.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition","attachment;filename=StudentReport.xlsx");
	}
	
	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse response) throws Exception
	{
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=Student.pdf");
		
		Document document=new Document(PageSize.A4);
		
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setSize(30);
		
		Paragraph para=new Paragraph("Student Details",font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(para);
		
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(100f);
		table.addCell("Student Id");
		table.addCell("Student Name");
		table.addCell("Student RollNo");
		
		table.addCell("101");
		table.addCell("Vaibhav Yadav");
		table.addCell("62");
		
		document.add(table);
		document.close();
		
	}
	
	@GetMapping("/sendemail")
	public void sendEmail() throws MessagingException
	{
		String subject="Sending mail for practise.";
		String body="<h1>Please find Citizen Report file.</h1>";
		String toAddress="yvaibhav7890@gmail.com";
		
		MimeMessage mimemsg = mailsender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimemsg, true);
		helper.setSubject(subject);
		helper.setText(body,true);
		helper.addTo(toAddress);
		helper.addAttachment("citizenplanpdf", new File("Report.pdf"));
		
		mailsender.send(mimemsg);
		
	}
	
}
