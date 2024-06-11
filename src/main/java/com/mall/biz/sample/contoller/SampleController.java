package com.mall.biz.sample.contoller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sample")
@Tag(name="샘플", description = "샘플 API")
public class SampleController {
    @GetMapping("")
    @Operation(summary = "String 테스트", description = "String 출력")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "String 조회 성공", content = @Content(schema = @Schema(implementation = String.class)))
    })
    public String getString() {
        return "테스트 성공23";
    }

//    @GetMapping("")
//    @Operation(summary = "샘플 전체조회", description = "모든 샘플을 전체 조회한다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "샘플 조회 성공", content = @Content(schema = @Schema(implementation = Sample.class)))
//    })
//    public SuccessResponse getSampleList() {
//        List<Sample> result = sampleService.getSampleList();
//        return new SuccessResponse(result);
//    }
}
