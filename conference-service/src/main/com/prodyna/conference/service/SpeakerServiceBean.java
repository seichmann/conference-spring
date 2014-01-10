package com.prodyna.conference.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prodyna.conference.service.model.Speaker;
import com.prodyna.conference.service.persistence.SpeakerRepository;

@Service
public class SpeakerServiceBean implements SpeakerService {

	@Autowired
	private SpeakerRepository repository;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Speaker> getAllSpeakers() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Speaker saveSpeaker(final Speaker speaker) {
		return repository.save(speaker);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteSpeaker(final Speaker speaker) {
		repository.delete(speaker);
	}
}
