package com.patchMaker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.patchMaker.dao.GenericEntityDaoImpl;
import com.patchMaker.entity.Patch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service("jasperServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class JasperServiceImpl {
	
	@Autowired
	GenericEntityDaoImpl<Patch> genericDao;
	
	@Transactional
	public OutputStream generatePDFFromJasper(String templatePathWithReport, String generationPath,
			Map<String, Object> parameters) {

		OutputStream os = null;
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(templatePathWithReport);
			Connection con1 = genericDao.getconnection();
			
			//JasperReport jasperReport  = (JasperReport) JRLoader.loadObjectFromFile("D:\\\\JRXML_AND_REPORT\\\\JRXML\\\\releaseNote.jasper");
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con1);			
			// java.awt.Image s=new java.awt.Image();
			// File outDir = new File("D:/report");
			// outDir.mkdirs();

			// JasperExportManager.exportReportToPdfFile(jasperPrint,"D:/report/report.pdf");
			byte[] abr = JasperExportManager.exportReportToPdf(jasperPrint);
			
			os = new FileOutputStream(new File(generationPath));
			
			os.write(abr);

		} catch (Exception e) {
			System.out.printf("PDF : Inside PDFGenerationServiceImpl class : generatePDFFromJasper method>> Inside Exception for report :"+templatePathWithReport.substring(templatePathWithReport.lastIndexOf("/")+1)+" : Exception is :", e);
		}
		return os;
	}

}
