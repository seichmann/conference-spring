package com.prodyna.conference.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prodyna.conference.service.dto.RoomCountResult;
import com.prodyna.conference.service.exception.ConferenceConstraintException;
import com.prodyna.conference.service.exception.ConferenceServiceException;
import com.prodyna.conference.service.model.Conference;
import com.prodyna.conference.service.model.Talk;
import com.prodyna.conference.service.persistence.conference.ConferenceRepository;
import com.prodyna.conference.service.util.DateUtil;

@Service
public class ConferenceServiceBean implements ConferenceService {

	@Autowired
	private ConferenceRepository repository;

	@Override
	public List<Conference> getAllConferences() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Conference saveConference(final Conference conference)
			throws ConferenceServiceException {
		// Validate Constraints (Talks not in daterange of conference)
		if (conference.getTalks() != null) {
			for (Talk talk : conference.getTalks()) {
				if (!DateUtil.inRange(talk.getStart(), conference.getStart(),
						conference.getEnd())
						|| !DateUtil.inRange(talk.getEnd(),
								conference.getStart(), conference.getEnd())) {
					throw new ConferenceConstraintException(talk);
				}
			}

		}

		return repository.save(conference);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteConference(final Conference conference) {
		repository.delete(conference);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<RoomCountResult> getRoomsCount(final Conference conference) {
		return repository.evaluateRoomsCount(conference);
	}
}
