package com.mall.biz.sample.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSample is a Querydsl query type for Sample
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSample extends EntityPathBase<Sample> {

    private static final long serialVersionUID = 701838809L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSample sample = new QSample("sample");

    public final com.mall.common.entity.QBaseEntity _super = new com.mall.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath name = createString("name");

    public final QSampleTeam sampleTeam;

    public QSample(String variable) {
        this(Sample.class, forVariable(variable), INITS);
    }

    public QSample(Path<? extends Sample> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSample(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSample(PathMetadata metadata, PathInits inits) {
        this(Sample.class, metadata, inits);
    }

    public QSample(Class<? extends Sample> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sampleTeam = inits.isInitialized("sampleTeam") ? new QSampleTeam(forProperty("sampleTeam")) : null;
    }

}

