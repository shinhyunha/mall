package com.mall.biz.sample.repository;

import com.mall.biz.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Long>, SampleRepositoryCustom, QuerydslPredicateExecutor<Sample> {
    // select s from Sample s where s.name = :name
    List<Sample> findByName(String name);
}
