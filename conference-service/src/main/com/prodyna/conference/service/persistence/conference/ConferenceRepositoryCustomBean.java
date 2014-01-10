package com.prodyna.conference.service.persistence.conference;

import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.mysema.query.types.Projections;
import com.mysema.query.types.expr.Wildcard;
import com.prodyna.conference.service.dto.RoomCountResult;
import com.prodyna.conference.service.model.Conference;
import com.prodyna.conference.service.model.QConference;
import com.prodyna.conference.service.model.QRoom;
import com.prodyna.conference.service.model.QTalk;

public class ConferenceRepositoryCustomBean extends QueryDslRepositorySupport
		implements ConferenceRepositoryCustom {

	public ConferenceRepositoryCustomBean() {
		super(Conference.class);
	}

	@Override
	public List<RoomCountResult> evaluateRoomsCount(final Conference conference) {
		QConference c = QConference.conference;
		QTalk talkAlias = QTalk.talk;
		QRoom room = talkAlias.room;

		// Alternative: directly to map
		// Map<Room, Long> list = query.from(c).join(c.talks, talkAlias)
		// .join(room).groupBy(room).orderBy(Wildcard.count.desc())
		// .map(room, Wildcard.count);
		return from(c)
				.join(c.talks, talkAlias)
				.join(room)
				.groupBy(room)
				.orderBy(Wildcard.count.desc())
				.list(Projections.constructor(RoomCountResult.class, room,
						Wildcard.count));
	}
}
