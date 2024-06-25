package com.mall.biz.delivery.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeliveryStatus is a Querydsl query type for DeliveryStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryStatus extends EntityPathBase<DeliveryStatus> {

    private static final long serialVersionUID = 1944914687L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeliveryStatus deliveryStatus = new QDeliveryStatus("deliveryStatus");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final QDelivery delivery;

    public final EnumPath<DeliveryCode> deliveryCode = createEnum("deliveryCode", DeliveryCode.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QDeliveryStatus(String variable) {
        this(DeliveryStatus.class, forVariable(variable), INITS);
    }

    public QDeliveryStatus(Path<? extends DeliveryStatus> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeliveryStatus(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeliveryStatus(PathMetadata metadata, PathInits inits) {
        this(DeliveryStatus.class, metadata, inits);
    }

    public QDeliveryStatus(Class<? extends DeliveryStatus> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new QDelivery(forProperty("delivery")) : null;
    }

}

