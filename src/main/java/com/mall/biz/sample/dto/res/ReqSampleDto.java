package com.mall.biz.sample.dto.res;

import com.mall.biz.sample.entity.Sample;
import com.mall.biz.sample.entity.SampleTeam;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class ReqSampleDto {
    private Long id;
    private String name;
    private Long teamId;
    private ReqSampleTeamDto reqSampleTeamDto;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;

    public ReqSampleDto(Long id, String name, Long teamId, ReqSampleTeamDto reqSampleTeamDto, LocalDateTime createDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
        this.reqSampleTeamDto = reqSampleTeamDto;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public static ReqSampleDto entityToDto(Sample sample) {
        return ReqSampleDto.builder()
                .id(sample.getId())
                .name(sample.getName())
                .teamId(sample.getSampleTeam() != null ? sample.getSampleTeam().getId() : null)
                .reqSampleTeamDto(sample.getSampleTeam() != null? ReqSampleTeamDto.entityToDto(sample.getSampleTeam()) : null)
                .createDate(sample.getCreateDate())
                .lastModifiedDate(sample.getLastModifiedDate())
                .createdBy(sample.getCreatedBy())
                .lastModifiedBy(sample.getLastModifiedBy())
                .build();
    }
}
