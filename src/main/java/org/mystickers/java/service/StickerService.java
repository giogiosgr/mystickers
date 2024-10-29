package org.mystickers.java.service;

import java.util.List;

import org.mystickers.java.model.Sticker;
import org.mystickers.java.repo.StickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class StickerService {

	@Autowired
	StickerRepository stickerRepo;

	public List<Sticker> getAllSortedByIdDesc() {

		return stickerRepo.findAllByOrderByIdDesc();

	}

	public List<Sticker> getByTextContainingOrderByIdDesc(String text) {

		return stickerRepo.findByTextContainingOrderByIdDesc(text);

	}

	public Sticker getById(Integer id) {

		return stickerRepo.findById(id).get();

	}

	public void save(@Valid Sticker stickerForm) {

		stickerRepo.save(stickerForm);

	}

	public void deleteById(int id) {

		stickerRepo.deleteById(id);

	}

}
