package com.pms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.RoomInfo;
import com.pms.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	private RoomRepository roomRepository;

	public List<RoomInfo> getAll(String status) {
		return roomRepository.findAll(status);
	}

	
	public void saveRoomInfos(RoomInfo methodInfo) {
		roomRepository.saveRoomInfos(methodInfo);
	}

	public boolean validateRoomName(Map<String, String[]> requestMap) {
		String roomName = requestMap.get("roomName")[0];
		List<RoomInfo> entityTransList = roomRepository.validateRoomName(roomName);
		if (entityTransList.size() == 0) {
			return true;
		}

		return false;
	}
}
