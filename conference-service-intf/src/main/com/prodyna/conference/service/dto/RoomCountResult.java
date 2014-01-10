package com.prodyna.conference.service.dto;

import java.io.Serializable;

import com.mysema.query.annotations.QueryEntity;
import com.prodyna.conference.service.model.Room;

/**
 * DTO for Room with counter,
 * 
 * @author Stephan Eichmann
 * 
 */
@QueryEntity
public class RoomCountResult implements Serializable {
	private static final long serialVersionUID = -1;

	private Room room;

	private Long count;

	public RoomCountResult(final Room room, final Long count) {
		super();
		this.room = room;
		this.count = count;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(final Room room) {
		this.room = room;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(final Long count) {
		this.count = count;
	}
}
