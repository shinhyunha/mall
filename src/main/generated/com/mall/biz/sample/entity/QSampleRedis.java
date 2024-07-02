package com.mall.biz.sample.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSampleRedis is a Querydsl query type for SampleRedis
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSampleRedis extends EntityPathBase<SampleRedis> {

    private static final long serialVersionUID = -1692238046L;

    public static final QSampleRedis sampleRedis = new QSampleRedis("sampleRedis");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSampleRedis(String variable) {
        super(SampleRedis.class, forVariable(variable));
    }

    public QSampleRedis(Path<? extends SampleRedis> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSampleRedis(PathMetadata metadata) {
        super(SampleRedis.class, metadata);
    }

}

