package com.mall.biz.sample.service;

import com.mall.biz.sample.dto.req.SaveSampleDto;
import com.mall.biz.sample.dto.req.SaveTeamDto;
import com.mall.biz.sample.dto.req.UpdateSampleDto;
import com.mall.biz.sample.dto.res.ResSampleDto;
import com.mall.biz.sample.entity.Sample;
import com.mall.biz.sample.entity.SampleTeam;
import com.mall.biz.sample.repository.SampleRepository;
import com.mall.biz.sample.repository.SampleTeamRepository;
import com.mall.common.exception.InputCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;
    private final SampleTeamRepository sampleTeamRepository;

    @Transactional(readOnly = true)
    public List<ResSampleDto> searchSampleAll() {
        // 아이디 sorting 정보 넣기
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Sample> sampleList = sampleRepository.findAll(sort);
        // entity to dto 하여 정보전달
        List<ResSampleDto> resSampleDtoList = new ArrayList<>();
        for (Sample sample : sampleList) {
            resSampleDtoList.add(ResSampleDto.entityToDto(sample));
        }
        return resSampleDtoList;
    }

    @Transactional
    public void saveSample(SaveSampleDto saveSampleDto) {
        Sample sample = saveSampleDto.dtoToSampleEntity();
        // sampleteam을 조회하여 팀정보 입력
        if (saveSampleDto.getTeamId() != null) {
            SampleTeam sampleTeam = sampleTeamRepository.findById(saveSampleDto.getTeamId()).orElseThrow(() ->
                    new InputCheckException("Team Id를 확인하세요."));

            // 그 속한 녀석을 sample에 넣는다.
            sample.setTeam(sampleTeam);
        }
        // sample 저장
        sampleRepository.save(sample);
    }

    @Transactional(readOnly = true)
    public ResSampleDto searchSampleByName(String name) {
        // custom에 작성한고 impl에 구현 = querymethod
        Sample sample = sampleRepository.findByName(name);
        return (sample != null ? ResSampleDto.entityToDto(sample) : null);
    }

    @Transactional
    public void saveSampleTeam(SaveTeamDto saveTeamDto) {
        sampleTeamRepository.save(saveTeamDto.dtoToEntity());
    }

    @Transactional
    public void updateSample(UpdateSampleDto updateSampleDto) {
        Sample sample = sampleRepository.findById(updateSampleDto.getId()).orElseThrow(()
                -> new InputCheckException("Sample id를 확인하세요."));
        if (updateSampleDto.getTeamId() != null) {
            SampleTeam sampleTeam = sampleTeamRepository.findById(updateSampleDto.getTeamId()).orElse(null);
            sample.setTeam(sampleTeam);
        } else {
            sample.setTeam(null);
        }
        sample.updateSample(updateSampleDto.dtoToSampleEntity());
    }

    @Transactional
    public void removeSample(Long id) {
        Sample sample = sampleRepository.findById(id).orElseThrow(()
                -> new InputCheckException("Sample Id를 확인하세요."));
        sampleRepository.delete(sample);
    }
}
