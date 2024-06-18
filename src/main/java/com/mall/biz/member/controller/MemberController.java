package com.mall.biz.member.controller;

import com.mall.biz.member.dto.ReqSaveMemberDto;
import com.mall.biz.member.service.MemberService;
import com.mall.common.model.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Tag(name="회원", description = "회원 API")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    @Operation(summary = "회원 등록", description = "회원을 등록한다.")
    public SuccessResponse saveMember(@RequestBody ReqSaveMemberDto reqSaveMemberDto) {
        memberService.saveMember(reqSaveMemberDto);
        return new SuccessResponse();
    }

}
