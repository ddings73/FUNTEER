package com.yam.funteer.common.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController @Api(tags = {"공통 코드"})
@RequiredArgsConstructor
public class TypeController {

	private final ObjectMapper objectMapper;

	@GetMapping("/type")
	@ApiOperation(value = "공통코드 목록")
	public ResponseEntity<Map<String, List<TypeValue>>> initPostType(){
		Map<String, List<TypeValue>> types = new HashMap<>();
		types.put("Post_Type", toEnumValues(PostType.class));
		types.put("Target_Money_Type", toEnumValues(TargetMoneyType.class));
		types.put("User_Type", toEnumValues(UserType.class));
		return ResponseEntity.ok(types);
	}

	private List<TypeValue> toEnumValues(Class<? extends TypeModel> e){
		return Arrays.stream(e.getEnumConstants())
			.map(TypeValue::new)
			.collect(Collectors.toList());
	}
}
