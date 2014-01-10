package com.prodyna.conference.service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prodyna.conference.service.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
