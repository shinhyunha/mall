package com.mall.biz.sample.dto.req;

import com.mall.biz.sample.entity.Sample;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaveSampleDto {
    private String name;

    public Sample sampleDtoToEntity() {
        return new Sample(this.name);
    }
}
