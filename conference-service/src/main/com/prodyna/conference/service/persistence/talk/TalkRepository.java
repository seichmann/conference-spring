package com.prodyna.conference.service.persistence.talk;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.prodyna.conference.service.model.Talk;

public interface TalkRepository extends JpaRepository<Talk, Long>,
		QueryDslPredicateExecutor<Talk>, TalkRepositoryCustom {

	@Query("SELECT t from Talk t where t.room.id = :roomId order by t.start desc")
	List<Talk> getTalksByRoom(@Param("roomId") Long roomId);

}
