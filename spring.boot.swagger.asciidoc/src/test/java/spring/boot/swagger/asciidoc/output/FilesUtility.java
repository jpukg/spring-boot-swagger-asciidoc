package spring.boot.swagger.asciidoc.output;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import com.google.common.base.Charsets;

public class FilesUtility {
	
	public File createDir (String strFilePath) throws IOException {
		   String strFullPath =  System.getProperty("user.dir") + "\\" + strFilePath;
	       File dir = new File (strFullPath);
	       FileUtils.deleteDirectory(dir);
	       dir.mkdirs();
	       return dir;
	}
	
	protected File mergeFileByType (File dir, String type, String strOut) throws IOException {
		 StringBuilder sb = new StringBuilder();
		 File outputFile = new File(dir, strOut);
		 FileFilter typeFilter = new WildcardFileFilter(type);
       List<File> lstAdocFiles = Arrays.asList(dir.listFiles(typeFilter));
       for (File inputFile : lstAdocFiles){
      	 String strContents = FileUtils.readFileToString(inputFile);
      	 sb.append(strContents+"\n");
       }
       FileUtils.writeStringToFile(outputFile, sb.toString(), Charsets.UTF_8);
       return outputFile;
	}
	
	public void renameFiles(File dir, Map <String, String> map) throws IOException {
		for (Entry<String, String> pair : map.entrySet()){
			File oldFile = new File (dir, pair.getKey());
			File newFile = new File (dir, pair.getValue());
			FileUtils.moveFile (oldFile, newFile);
		}
	}
}