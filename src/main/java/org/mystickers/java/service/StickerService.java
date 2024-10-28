package org.mystickers.java.service;

import java.util.List;

import org.mystickers.java.model.Sticker;
import org.mystickers.java.repo.StickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StickerService {
	
	@Autowired
	StickerRepository stickerRepo;
	
	public List<Sticker> getAllOrderedByCreatedAt() {

		return stickerRepo.findAllOrderByCreatedAtDesc();

	}

}
