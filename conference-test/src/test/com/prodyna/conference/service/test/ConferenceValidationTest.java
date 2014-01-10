package com.prodyna.conference.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.prodyna.conference.service.exception.ConferenceConstraintException;
import com.prodyna.conference.service.model.Conference;
import com.prodyna.conference.service.model.Talk;
import com.prodyna.conference.service.test.base.BaseTest;
import com.prodyna.conference.service.util.DateUtil;

/**
 * Test for Validation Logic of Conference.
 * 
 * @author Stephan Eichmann
 * 
 */
public class ConferenceValidationTest extends BaseTest {

	@Test(expected = ConferenceConstraintException.class)
	public void valdiateSpeakerConstraint() throws Exception {
		Talk talk1 = new Talk();
		talk1.setName("Java EE6");
		talk1.setDescription("JPA, CDI, EJB");
		talk1.setStart(DateUtil.parseHourMinute("01.05.2015 12:30"));
		talk1.setEnd(DateUtil.parseHourMinute("01.05.2015 14:30"));
		Talk saveTalk1 = talkService.saveTalk(talk1, null);

		Conference conference = new Conference();
		conference.setName("Prodyna Hausmesser");
		conference.setDescription("Best of the best.");
		conference.setStart(DateUtil.parse("01.07.2015"));
		conference.setEnd(DateUtil.parse("01.07.2015"));
		List<Talk> talks = new ArrayList<Talk>();
		talks.add(saveTalk1);
		conference.setTalks(talks);
		conferenceService.saveConference(conference);
	}
}
