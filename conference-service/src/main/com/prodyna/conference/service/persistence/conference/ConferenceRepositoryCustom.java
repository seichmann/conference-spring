package com.prodyna.conference.service.persistence.conference;

import java.util.List;

import com.prodyna.conference.service.dto.RoomCountResult;
import com.prodyna.conference.service.model.Conference;

public interface ConferenceRepositoryCustom {
	List<RoomCountResult> evaluateRoomsCount(final Conference conference);
}
