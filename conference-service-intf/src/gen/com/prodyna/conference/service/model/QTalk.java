package com.prodyna.conference.service.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTalk is a Querydsl query type for Talk
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTalk extends EntityPathBase<Talk> {

    private static final long serialVersionUID = -1432714826;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTalk talk = new QTalk("talk");

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> end = createDateTime("end", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QRoom room;

    public final DateTimePath<java.util.Date> start = createDateTime("start", java.util.Date.class);

    public QTalk(String variable) {
        this(Talk.class, forVariable(variable), INITS);
    }

    public QTalk(Path<? extends Talk> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTalk(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTalk(PathMetadata<?> metadata, PathInits inits) {
        this(Talk.class, metadata, inits);
    }

    public QTalk(Class<? extends Talk> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new QRoom(forProperty("room")) : null;
    }

}

