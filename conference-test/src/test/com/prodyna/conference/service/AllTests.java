package com.prodyna.conference.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.prodyna.conference.service.test.ConferenceServiceTest;
import com.prodyna.conference.service.test.ConferenceValidationTest;
import com.prodyna.conference.service.test.TalkServiceValidationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ConferenceServiceTest.class,
		ConferenceValidationTest.class, TalkServiceValidationTest.class })
public class AllTests {
}
