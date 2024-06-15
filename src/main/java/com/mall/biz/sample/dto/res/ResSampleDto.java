package com.mall.biz.sample.dto.res;

import com.mall.biz.sample.entity.Sample;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
public class ResSampleDto {
    private Long id;
    private String name;
    private Long teamId;
    private ResSampleTeamDto resSampleTeamDto;
    private LocalDateTime createDate;
    private LocalDateTime lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;

    public ResSampleDto(Long id, String name, Long teamId, ResSampleTeamDto resSampleTeamDto, LocalDateTime createDate, LocalDateTime lastModifiedDate, String createdBy, String lastModifiedBy) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
        this.resSampleTeamDto = resSampleTeamDto;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
    }

    public static ResSampleDto entityToDto(Sample sample) {
        return ResSampleDto.builder()
                .id(sample.getId())
                .name(sample.getName())
                .teamId(sample.getSampleTeam() != null ? sample.getSampleTeam().getId() : null)
                .resSampleTeamDto(sample.getSampleTeam() != null? ResSampleTeamDto.entityToDto(sample.getSampleTeam()) : null)
                .createDate(sample.getCreateDate())
                .lastModifiedDate(sample.getLastModifiedDate())
                .createdBy(sample.getCreatedBy())
                .lastModifiedBy(sample.getLastModifiedBy())
                .build();
    }
}
