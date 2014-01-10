package com.prodyna.conference.service.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QTalkSpeakerRelation is a Querydsl query type for TalkSpeakerRelation
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QTalkSpeakerRelation extends EntityPathBase<TalkSpeakerRelation> {

    private static final long serialVersionUID = -374594939;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTalkSpeakerRelation talkSpeakerRelation = new QTalkSpeakerRelation("talkSpeakerRelation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSpeaker speaker;

    public final QTalk talk;

    public QTalkSpeakerRelation(String variable) {
        this(TalkSpeakerRelation.class, forVariable(variable), INITS);
    }

    public QTalkSpeakerRelation(Path<? extends TalkSpeakerRelation> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTalkSpeakerRelation(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QTalkSpeakerRelation(PathMetadata<?> metadata, PathInits inits) {
        this(TalkSpeakerRelation.class, metadata, inits);
    }

    public QTalkSpeakerRelation(Class<? extends TalkSpeakerRelation> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.speaker = inits.isInitialized("speaker") ? new QSpeaker(forProperty("speaker")) : null;
        this.talk = inits.isInitialized("talk") ? new QTalk(forProperty("talk"), inits.get("talk")) : null;
    }

}

