package com.prodyna.conference.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.path.NumberPath;
import com.prodyna.conference.service.exception.ConferenceServiceException;
import com.prodyna.conference.service.exception.RoomConstraintException;
import com.prodyna.conference.service.exception.SpeakerConstraintException;
import com.prodyna.conference.service.model.QTalk;
import com.prodyna.conference.service.model.QTalkSpeakerRelation;
import com.prodyna.conference.service.model.Speaker;
import com.prodyna.conference.service.model.Talk;
import com.prodyna.conference.service.model.TalkSpeakerRelation;
import com.prodyna.conference.service.persistence.TalkSpeakerRelationRepository;
import com.prodyna.conference.service.persistence.talk.TalkRepository;
import com.prodyna.conference.service.util.ConvertingUtils;

@Service
public class TalkServiceBean implements TalkService {

	@Autowired
	private TalkRepository talkRepository;

	@Autowired
	private TalkSpeakerRelationRepository talkSpeakerRepository;

	@Override
	public List<Talk> getAllTalks() {
		return talkRepository.findAll();
	}

	@Override
	public void deleteTalk(final Talk talk) {
		talkRepository.delete(talk);
	}

	@Override
	public Talk saveTalk(final Talk talk, final List<Speaker> speakerList)
			throws ConferenceServiceException {
		// Validate Constraints
		List<Talk> talks = getTalks(talk.getStart(), talk.getEnd());

		if (speakerList != null && !speakerList.isEmpty()) {
			for (Speaker speaker : speakerList) {
				for (Talk existingTalk : talks) {
					List<Speaker> existingSpeakers = getSpeakersByTalk(existingTalk
							.getId());

					if (!isSameTalk(existingTalk, talk)
							&& existingSpeakers != null
							&& existingSpeakers.contains(speaker)) {
						throw new SpeakerConstraintException(existingTalk);
					}
				}
			}
		}

		if (talk.getRoom() != null) {
			for (Talk existingTalk : talks) {
				if (!isSameTalk(existingTalk, talk)
						&& existingTalk.getRoom() != null
						&& existingTalk.getRoom().equals(talk.getRoom())) {
					throw new RoomConstraintException(existingTalk);
				}
			}
		}

		Talk result = talkRepository.save(talk);

		// Unassign when updated talk.
		if (talk.getId() != null) {
			unassignAllSpeakers(result);
		}

		if (speakerList != null && !speakerList.isEmpty()) {
			for (Speaker speaker : speakerList) {
				assignSpeaker(result, speaker);
			}
		}

		return result;
	}

	private void assignSpeaker(final Talk talk, final Speaker speaker) {
		TalkSpeakerRelation talkSpeakerRelation = new TalkSpeakerRelation();
		talkSpeakerRelation.setTalk(talk);
		talkSpeakerRelation.setSpeaker(speaker);
		talkSpeakerRepository.save(talkSpeakerRelation);
	}

	private void unassignAllSpeakers(final Talk talk) {
		List<TalkSpeakerRelation> talkSpeakerRelations = getTalkSpeakerRelations(talk
				.getId());
		for (TalkSpeakerRelation talkSpeakerRelation : talkSpeakerRelations) {
			talkSpeakerRepository.delete(talkSpeakerRelation);
		}
	}

	private boolean isSameTalk(final Talk existingTalk, final Talk talk) {
		return talk.getId() != null
				&& talk.getId().equals(existingTalk.getId());
	}

	@Override
	public List<Speaker> getSpeakersByTalk(final Long talkId) {
		List<TalkSpeakerRelation> talkSpeakerRelations = getTalkSpeakerRelations(talkId);
		List<Speaker> result = new ArrayList<Speaker>();
		for (TalkSpeakerRelation talkSpeakerRelation : talkSpeakerRelations) {
			result.add(talkSpeakerRelation.getSpeaker());
		}
		return result;
	}

	private List<TalkSpeakerRelation> getTalkSpeakerRelations(final Long talkId) {
		NumberPath<Long> talkIdPath = QTalkSpeakerRelation.talkSpeakerRelation.talk.id;
		return ConvertingUtils.constructList(talkSpeakerRepository
				.findAll(talkIdPath.eq(talkId)));
	}

	@Override
	public List<Talk> getTalks(final Date start, final Date end) {
		QTalk talk = QTalk.talk;

		BooleanBuilder builder = new BooleanBuilder();
		builder.or(talk.start.lt(start).and(talk.end.gt(start)));
		builder.or(talk.start.lt(end).and(talk.end.gt(end)));
		builder.or(talk.start.gt(start).and(talk.end.lt(end)));
		return ConvertingUtils.constructList(talkRepository.findAll(builder
				.getValue()));
	}

	@Override
	public List<Talk> getTalksBySpeaker(final Long speakerId) {
		NumberPath<Long> speakerIdPath = QTalkSpeakerRelation.talkSpeakerRelation.speaker.id;
		Iterable<TalkSpeakerRelation> talkSpeakerRelations = talkSpeakerRepository
				.findAll(speakerIdPath.eq(speakerId));

		List<Talk> result = new ArrayList<Talk>();
		for (TalkSpeakerRelation talkSpeakerRelation : talkSpeakerRelations) {
			result.add(talkSpeakerRelation.getTalk());
		}
		return result;
	}

	@Override
	public List<Talk> getTalksByRoom(final Long roomId) {
		// TypedQuery<Talk> query = em.createNamedQuery("Talk.ByRoom",
		// Talk.class);
		// query.setParameter("roomId", roomId);
		// return query.getResultList();

		// Alternativ auch Predicate + Orderspecifier anstelle einer Query.
		return talkRepository.getTalksByRoom(roomId);
	}

	@Override
	public Talk getTalk(final Long talkId) {
		return talkId != null ? talkRepository.findOne(talkId) : null;
	}
}
