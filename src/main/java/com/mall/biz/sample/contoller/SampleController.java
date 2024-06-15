package com.mall.biz.sample.contoller;

import com.mall.biz.sample.dto.req.SaveSampleDto;
import com.mall.biz.sample.dto.req.SaveTeamDto;
import com.mall.biz.sample.dto.req.UpdateSampleDto;
import com.mall.biz.sample.dto.res.ReqSampleDto;
import com.mall.common.model.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sample")
@Tag(name="샘플", description = "샘플 API")
public class SampleController {
    @Autowired
    private final SampleService sampleService;

    @GetMapping("")
    @Operation(summary = "샘플 전체조회", description = "모든 샘플을 전체 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "샘플 조회 성공", content = @Content(schema = @Schema(implementation = ReqSampleDto.class)))
    })
    public SuccessResponse searchSampleAll() {
        List<ReqSampleDto> result = sampleService.searchSampleAll();
        return new SuccessResponse(result);
    }

    @PostMapping("")
    @Operation(summary = "샘플 등록", description = "샘플을 등록한다.")
    public SuccessResponse saveSample(@RequestBody SaveSampleDto saveSampleDto) {
        sampleService.saveSample(saveSampleDto);
        return new SuccessResponse();
    }

    @PostMapping("/team")
    @Operation(summary = "팀 등록", description = "팀을 등록한다.")
    public SuccessResponse saveSampleTeam(SaveTeamDto saveTeamDto) {
        sampleService.saveSampleTeam(saveTeamDto);
        return new SuccessResponse();
    }

    @PutMapping("/")
    @Operation(summary = "sample 수정", description = "sample을 수정한다.")
    public SuccessResponse updateSample(@RequestBody UpdateSampleDto updateSampleDto) {
        sampleService.updateSample(updateSampleDto);
        return new SuccessResponse();
    }

}
