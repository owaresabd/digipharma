package com.pms.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.model.ItemInfo;
import com.pms.model.User;
import com.pms.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	@Autowired
	private IUserService userService;
	@Autowired
	private ItemRepository itemRepository;

	public List<ItemInfo> getAll(String status) {
		User user = userService.getCurrentUser();
		if (status != null) {
			return itemRepository.findAll().stream()
					.filter(info -> info.getStatus().equals(status) && info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getItemName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		} else {
			return itemRepository.findAll().stream()
					.filter(info ->info.getCompanyId().equals(user.getCompanyId()))
					.sorted(Comparator.comparing(c -> c.getItemName(), String.CASE_INSENSITIVE_ORDER))
					.collect(Collectors.toList());

		}
	}

	

	public void saveOrUpdate(ItemInfo info) {
		User user = userService.getCurrentUser();
		info.setCompanyId(user.getCompanyId());
		
		if (info.getId() == null ) {
			info.setCreatedBy(user.getId());

		} else {
			
			info.setUpdatedBy(user.getId());
		}
		itemRepository.save(info);
		
	}
	

}
