package com.mall.biz.sample.dto.req;

import com.mall.biz.sample.entity.Sample;
import com.mall.biz.sample.entity.SampleTeam;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSampleDto {
    private Long id;
    private String name;
    private SampleTeam sampleTeam;

    public Sample dtoToSampleEntity() {
        return (this.sampleTeam == null ? new Sample(this.name, this.id) : new Sample(this.id, this.name, this.sampleTeam));
    }
}
