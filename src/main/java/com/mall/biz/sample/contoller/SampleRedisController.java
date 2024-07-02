package com.mall.biz.sample.contoller;

import com.mall.biz.sample.dto.req.*;
import com.mall.biz.sample.dto.res.ResSampleDto;
import com.mall.biz.sample.dto.res.ResSampleRedisDto;
import com.mall.biz.sample.entity.SampleRedis;
import com.mall.biz.sample.service.SampleRedisService;
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
@RequestMapping("/api/sample/redis")
@Tag(name="샘플 Redis", description = "샘플 Redis API")
public class SampleRedisController {
    @Autowired
    private final SampleRedisService sampleRedisService;

    @PostMapping("")
    @Operation(summary = "redis 샘플 등록", description = "redis 샘플을 등록한다.")
    public SuccessResponse saveRedisSample(@RequestBody SaveRedisSampleDto saveRedisSampleDto) {
        SampleRedis result = sampleRedisService.saveRedisSample(saveRedisSampleDto);
        return new SuccessResponse(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "redis 아이디로 단건 조회", description = "redis 아이디로 단건 조회한다.")
    public SuccessResponse searchRedisSample(@PathVariable(name = "id") Long id) {
        ResSampleRedisDto result = sampleRedisService.searchRedisSample(id);
        return new SuccessResponse(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "redis 아이디로 db 제거", description = "redis 아이디로 db제거한다.")
    public SuccessResponse removeRedisSample(@PathVariable(name = "id") Long id) {
        sampleRedisService.removeRedisSample(id);
        return new SuccessResponse();
    }

    @PutMapping("")
    @Operation(summary = "redis 데이터 수정", description = "redis 데이터 수정한다.")
    public SuccessResponse updateRedisSample(@RequestBody UpdateRedisSampleDto updateRedisSampleDto) {
        SampleRedis result = sampleRedisService.updateRedisSample(updateRedisSampleDto);
        return new SuccessResponse();
    }
}
