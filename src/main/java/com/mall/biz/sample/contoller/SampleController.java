package com.mall.biz.sample.contoller;

import com.mall.biz.sample.dto.req.SaveSampleDto;
import com.mall.biz.sample.entity.Sample;
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiResponse(responseCode = "200", description = "샘플 조회 성공", content = @Content(schema = @Schema(implementation = Sample.class)))
    })
    public SuccessResponse searchSampleAll() {
        List<Sample> result = sampleService.searchSampleAll();
        return new SuccessResponse(result);
    }

    @PostMapping("")
    @Operation(summary = "샘플 등록", description = "샘플을 등록한다.")
    public SuccessResponse saveSample(SaveSampleDto saveSampleDto) {
        sampleService.saveSample(saveSampleDto);
        return new SuccessResponse();
    }
}
