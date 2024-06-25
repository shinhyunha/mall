package com.mall.biz.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderStatus is a Querydsl query type for OrderStatus
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderStatus extends EntityPathBase<OrderStatus> {

    private static final long serialVersionUID = 1123354301L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderStatus orderStatus = new QOrderStatus("orderStatus");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOrder order;

    public final EnumPath<OrderCode> orderCode = createEnum("orderCode", OrderCode.class);

    public QOrderStatus(String variable) {
        this(OrderStatus.class, forVariable(variable), INITS);
    }

    public QOrderStatus(Path<? extends OrderStatus> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderStatus(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderStatus(PathMetadata metadata, PathInits inits) {
        this(OrderStatus.class, metadata, inits);
    }

    public QOrderStatus(Class<? extends OrderStatus> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order")) : null;
    }

}

