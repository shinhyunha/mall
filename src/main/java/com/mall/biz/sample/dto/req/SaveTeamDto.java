package com.mall.biz.sample.dto.req;

import com.mall.biz.sample.entity.Sample;
import com.mall.biz.sample.entity.SampleTeam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveTeamDto {
    private String teamName;

    public SampleTeam dtoToEntity() {
        return new SampleTeam(this.teamName);
    }
}
