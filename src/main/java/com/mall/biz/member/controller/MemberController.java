package com.mall.biz.member.controller;

import com.mall.biz.member.dto.req.ReqMemberSearchFilter;
import com.mall.biz.member.dto.req.ReqSaveMemberDto;
import com.mall.biz.member.dto.req.ReqUpdateMemberDto;
import com.mall.biz.member.dto.res.ResMemberDto;
import com.mall.biz.member.service.MemberService;
import com.mall.biz.member.dto.res.ResMemberListDto;
import com.mall.biz.sample.dto.res.ResSampleDto;
import com.mall.common.model.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Tag(name="회원", description = "회원 API")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("")
    @Operation(summary = "회원 등록", description = "회원을 등록한다.")
    public SuccessResponse saveMember(@RequestBody @Valid ReqSaveMemberDto reqSaveMemberDto) throws Exception {
        memberService.saveMember(reqSaveMemberDto);
        return new SuccessResponse();
    }

    @GetMapping("")
    @Parameters({
            @Parameter(name = "fromDate", description = "검색시작일 (YYYY-MM-DD)", schema = @Schema(type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "toDate", description = "검색종료일 (YYYY-MM-DD)", schema = @Schema(type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "searchCd", description = "검색조건: ['']전체, [10]회원명, [20]로그인ID", schema = @Schema(allowableValues = { "10", "20" }, type = "String", nullable = true), in = ParameterIn.QUERY),
            @Parameter(name = "searchKeyword", description = "검색어", schema = @Schema(type = "string", maxLength = 9999), in = ParameterIn.QUERY),
            @Parameter(name = "page", description = "페이지번호", schema = @Schema(type = "integer", defaultValue = "0"), in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "페이지당 행 개수", schema = @Schema(type = "integer", defaultValue = "10"), in = ParameterIn.QUERY),
    })
    @Operation(summary = "회원 목록 조회", description = "회원 목록을 전체 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 목록 조회 성공", content = @Content(schema = @Schema(implementation = ResMemberListDto.class)))
    })
    public SuccessResponse searchMemberList(
            @Parameter(hidden = true) @Valid ReqMemberSearchFilter reqMemberSearchFilter,
            @Parameter(hidden = true) Pageable pageable
    ) {
        Page<ResMemberListDto> result = memberService.searchMemberList(reqMemberSearchFilter, pageable);
        return new SuccessResponse(result);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "회원 단건 조회", description = "회원 단건을 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 단건 조회 성공", content = @Content(schema = @Schema(implementation = ResMemberDto.class)))
    })
    public SuccessResponse searchMemberList(@PathVariable(name = "memberId") String memberId) {
        ResMemberDto result = memberService.findMember(memberId);
        return new SuccessResponse(result);
    }

    @PutMapping("")
    @Operation(summary = "회원정보 수정", description = "회원정보를 수정한다.")
    public SuccessResponse updateMember(@RequestBody ReqUpdateMemberDto reqUpdateMemberDto) throws Exception {
        memberService.updateMember(reqUpdateMemberDto);
        return new SuccessResponse();
    }
}
