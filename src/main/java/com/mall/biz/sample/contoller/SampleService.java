package com.mall.biz.sample.contoller;

import com.mall.biz.sample.dto.req.SaveSampleDto;
import com.mall.biz.sample.dto.req.SaveTeamDto;
import com.mall.biz.sample.dto.req.UpdateSampleDto;
import com.mall.biz.sample.dto.res.ReqSampleDto;
import com.mall.biz.sample.entity.Sample;
import com.mall.biz.sample.entity.SampleTeam;
import com.mall.biz.sample.repository.SampleRepository;
import com.mall.biz.sample.repository.SampleTeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;
    private final SampleTeamRepository sampleTeamRepository;

    @Transactional(readOnly = true)
    public List<ReqSampleDto> searchSampleAll() {
        // 아이디 sorting 정보 넣기
        Sort sort = Sort.by(Sort.Order.desc("id"));
        List<Sample> sampleList = sampleRepository.findAll(sort);
        // entity to dto 하여 정보전달
        List<ReqSampleDto> reqSampleDtoList = new ArrayList<>();
        for (Sample sample : sampleList) {
            reqSampleDtoList.add(ReqSampleDto.entityToDto(sample));
        }
        return reqSampleDtoList;
    }

    @Transactional
    public void saveSample(SaveSampleDto saveSampleDto) {
        // sampleteam을 따로 저장하여 엔티티에 속한 애를 가져온다.
        if (saveSampleDto.getSampleTeam() != null) {
            SampleTeam tempSampleTeam = sampleTeamRepository.save(saveSampleDto.getSampleTeam().dtoToSampleTeamEntity());
            // 그 속한 녀석을 sample에 넣는다.
            saveSampleDto.setSampleTeam(tempSampleTeam);
        }
        // sample 저장
        sampleRepository.save(saveSampleDto.dtoToSampleEntity());
    }

    @Transactional(readOnly = true)
    public ReqSampleDto searchSampleByName(String name) {
        // custom에 작성한고 impl에 구현 = querymethod
        Sample sample = sampleRepository.findByName(name);
        return (sample != null ? ReqSampleDto.entityToDto(sample) : null);
    }

    @Transactional
    public void saveSampleTeam(SaveTeamDto saveTeamDto) {
        sampleTeamRepository.save(saveTeamDto.dtoToEntity());
    }

    @Transactional
    public void updateSample(UpdateSampleDto updateSampleDto) {
        Sample sample = sampleRepository.findById(updateSampleDto.dtoToSampleEntity().getId()).orElse(null);
//        Optional<SampleTeam> sampleTeam = sampleTeamRepository.findById(updateSampleDto.getSampleTeam().getId());
        sample.updateSample(updateSampleDto.dtoToSampleEntity());
    }
}
