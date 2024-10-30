package org.mystickers.java.controller;

import java.io.IOException;

import org.mystickers.java.model.Sticker;
import org.mystickers.java.service.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/stickers")
public class StickerController {

	@Autowired
	StickerService stickerService;

	// INDEX
	@GetMapping()
	public String index(Model model) {

		model.addAttribute("stickers", stickerService.getAllSortedByIdDesc());

		return "stickers/index";
	}

	// SEARCH
	@GetMapping("/search")
	public String search(@RequestParam String text, Model model) {

		model.addAttribute("stickers", stickerService.getByTextContainingOrderByIdDesc(text));

		return "stickers/index";
	}

	// CREATE
	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("sticker", new Sticker());

		return "stickers/create";
	}

	// STORE
	@PostMapping("/create")
	public String store(Model model, @RequestParam String text, @RequestParam MultipartFile file,
			RedirectAttributes attributes) throws IOException {

		stickerService.saveSticker(text, file);

		attributes.addFlashAttribute("successMessage", "new sticker added");

		return "redirect:/stickers";
	}

	// EDIT
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {

		model.addAttribute("sticker", stickerService.getById(id));

		return "stickers/edit";
	}

	// UPDATE
	@PostMapping("/edit/{id}")
	public String update(@PathVariable int id, Model model, @RequestParam String text, @RequestParam MultipartFile file,
			RedirectAttributes attributes) throws IOException {

		stickerService.updateSticker(stickerService.getById(id), text, file);

		attributes.addFlashAttribute("successMessage", "sticker edited");

		return "redirect:/stickers";
	}

	// DELETE
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id, RedirectAttributes attributes) {

		stickerService.deleteById(id);

		attributes.addFlashAttribute("successMessage", "sticker deleted");

		return "redirect:/stickers";
	}

	// DOWNLOAD
	@GetMapping("/download/{id}")
	public ResponseEntity<byte[]> download(@PathVariable int id) {

		Sticker sticker = stickerService.getById(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + sticker.getFileName() + "\"")
				.body(sticker.getFile());
	}

}
