package org.mystickers.java.service;

import java.io.IOException;
import java.util.List;

import org.mystickers.java.model.Sticker;
import org.mystickers.java.repo.StickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	public void saveSticker(String text, MultipartFile file) throws IOException {

		Sticker sticker = new Sticker();

		sticker.setText(text);

		sticker.setFile(file.getBytes());

		sticker.setFileName(file.getOriginalFilename());

		stickerRepo.save(sticker);

	}

	public void updateSticker(Sticker sticker, String text, MultipartFile file) throws IOException {

		sticker.setText(text);

		if (file.getOriginalFilename().length() > 0) {
			sticker.setFile(file.getBytes());
			sticker.setFileName(file.getOriginalFilename());
		}

		stickerRepo.save(sticker);

	}

	public void deleteById(int id) {

		stickerRepo.deleteById(id);

	}

}
