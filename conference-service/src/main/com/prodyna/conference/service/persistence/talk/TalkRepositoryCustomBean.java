package com.prodyna.conference.service.persistence.talk;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.prodyna.conference.service.model.Talk;

public class TalkRepositoryCustomBean extends QueryDslRepositorySupport
		implements TalkRepositoryCustom {

	public TalkRepositoryCustomBean() {
		super(Talk.class);
	}
}
