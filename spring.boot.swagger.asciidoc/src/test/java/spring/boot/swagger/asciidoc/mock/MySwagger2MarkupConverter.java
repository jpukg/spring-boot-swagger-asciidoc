package spring.boot.swagger.asciidoc.mock;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;

import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import io.github.robwin.swagger2markup.builder.document.DefinitionsDocument;
import io.github.robwin.swagger2markup.builder.document.OverviewDocument;
import io.github.robwin.swagger2markup.builder.document.PathsDocument;
import io.github.robwin.swagger2markup.config.Swagger2MarkupConfig;
import io.swagger.models.Swagger;

public class MySwagger2MarkupConverter  {
	
    private static final String OVERVIEW_DOCUMENT = "overview";
    private static final String PATHS_DOCUMENT = "paths";
    private static final String DEFINITIONS_DOCUMENT = "definitions";
	
	protected Swagger2MarkupConfig m_config;
	OverviewDocument m_overviewDoc;
	PathsDocument m_pathsDoc;
	DefinitionsDocument m_definitionsDoc;
	
	public MySwagger2MarkupConverter (Swagger2MarkupConverter smc) throws Exception{
		this(getSwaggerConfig(smc));
	}
	public MySwagger2MarkupConverter (Swagger2MarkupConfig config){
		m_config = config;
	}
	
	@SuppressWarnings("unused")
	private static Swagger getSwagger(Swagger2MarkupConverter smc) throws Exception {
		Swagger2MarkupConfig config = getSwaggerConfig(smc);
		Swagger swagger =  config.getSwagger();
		return swagger;
	}

	private static Swagger2MarkupConfig getSwaggerConfig(Swagger2MarkupConverter smc) throws Exception {
		Field f = smc.getClass().getDeclaredField("swagger2MarkupConfig"); //NoSuchFieldException
		f.setAccessible(true);
		Swagger2MarkupConfig config = (Swagger2MarkupConfig) f.get(smc); //IllegalAccessException
		return config;
	}

	public void intoFolder(String directory) throws IOException {
			//OverviewDocument
			m_overviewDoc = new OverviewDocument(m_config);
			m_overviewDoc.build();
			m_overviewDoc.writeToFile(directory, OVERVIEW_DOCUMENT, StandardCharsets.UTF_8);
			
			//PathsDocument: MyPathsDocument
			m_pathsDoc =  new MyPathsDocument(m_config);
			m_pathsDoc.build();
			m_pathsDoc.writeToFile(directory, PATHS_DOCUMENT, StandardCharsets.UTF_8);
			
			//DefinitionsDocument
			m_definitionsDoc = new DefinitionsDocument(m_config, directory);
			m_definitionsDoc.build();
			m_definitionsDoc.writeToFile(directory, DEFINITIONS_DOCUMENT, StandardCharsets.UTF_8);
	 }

}
