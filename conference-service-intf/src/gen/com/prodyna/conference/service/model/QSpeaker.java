package com.prodyna.conference.service.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSpeaker is a Querydsl query type for Speaker
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSpeaker extends EntityPathBase<Speaker> {

    private static final long serialVersionUID = 912883381;

    public static final QSpeaker speaker = new QSpeaker("speaker");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QSpeaker(String variable) {
        super(Speaker.class, forVariable(variable));
    }

    public QSpeaker(Path<? extends Speaker> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSpeaker(PathMetadata<?> metadata) {
        super(Speaker.class, metadata);
    }

}

