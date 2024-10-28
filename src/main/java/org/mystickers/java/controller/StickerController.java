package org.mystickers.java.controller;

import org.mystickers.java.model.Sticker;
import org.mystickers.java.service.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/stickers")
public class StickerController {

	@Autowired
	StickerService stickerService;

	// INDEX
	@GetMapping()
	public String index(Model model) {

		model.addAttribute("stickers", stickerService.getAllOrderedByCreatedAt());

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
	public String store(@Valid @ModelAttribute("sticker") Sticker stickerForm, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "stickers/create";
		}

		stickerService.save(stickerForm);

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
	public String update(@Valid @ModelAttribute("sticker") Sticker stickerForm, BindingResult bindingResult,
			Model model, RedirectAttributes attributes) {

		if (bindingResult.hasErrors()) {
			return "stickers/edit";
		}

		stickerService.save(stickerForm);

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

}
