package com.mall.biz.sample.entity;

import com.mall.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sample_team")
public class SampleTeam extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sample_team_id")
    private Long id;

    private String teamName;

    public SampleTeam(String teamName) {
        this.teamName = teamName;
    }

    public SampleTeam dtoToSampleTeamEntity() {
        return new SampleTeam(this.teamName);
    }

    public void updateSampleTeam(SampleTeam updateSampleTeam) {
        this.teamName = updateSampleTeam.teamName;
    }
}
