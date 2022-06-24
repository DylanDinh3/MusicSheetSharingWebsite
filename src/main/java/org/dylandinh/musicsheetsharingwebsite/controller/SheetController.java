package org.dylandinh.musicsheetsharingwebsite.controller;

import org.dylandinh.musicsheetsharingwebsite.model.User;
import org.dylandinh.musicsheetsharingwebsite.repository.SheetRepository;
import org.dylandinh.musicsheetsharingwebsite.repository.UserRepository;
import org.dylandinh.musicsheetsharingwebsite.service.SheetService;
import org.dylandinh.musicsheetsharingwebsite.upload.SheetInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SheetController {
	@Autowired
	SheetRepository sheetRepository;

	@Autowired
	SheetService sheetService;

	@Autowired
	UserRepository userRepository;

	@GetMapping("/sheets/add")
	public String showAddForm(Model model) {
		model.addAttribute("sheetInput", new SheetInput());
		return "add";
	}

	@PostMapping("/sheets/addSheet")
	public String addSheet(SheetInput sheetInput, @AuthenticationPrincipal UserDetails currentUser) {
		User user = (User)userRepository.findByUsername(currentUser.getUsername());
		sheetRepository.save(sheetService.createNewSheet(user, sheetInput));
		return "redirect:/sheets/add?success";
	}
}
