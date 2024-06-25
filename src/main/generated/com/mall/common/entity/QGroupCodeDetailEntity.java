package com.mall.common.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGroupCodeDetailEntity is a Querydsl query type for GroupCodeDetailEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGroupCodeDetailEntity extends EntityPathBase<GroupCodeDetailEntity> {

    private static final long serialVersionUID = -936851733L;

    public static final QGroupCodeDetailEntity groupCodeDetailEntity = new QGroupCodeDetailEntity("groupCodeDetailEntity");

    public final StringPath code_data = createString("code_data");

    public final StringPath description = createString("description");

    public final StringPath detailCode = createString("detailCode");

    public final StringPath groupCode = createString("groupCode");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> sequence = createNumber("sequence", Integer.class);

    public QGroupCodeDetailEntity(String variable) {
        super(GroupCodeDetailEntity.class, forVariable(variable));
    }

    public QGroupCodeDetailEntity(Path<? extends GroupCodeDetailEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroupCodeDetailEntity(PathMetadata metadata) {
        super(GroupCodeDetailEntity.class, metadata);
    }

}

