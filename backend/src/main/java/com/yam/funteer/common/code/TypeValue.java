package com.yam.funteer.common.code;

import lombok.Getter;

@Getter
public class TypeValue {
	private String key;
	private String description;

	public TypeValue(TypeModel typeModel){
		this.key = typeModel.getKey();
		this.description = typeModel.getDescription();
	}
}

