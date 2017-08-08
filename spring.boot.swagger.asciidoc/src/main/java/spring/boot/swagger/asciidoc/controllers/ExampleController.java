package spring.boot.swagger.asciidoc.controllers;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import spring.boot.swagger.asciidoc.model.ExampleEntity;

@RestController
@RequestMapping("/Examples")
@Api(value = "Rest for example", description = "Rest for example entities")
public class ExampleController {
	
	@ApiOperation(value = "Get list of example entities", notes = "Get all the  example entities from the server")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<ExampleEntity> get() {
		return Arrays.asList(new ExampleEntity(0, "exampleA"), new ExampleEntity(1, "exampleB"), new ExampleEntity(2, "exampleC"));
	}

	@ApiOperation(value = "Get an example entity by its id", notes = "Get an example entity by its id, or throw exception if non is found")
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = "application/json")
	public ExampleEntity get(
			@ApiParam(value = "The example entitiy id", required = true, defaultValue = "1") @PathVariable("id")  String id) 
			throws Exception {
		return new ExampleEntity(0, "exampleA");
	}
	
	@ApiOperation(value = "Create an example entity", notes = "Create a new example entity")
	@RequestMapping(method=RequestMethod.POST, produces = "application/json")
	public ExampleEntity create(@RequestBody ExampleEntity example) throws Exception {
		return example;
	}
	
	@ApiOperation(value = "Update an existing example entity", notes = "Update an existing example entity")
	@RequestMapping(method=RequestMethod.PUT, produces = "application/json")
	public ExampleEntity update (@RequestBody ExampleEntity example) throws Exception {
		return example;
	}
	
	@ApiOperation(value = "Delete an existing example entity", notes = "Delete an existing example entity")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = "application/json")
	public void delete(@PathVariable Object id) throws Exception {
	}
}