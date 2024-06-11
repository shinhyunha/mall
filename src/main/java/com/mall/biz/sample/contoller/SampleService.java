package com.mall.biz.sample.contoller;

import com.mall.biz.sample.dto.req.SaveSampleDto;
import com.mall.biz.sample.entity.Sample;
import com.mall.biz.sample.repository.SampleJpaReposity;
import com.mall.biz.sample.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SampleService {
    private final SampleJpaReposity sampleJpaReposity;
    private final SampleRepository sampleRepository;

    @Transactional(readOnly = true)
    public List<Sample> searchSampleAll() {
        List<Sample> sampleList = sampleRepository.findAll();
        return sampleList;
    }

    public void saveSample(SaveSampleDto saveSampleDto) {
        sampleJpaReposity.save(saveSampleDto.sampleDtoToEntity());
    }
}
