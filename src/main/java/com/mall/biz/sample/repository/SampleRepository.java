package com.mall.biz.sample.repository;

import com.mall.biz.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long>, SampleRepositoryCustom, QuerydslPredicateExecutor<Sample> {
}
