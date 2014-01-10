package com.prodyna.conference.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prodyna.conference.service.model.Room;
import com.prodyna.conference.service.persistence.RoomRepository;

@Service
public class RoomServiceBean implements RoomService {

	@Autowired
	private RoomRepository repository;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Room> getAllRooms() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Room saveRoom(final Room Room) {
		return repository.save(Room);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteRoom(final Room room) {
		repository.delete(room);
	}
}
