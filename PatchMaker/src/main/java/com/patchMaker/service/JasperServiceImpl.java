package com.patchMaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.patchMaker.dao.GenericEntityDaoImpl;
import com.patchMaker.entity.Patch;
import com.patchMaker.util.Values;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

@Service("jasperServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JasperServiceImpl {
	
	@Autowired
	GenericEntityDaoImpl<Patch> genericDao;
	
		
	@Transactional
	public OutputStream generateReleaseNote(Long patchId) {

		OutputStream os = null;
		String templatePathWithReport = Values.JRXML_DIRECTORY + File.separator + Values.RELEASE_NOTE_JRXML_NAME;
		String generationPath = Values.RELEASE_NOTE_PDF_DIRECTORY + File.separator + Values.RELEASE_NOTE_PDF_NAME;
		Map<String, Object> parameters = new HashMap<>();
		System.out.println("Generating report for ID :"+ patchId);
		parameters.put("PATCH_ID", patchId);	
		
		
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(templatePathWithReport);
			Connection con1 = genericDao.getconnection();
			
			//JasperReport jasperReport  = (JasperReport) JRLoader.loadObjectFromFile("D:\\\\JRXML_AND_REPORT\\\\JRXML\\\\releaseNote.jasper");
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con1);			
			// java.awt.Image s=new java.awt.Image();
			// File outDir = new File("D:/report");
			// outDir.mkdirs();

			// JasperExportManager.exportReportToPdfFile(jasperPrint,"D:/report/report.pdf");
			
			//extractPrintImage(Values.RELEASE_NOTE_PDF_DIRECTORY,jasperPrint);			
			
			byte[] abr = JasperExportManager.exportReportToPdf(jasperPrint);
			
			os = new FileOutputStream(new File(generationPath));
			
			os.write(abr);

		} catch (Exception e) {
			System.out.printf("PDF : Inside PDFGenerationServiceImpl class : generatePDFFromJasper method>> Inside Exception for report :"+templatePathWithReport.substring(templatePathWithReport.lastIndexOf("/")+1)+" : Exception is :", e.getStackTrace());
		}
		return os;
	}
	@SuppressWarnings("static-access")
	private void extractPrintImage (String filePath, JasperPrint print){      
		
	     File file = new File(filePath+ File.separator+ "reportPage1.png");  
	     File file2 = new File(filePath+ File.separator+ "reportPage2.png");  
	     
	     OutputStream ouputStream= null;   
	     OutputStream ouputStream2= null;   
	     try{   
	        ouputStream= new FileOutputStream(file);   
	        ouputStream2= new FileOutputStream(file2);  
	        DefaultJasperReportsContext.getInstance();   
	        JasperPrintManager printManager = JasperPrintManager.getInstance(DefaultJasperReportsContext.getInstance());      
	 
	        BufferedImage rendered_image = null;      
	        rendered_image = (BufferedImage)printManager.printPageToImage(print, 0,1.6f); 
	        ImageIO.write(rendered_image, "png", ouputStream);     
	        rendered_image = (BufferedImage)printManager.printPageToImage(print, 1,1.6f); 
	        ImageIO.write(rendered_image, "png", ouputStream2);     
	 
	     }catch(Exception e){       e.printStackTrace();  }    }

}
