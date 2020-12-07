package com.pms.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.ClientInfo;
import com.pms.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<ClientInfo> getAll(String status) {
		return clientRepository.findAll(status);
	}
	
	public ClientInfo getClientInfoById(UUID id) {
		ClientInfo info = clientRepository.getClientInfoById(id);
		return info;
	}
	
	public void saveClientInfos(ClientInfo clientInfo) {
		clientRepository.saveClientInfos(clientInfo);
	}

	public boolean validateClientId(Map<String, String[]> requestMap) {
		String clientId = requestMap.get("clientId")[0];
		List<ClientInfo> clientIdList = clientRepository.validateClientId(clientId);
		if (clientIdList.size() == 0) {
			return true;
		}
		return false;
	}
}
