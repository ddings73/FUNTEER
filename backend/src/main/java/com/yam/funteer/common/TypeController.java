package com.yam.funteer.common;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yam.funteer.funding.entity.TargetMoney;
import com.yam.funteer.post.PostType;
import com.yam.funteer.post.TargetMoneyType;
import com.yam.funteer.user.UserType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController @Api(tags = {"공통 코드"})
@RequestMapping("/type")
public class TypeController {

	@GetMapping("/post")
	@ApiOperation(value = "게시글 타입", notes =
		"DONATION_ACTIVE(\"모금_진행\"),\n"
		+ "DONATION_CLOSE(\"모금_종료\"),\n"
		+ "FUNDING_WAIT(\"펀딩_승인_대기\"),\n"
		+ "FUNDING_REJECT(\"펀딩_승인_거부\"),\n"
		+ "FUNDING_ACCEPT(\"펀딩_승인_완료\"),\n"
		+ "FUNDING_IN_PROGRESS(\"펀딩_진행중\"),\n"
		+ "FUNDING_EXTEND(\"펀딩_연장\"),\n"
		+ "FUNDING_COMPLETE(\"펀딩_완료\"),\n"
		+ "FUNDING_FAIL(\"펀딩_실패\"),\n"
		+ "REPORT_WAIT(\"보고서_승인대기\"),\n"
		+ "REPORT_ACCEPT(\"보고서_승인완료\"),\n"
		+ "REPORT_REJECT(\"보고서_승인거부\")")
	public ResponseEntity<List<PostType>> initPostType(){
		return ResponseEntity.ok(List.of(PostType.values()));
	}

	@GetMapping("/targetmoney")
	@ApiOperation(value = "펀딩 목표금액 타입", notes =
		"LEVEL_ONE(\"1단계\"),\n"
		+ "LEVEL_TWO(\"2단계\"),\n"
		+ "LEVEL_THREE(\"3단계\")")
	public ResponseEntity<List<TargetMoneyType>> initTargetMoneyType(){
		return ResponseEntity.ok(List.of(TargetMoneyType.values()));
	}

	@GetMapping("/user")
	@ApiOperation(value = "유저 타입", notes =
		"NORMAL(\"일반\"),\n"
		+ "KAKAO(\"카카오\"),\n"
		+ "TEAM_AWAIT(\"단체_대기\"),\n"
		+ "TEAM(\"단체_승인\"),\n"
		+ "NORMAL_RESIGN(\"일반_탈퇴\"),\n"
		+ "TEAM_RESIGN(\"단체_탈퇴\"),\n"
		+ "ADMIN(\"관리자\")")
	public ResponseEntity<List<UserType>> initUserType(){
		return ResponseEntity.ok(List.of(UserType.values()));
	}
}
