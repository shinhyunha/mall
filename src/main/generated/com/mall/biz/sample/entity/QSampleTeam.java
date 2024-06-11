package com.mall.biz.sample.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSampleTeam is a Querydsl query type for SampleTeam
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSampleTeam extends EntityPathBase<SampleTeam> {

    private static final long serialVersionUID = 776755158L;

    public static final QSampleTeam sampleTeam = new QSampleTeam("sampleTeam");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath teamName = createString("teamName");

    public QSampleTeam(String variable) {
        super(SampleTeam.class, forVariable(variable));
    }

    public QSampleTeam(Path<? extends SampleTeam> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSampleTeam(PathMetadata metadata) {
        super(SampleTeam.class, metadata);
    }

}

