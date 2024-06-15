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
public class ReqSampleTeamDto {
    private Long id;
    private String teamName;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;

    public ReqSampleTeamDto(Long id, String teamName, LocalDateTime createDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy) {
        this.id = id;
        this.teamName = teamName;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public static ReqSampleTeamDto entityToDto(SampleTeam sampleTeam) {
        return ReqSampleTeamDto.builder()
                .id(sampleTeam.getId())
                .teamName(sampleTeam.getTeamName())
                .createDate(sampleTeam.getCreateDate())
                .lastModifiedDate(sampleTeam.getLastModifiedDate())
                .createdBy(sampleTeam.getCreatedBy())
                .lastModifiedBy(sampleTeam.getLastModifiedBy())
                .build();
    }
}
