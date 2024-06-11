package com.mall.biz.sample.entity;

import com.mall.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sample")
public class Sample extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sample_id")
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_team_id")
    private SampleTeam sampleTeam;

    public Sample(String name) {
        this.name = name;
    }

    public Sample(String name, SampleTeam sampleTeam) {
        this.name = name;
        this.sampleTeam = sampleTeam;
    }
}
