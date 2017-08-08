package spring.boot.swagger.asciidoc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import spring.boot.swagger.asciidoc.output.AsciiUtility;
import spring.boot.swagger.asciidoc.output.DocFilesPaths;
import spring.boot.swagger.asciidoc.output.FilesUtility;
import spring.boot.swagger.asciidoc.swagger.SpringSwaggerConfig;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringSwaggerMain.class, SpringSwaggerConfig.class})
@AutoConfigureMockMvc
public class Swagger2MarkupTest {

protected static final String OUTPUT_DIR_PATH = "target/docs/swagger/";
   
	@Autowired
	protected MockMvc mockMvc;

	@Test
	public void createApiDocument() throws Exception {
		String swaggerJson =  getSwaggerJson();	
		DocFilesPaths docFilesPaths = new DocFilesPaths();
		FilesUtility  filesUtility = new FilesUtility();
		AsciiUtility asciiUtility = new AsciiUtility(filesUtility, docFilesPaths);
		asciiUtility.create(OUTPUT_DIR_PATH, swaggerJson);
	}
   
	protected String getSwaggerJson() throws Exception{
		MvcResult mvcResult = this.mockMvc.perform(get("/v2/api-docs")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String swaggerJson = response.getContentAsString();
		return swaggerJson;
	}
}