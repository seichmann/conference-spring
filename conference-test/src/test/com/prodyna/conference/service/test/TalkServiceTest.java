package com.prodyna.conference.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
public class TalkServiceTest extends BaseTest {

	@Test
	public void agetTalksBySpeaker() throws Exception {
		Speaker speaker1 = new Speaker();
		speaker1.setName("Stephan");
		speaker1.setDescription("...");

		Speaker speaker2 = new Speaker();
		speaker2.setName("Dennis");
		speaker2.setDescription("...");

		Speaker saveSpeaker1 = speakerService.saveSpeaker(speaker1);
		Speaker saveSpeaker2 = speakerService.saveSpeaker(speaker2);

		Talk talk1 = new Talk();
		talk1.setName("Java EE6");
		talk1.setDescription("JPA, CDI, EJB");
		talk1.setStart(DateUtil.parseHourMinute("01.05.2015 12:30"));
		talk1.setEnd(DateUtil.parseHourMinute("01.05.2015 14:30"));
		List<Speaker> speakers1 = new ArrayList<Speaker>();
		speakers1.add(saveSpeaker1);
		talkService.saveTalk(talk1, speakers1);

		// Assert
		List<Talk> talksBySpeaker = talkService.getTalksBySpeaker(saveSpeaker1
				.getId());
		Assert.assertEquals(1, talksBySpeaker.size());
		Assert.assertEquals("Java EE6", talksBySpeaker.get(0).getName());

		Talk talk2 = new Talk();
		talk2.setName("Spring");
		talk2.setDescription("Ecosystem");
		talk2.setStart(DateUtil.parseHourMinute("01.05.2015 13:30"));
		talk2.setEnd(DateUtil.parseHourMinute("01.05.2015 15:30"));
		List<Speaker> speakers2 = new ArrayList<Speaker>();
		speakers2.add(saveSpeaker2);
		talkService.saveTalk(talk2, speakers2);

		// Assert
		List<Talk> talksBySpeaker2 = talkService.getTalksBySpeaker(saveSpeaker2
				.getId());
		Assert.assertEquals(1, talksBySpeaker2.size());
		Assert.assertEquals("Spring", talksBySpeaker2.get(0).getName());
	}

	@Test
	public void bgetTalksByRoom() throws Exception {
		Room room1 = new Room();
		room1.setName("Meeting 1");
		room1.setCapacity(10);

		Room saveRoom1 = roomService.saveRoom(room1);

		Room room2 = new Room();
		room2.setName("Meeting 2");
		room2.setCapacity(20);

		Room saveRoom2 = roomService.saveRoom(room2);

		Talk talk1 = new Talk();
		talk1.setName("Java EE6 2");
		talk1.setDescription("JPA, CDI, EJB");
		talk1.setStart(DateUtil.parseHourMinute("01.05.2015 10:30"));
		talk1.setEnd(DateUtil.parseHourMinute("01.05.2015 12:30"));
		talk1.setRoom(saveRoom1);
		talkService.saveTalk(talk1, null);

		// Assert
		List<Talk> talksByRoom = talkService.getTalksByRoom(saveRoom1.getId());
		Assert.assertEquals(1, talksByRoom.size());
		Assert.assertEquals("Java EE6 2", talksByRoom.get(0).getName());

		Talk talk2 = new Talk();
		talk2.setName("Spring 2");
		talk2.setDescription("Ecosystem");
		talk2.setStart(DateUtil.parseHourMinute("01.05.2015 11:30"));
		talk2.setEnd(DateUtil.parseHourMinute("01.05.2015 15:30"));
		talk2.setRoom(saveRoom2);
		talkService.saveTalk(talk2, null);

		// Assert
		List<Talk> talksByRoom2 = talkService.getTalksByRoom(saveRoom2.getId());
		Assert.assertEquals(1, talksByRoom2.size());
		Assert.assertEquals("Spring 2", talksByRoom2.get(0).getName());
	}
}
