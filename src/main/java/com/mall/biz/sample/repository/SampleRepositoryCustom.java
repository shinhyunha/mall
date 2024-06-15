package com.mall.biz.sample.repository;

import com.mall.biz.sample.entity.Sample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepositoryCustom {
    Sample findByName(String name);
}
