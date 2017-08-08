package spring.boot.swagger.asciidoc.output;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DocFilesPaths {
	public static final String OVERVIEW_ASCII_DOC_ORIGINAL_NAME = "overview.adoc";
	public static final String PATHS_ASCII_DOC_ORIGINAL_NAME = "paths.adoc";
	public static final String DEFINITIONS_ASCII_DOC_ORIGINAL_NAME = "definitions.adoc";
	 
	public static final String OVERVIEW_ASCII_DOC_NAME = "1overview.adoc";
	public static final String PATHS_ASCII_DOC_NAME = "3paths.adoc";
	public static final String DEFINITIONS_ASCII_DOC_NAME = "2definitions.adoc";
	 
	public static final String OUTPUT_FILE_NAME = "WikiRestAPI";
	public static final String OUTPUT_FILE_NAME_ASCII = OUTPUT_FILE_NAME + ".adoc";
	public static final String OUTPUT_FILE_NAME_PDF = OUTPUT_FILE_NAME + ".pdf";
	public static final String OUTPUT_FILE_NAME_HTML = OUTPUT_FILE_NAME + ".html";

	public String getNameOfPDF() {
		return OUTPUT_FILE_NAME_PDF;
	}

	public String getNameOfHtml() {
		return OUTPUT_FILE_NAME_HTML;
	}
	
	public String getNameOfAscii() {
		return OUTPUT_FILE_NAME_ASCII;
	}
	
	public Map <String, String> getMapOfFilesNames () throws IOException {
		Map <String, String> map = new HashMap<String, String>();
		map.put(OVERVIEW_ASCII_DOC_ORIGINAL_NAME, OVERVIEW_ASCII_DOC_NAME);
		map.put(PATHS_ASCII_DOC_ORIGINAL_NAME, PATHS_ASCII_DOC_NAME);
		map.put(DEFINITIONS_ASCII_DOC_ORIGINAL_NAME, DEFINITIONS_ASCII_DOC_NAME);
		return map;
	}

	
}
