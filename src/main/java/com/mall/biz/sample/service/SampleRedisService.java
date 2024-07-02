package com.mall.biz.sample.service;

import com.mall.biz.sample.dto.req.*;
import com.mall.biz.sample.dto.res.ResRedisSampleListDto;
import com.mall.biz.sample.dto.res.ResSampleDto;
import com.mall.biz.sample.dto.res.ResSampleRedisDto;
import com.mall.biz.sample.entity.Sample;
import com.mall.biz.sample.entity.SampleRedis;
import com.mall.biz.sample.entity.SampleTeam;
import com.mall.biz.sample.repository.SampleRedisRepository;
import com.mall.biz.sample.repository.SampleRepository;
import com.mall.biz.sample.repository.SampleTeamRepository;
import com.mall.common.exception.InputCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleRedisService {
    private final SampleRedisRepository sampleRedisRepository;

    @Transactional
    public SampleRedis saveRedisSample(SaveRedisSampleDto saveRedisSampleDto) {
        System.out.println(saveRedisSampleDto);
        SampleRedis sampleRedis = saveRedisSampleDto.dtoToEntity();
        SampleRedis save = sampleRedisRepository.save(sampleRedis);
        return save;
    }

    @Transactional
    public List<ResRedisSampleListDto> searchRedisSampleList() {
        Iterable<SampleRedis> all = sampleRedisRepository.findAll();
        List<ResRedisSampleListDto> result = new ArrayList<>();
        for (SampleRedis sampleRedis : all) {
            result.add(new ResRedisSampleListDto(sampleRedis.getId(), sampleRedis.getName()));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public ResSampleRedisDto searchRedisSample(Long id) {
        SampleRedis sampleRedis = sampleRedisRepository.findById(id).orElseThrow(()
                -> new InputCheckException("SampleRedis id 체크하세요."));
        return new ResSampleRedisDto(sampleRedis.getId(), sampleRedis.getName());
    }

    @Transactional
    public void removeRedisSample(Long id) {
        SampleRedis findRedis = sampleRedisRepository.findById(id).orElseThrow(()
                -> new InputCheckException("아이디를 확인하세요."));
        sampleRedisRepository.delete(findRedis);
    }

    @Transactional
    public SampleRedis updateRedisSample(UpdateRedisSampleDto updateRedisSampleDto) {
        SampleRedis findSample = sampleRedisRepository.findById(updateRedisSampleDto.getId()).orElseThrow(()
                -> new InputCheckException("ID를 확인하세요."));

        findSample.updateName(updateRedisSampleDto.getName());
        return findSample;
    }
}
