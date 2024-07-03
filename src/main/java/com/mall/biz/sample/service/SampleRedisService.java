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
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SampleRedisService {
    private final SampleRedisRepository sampleRedisRepository;
    private final AtomicLong idCounter = new AtomicLong();

    @PostConstruct
    private void init() {
        // Redis id 체크
        List<SampleRedis> samples = StreamSupport.stream(sampleRedisRepository.findAll().spliterator(), false)
                .toList();

        if (!samples.isEmpty()) {
            long maxId = samples.stream().mapToLong(SampleRedis::getId).max().orElse(0);
            idCounter.set(maxId);
        } else {
            idCounter.set(0);
        }
    }

    @Transactional
    public SampleRedis saveRedisSample(SaveRedisSampleDto saveRedisSampleDto) {
        System.out.println(saveRedisSampleDto);
        saveRedisSampleDto.setId(idCounter);
        SampleRedis sampleRedis = saveRedisSampleDto.dtoToEntity();
        return sampleRedisRepository.save(sampleRedis);
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
        sampleRedisRepository.save(findSample);
        return findSample;
    }

    @Transactional(readOnly = true)
    public List<ResSampleRedisDto> searchRedisSampleAll() {
        Iterable<SampleRedis> all = sampleRedisRepository.findAll();
        List<ResSampleRedisDto> result = new ArrayList<>();
        for (SampleRedis item : all) {
            result.add(new ResSampleRedisDto(item.getId(), item.getName()));
        }

        return result;
    }
}
