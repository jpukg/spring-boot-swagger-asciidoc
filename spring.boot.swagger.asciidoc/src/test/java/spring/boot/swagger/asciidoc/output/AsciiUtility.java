package spring.boot.swagger.asciidoc.output;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter.Builder;
import spring.boot.swagger.asciidoc.mock.MySwagger2MarkupConverter;
public class AsciiUtility  {
	
	protected DocFilesPaths m_paths = null;
	protected FilesUtility m_filesUtility = null;
	
	public AsciiUtility (FilesUtility filesUtility, DocFilesPaths paths){
		m_paths = paths;
		m_filesUtility = filesUtility;
	}
	
	public void create (String strPath, String swaggerJson) throws Exception {
		create (m_filesUtility.createDir(strPath), swaggerJson); 
	}

	public void create (File dir, String swaggerJson) throws Exception {
	   jsonToAsciiDocs(dir, swaggerJson);
	   File asciiDoc = mergeAsciiDocs(dir);
	   convertAsciiToPdf  (dir, asciiDoc);
	   convertAsciiToHtml (dir, asciiDoc);
	}
	
	protected void jsonToAsciiDocs(File dir, String swaggerJson)  throws Exception {
		   Builder builder = Swagger2MarkupConverter.fromString(swaggerJson);
		   Swagger2MarkupConverter smc = builder.build();
		   /*
		    This is an ugly temporary work around that I had to use to fix a bug in asciidoctorj. 
		    io.github.robwin.swagger2markup.Swagger2MarkupConverter
		    io.github.robwin.swagger2markup.builder.document.PathsDocument
		    io.github.robwin.swagger2markup.utils.ParameterUtil
		    The method ParameterUtil.getDefaultValue that is called from
		    PathsDocument (that is used by Swagger2MarkupConverter) throw
		    an unexplained exception. I could not change the source code
		    so I replace it with my own code. Hope to remove it soon. 
		    */
		// smc.intoFolder(dir.getAbsolutePath());
		   MySwagger2MarkupConverter msmc = new MySwagger2MarkupConverter(smc);
		   msmc.intoFolder(dir.getAbsolutePath());  
	}
	
	 protected File mergeAsciiDocs(File dir) throws IOException {
		Map <String, String>  map = m_paths.getMapOfFilesNames();
		m_filesUtility.renameFiles(dir, map);
		return m_filesUtility.mergeFileByType(dir, "*.adoc", m_paths.getNameOfAscii());
	}

	protected void convertAsciiToPdf(File dir, File inputFile) {
		File  outputFile = new File(dir, m_paths.getNameOfPDF());
		convertAsciiToFormat(inputFile, outputFile, "pdf");
	}
	
	protected void convertAsciiToHtml(File dir, File inputFile) {
		File outputFile = new File(dir, m_paths.getNameOfHtml());
		convertAsciiToFormat(inputFile, outputFile, "html");
	}
	
	protected void convertAsciiToFormat(File inputFile, File outputFile, String format) {
    	Asciidoctor asciidoctor = Asciidoctor.Factory.create();
        Options options = new Options();
    	options.setBackend(format);
    	options.setHeaderFooter(true);
    	options.setToFile(outputFile.getAbsolutePath());
    	asciidoctor.renderFile(inputFile, options);
	}
}