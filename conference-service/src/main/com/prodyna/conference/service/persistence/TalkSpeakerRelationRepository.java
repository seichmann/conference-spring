package com.prodyna.conference.service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.prodyna.conference.service.model.TalkSpeakerRelation;

@Repository
public interface TalkSpeakerRelationRepository extends
		JpaRepository<TalkSpeakerRelation, Long>,
		QueryDslPredicateExecutor<TalkSpeakerRelation> {

}
