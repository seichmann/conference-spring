package com.prodyna.conference.service.persistence.conference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodyna.conference.service.model.Conference;

@Repository
public interface ConferenceRepository extends ConferenceRepositoryCustom,
		JpaRepository<Conference, Long> {
}
