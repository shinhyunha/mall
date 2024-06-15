package com.mall.biz.sample.dto.req;

import com.mall.biz.sample.entity.Sample;
import com.mall.biz.sample.entity.SampleTeam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveSampleDto {
    private String name;
    private Long teamId;

    public Sample dtoToSampleEntity() {
        return new Sample(this.name);
    }
}
