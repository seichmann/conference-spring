package com.prodyna.conference.service.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QConference is a Querydsl query type for Conference
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QConference extends EntityPathBase<Conference> {

    private static final long serialVersionUID = -879484250;

    public static final QConference conference = new QConference("conference");

    public final StringPath description = createString("description");

    public final DateTimePath<java.util.Date> end = createDateTime("end", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.util.Date> start = createDateTime("start", java.util.Date.class);

    public final ListPath<Talk, QTalk> talks = this.<Talk, QTalk>createList("talks", Talk.class, QTalk.class, PathInits.DIRECT2);

    public QConference(String variable) {
        super(Conference.class, forVariable(variable));
    }

    public QConference(Path<? extends Conference> path) {
        super(path.getType(), path.getMetadata());
    }

    public QConference(PathMetadata<?> metadata) {
        super(Conference.class, metadata);
    }

}

