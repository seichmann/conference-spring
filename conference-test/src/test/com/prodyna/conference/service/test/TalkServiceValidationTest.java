package com.prodyna.conference.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.prodyna.conference.service.exception.RoomConstraintException;
import com.prodyna.conference.service.exception.SpeakerConstraintException;
import com.prodyna.conference.service.model.Room;
import com.prodyna.conference.service.model.Speaker;
import com.prodyna.conference.service.model.Talk;
import com.prodyna.conference.service.test.base.BaseTest;
import com.prodyna.conference.service.util.DateUtil;

/**
 * Test for Validation Logic of Conference / Talks.
 * 
 * @author Stephan Eichmann
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TalkServiceValidationTest extends BaseTest {

	@Test(expected = SpeakerConstraintException.class)
	public void aValdiateSpeakerConstraint() throws Exception {
		Speaker speaker = new Speaker();
		speaker.setName("Stephan");
		speaker.setDescription("...");

		Speaker saveSpeaker = speakerService.saveSpeaker(speaker);

		Talk talk1 = new Talk();
		talk1.setName("Java EE6");
		talk1.setDescription("JPA, CDI, EJB");
		talk1.setStart(DateUtil.parseHourMinute("01.05.2015 12:30"));
		talk1.setEnd(DateUtil.parseHourMinute("01.05.2015 14:30"));
		List<Speaker> speakers = new ArrayList<Speaker>();
		speakers.add(saveSpeaker);
		Talk saveTalk1 = talkService.saveTalk(talk1, speakers);

		Talk talk2 = new Talk();
		talk2.setName("Spring");
		talk2.setDescription("Ecosystem");
		talk2.setStart(DateUtil.parseHourMinute("01.05.2015 13:30"));
		talk2.setEnd(DateUtil.parseHourMinute("01.05.2015 15:30"));
		Talk saveTalk2 = talkService.saveTalk(talk2, speakers);
	}

	@Test(expected = RoomConstraintException.class)
	public void bValdiateRoomConstraint() throws Exception {
		Room room = new Room();
		room.setName("Meeting 1");
		room.setCapacity(10);

		Room saveRoom = roomService.saveRoom(room);

		Talk talk1 = new Talk();
		talk1.setName("Java EE6 2");
		talk1.setDescription("JPA, CDI, EJB");
		talk1.setStart(DateUtil.parseHourMinute("01.05.2015 10:30"));
		talk1.setEnd(DateUtil.parseHourMinute("01.05.2015 12:30"));
		talk1.setRoom(saveRoom);
		Talk saveTalk1 = talkService.saveTalk(talk1, null);

		Talk talk2 = new Talk();
		talk2.setName("Spring 2");
		talk2.setDescription("Ecosystem");
		talk2.setStart(DateUtil.parseHourMinute("01.05.2015 11:30"));
		talk2.setEnd(DateUtil.parseHourMinute("01.05.2015 15:30"));
		talk2.setRoom(saveRoom);
		Talk saveTalk2 = talkService.saveTalk(talk2, null);
	}
}
