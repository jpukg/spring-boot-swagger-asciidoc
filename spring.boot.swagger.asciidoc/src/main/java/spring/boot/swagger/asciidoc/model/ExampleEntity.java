package spring.boot.swagger.asciidoc.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Example Entity", description = "Example of Rest entity")
public class ExampleEntity {
	
	@ApiModelProperty(required=true, example="123456")
	protected long id;
	
	@ApiModelProperty(required=true, example="Or Bartal")
	protected String name;
	
	public ExampleEntity(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}