package com.mall.biz.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderPurchaser is a Querydsl query type for OrderPurchaser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderPurchaser extends EntityPathBase<OrderPurchaser> {

    private static final long serialVersionUID = -258413338L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderPurchaser orderPurchaser = new QOrderPurchaser("orderPurchaser");

    public final com.mall.common.entity.QBaseDateEntity _super = new com.mall.common.entity.QBaseDateEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedDate = _super.lastModifiedDate;

    public final com.mall.biz.member.entity.QMember member;

    public final StringPath name = createString("name");

    public final QOrder order;

    public final StringPath phone = createString("phone");

    public QOrderPurchaser(String variable) {
        this(OrderPurchaser.class, forVariable(variable), INITS);
    }

    public QOrderPurchaser(Path<? extends OrderPurchaser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderPurchaser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderPurchaser(PathMetadata metadata, PathInits inits) {
        this(OrderPurchaser.class, metadata, inits);
    }

    public QOrderPurchaser(Class<? extends OrderPurchaser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.mall.biz.member.entity.QMember(forProperty("member")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order")) : null;
    }

}

