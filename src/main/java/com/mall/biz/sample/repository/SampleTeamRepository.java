package com.mall.biz.sample.repository;

import com.mall.biz.sample.entity.SampleTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SampleTeamRepository extends JpaRepository<SampleTeam, Long>, QuerydslPredicateExecutor<SampleTeam> {
}
