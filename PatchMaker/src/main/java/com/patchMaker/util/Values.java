package com.patchMaker.util;

import java.io.File;

public class Values {
	
	public static String BASE_DIRECTORY ="D:" + File.separator + "PatchMakerRoot";
	
	public static String REPORT_DIRECTORY = BASE_DIRECTORY + File.separator + "JRXML_AND_REPORT";
	
	public static String JRXML_DIRECTORY = REPORT_DIRECTORY + File.separator + "JRXML";
	
	public static String RELEASE_NOTE_JRXML_NAME = "releaseNote.jrxml";
	
	public static String RELEASE_NOTE_PDF_DIRECTORY = REPORT_DIRECTORY + File.separator + "GENERATED_REPORT";
	
	public static String RELEASE_NOTE_PDF_NAME = "RELEASE_NOTE.pdf";

}
