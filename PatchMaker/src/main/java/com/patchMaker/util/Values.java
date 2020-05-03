package com.patchMaker.util;

import java.io.File;

public class Values {
	
	public final static String BASE_DIRECTORY ="D:" + File.separator + "PatchMakerRoot";
	
	public final static String REPORT_DIRECTORY = BASE_DIRECTORY + File.separator + "JRXML_AND_REPORT";
	
	public final static String JRXML_DIRECTORY = REPORT_DIRECTORY + File.separator + "JRXML";
	
	public final static String RELEASE_NOTE_JRXML_NAME = "releaseNote.jrxml";
	
	public final static String RELEASE_NOTE_PDF_DIRECTORY = REPORT_DIRECTORY + File.separator + "GENERATED_REPORT";
	
	public final static String RELEASE_NOTE_PDF_NAME = "RELEASE_NOTE.pdf";
	
	public final static String BASE_DIR_FOR_CHECKIN_CHKECKOUT = "D:/DEEPALI/HDFC/SVNGA3.5/custom_development/neo_intg/source_code/dbscript/Loan_Movement_Patch/";

	public final static String SVN_DIR = "https://nucnocorpsvn.nucleussoftware.com:18080/svn/neo_implementations/hdfc_neo/branches/dev_hdfc_neo/hdfc_neo_3.0/custom_development/neo_intg/source_code/dbscript/Loan_Movement_Patch/";
}
