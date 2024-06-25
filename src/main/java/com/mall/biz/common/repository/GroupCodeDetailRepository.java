package com.mall.biz.common.repository;

import com.mall.common.entity.GroupCodeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupCodeDetailRepository extends JpaRepository<GroupCodeDetailEntity, Long>, GroupCodeDetailRepositoryCustom {
}
