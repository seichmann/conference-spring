package com.prodyna.conference.service.test.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.prodyna.conference.service.ConferenceService;
import com.prodyna.conference.service.RoomService;
import com.prodyna.conference.service.SpeakerService;
import com.prodyna.conference.service.TalkService;

// TODO no workspace dependency
@ActiveProfiles("test")
@ContextConfiguration(locations = { "/applicationContext.xml",
		"applicationTestContext.xml" })
public abstract class BaseTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	static {
		// System.setProperty("xxx", "x");
		// SecurityContextHolder.getContext().setAuthentication(
		// new UsernamePasswordAuthenticationToken("ANONYM", ""));
	}

	@Autowired
	protected ConferenceService conferenceService;

	@Autowired
	protected TalkService talkService;

	@Autowired
	protected RoomService roomService;

	@Autowired
	protected SpeakerService speakerService;

	protected Date createDate(final String value) throws ParseException {
		return new SimpleDateFormat("dd.MM.yyyy").parse(value);
	}

	protected Date createDateHourMinute(final String value)
			throws ParseException {
		return new SimpleDateFormat("dd.MM.yyyy k:mm").parse(value);
	}
}
