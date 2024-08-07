package com.mall.biz.sample.contoller;

import com.mall.biz.sample.dto.req.SaveRedisSampleDto;
import com.mall.biz.sample.dto.req.SaveSampleDto;
import com.mall.biz.sample.dto.req.SaveTeamDto;
import com.mall.biz.sample.dto.req.UpdateSampleDto;
import com.mall.biz.sample.dto.res.ResRedisSampleListDto;
import com.mall.biz.sample.dto.res.ResSampleDto;
import com.mall.biz.sample.dto.res.ResSampleRedisDto;
import com.mall.biz.sample.entity.SampleRedis;
import com.mall.biz.sample.service.SampleService;
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
            @ApiResponse(responseCode = "200", description = "샘플 조회 성공", content = @Content(schema = @Schema(implementation = ResSampleDto.class)))
    })
    public SuccessResponse searchSampleAll() {
        List<ResSampleDto> result = sampleService.searchSampleAll();
        return new SuccessResponse(result);
    }

    @GetMapping("/{name}")
    @Operation(summary = "샘플 이름으로 단건조회", description = "이름으로 샘플을 한건 조회한다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "샘플 조회 성공", content = @Content(schema = @Schema(implementation = ResSampleDto.class)))
    })
    public SuccessResponse searchSampleByName(@PathVariable("name") String name) {
        ResSampleDto result = sampleService.searchSampleByName(name);
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

    @DeleteMapping("/{id}")
    @Operation(summary = "sample 삭제", description = "sample을 삭제한다.")
    public SuccessResponse removeSample(@PathVariable("id") Long id) {
        sampleService.removeSample(id);
        return new SuccessResponse();
    }
}
