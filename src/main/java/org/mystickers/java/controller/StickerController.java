package org.mystickers.java.controller;

import org.mystickers.java.service.StickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
