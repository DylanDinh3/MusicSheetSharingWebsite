package org.dylandinh.musicsheetsharingwebsite.controller;

import java.util.List;

import javax.validation.Valid;

import org.dylandinh.musicsheetsharingwebsite.model.Sheet;
import org.dylandinh.musicsheetsharingwebsite.model.User;
import org.dylandinh.musicsheetsharingwebsite.repository.SheetRepository;
import org.dylandinh.musicsheetsharingwebsite.repository.UserRepository;
import org.dylandinh.musicsheetsharingwebsite.service.SheetService;
import org.dylandinh.musicsheetsharingwebsite.upload.SheetInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SheetController {
	@Autowired
	SheetRepository sheetRepository;

	@Autowired
	SheetService sheetService;

	@Autowired
	UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(SheetController.class);

	@GetMapping("/sheets")
	public String showSheetsPage(Model model) {
		List<SheetInput> sheets = sheetService.convertSheet(sheetRepository.findAll());
		model.addAttribute("sheets", sheets);
		logger.debug("In sheets page.");
		return "sheets";
	}

	@GetMapping("/sheets/add")
	public String showAddForm(Model model) {
		model.addAttribute("sheetInput", new SheetInput());
		logger.debug("In add page.");
		return "add";
	}

	@PostMapping("/sheets/addSheet")
	public String addSheet(@ModelAttribute(value = "sheetInput") SheetInput sheetInput, Authentication authentication) {
		User user = (User)userRepository.findByEmail(authentication.getName());
		sheetInput.setUser(user);

		sheetRepository.save(sheetService.createNewSheet(sheetInput));
		logger.debug("Successfully created new music sheet.");
		
		return "redirect:/profile?success";
	}

	@GetMapping("/sheets/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int sheetId, Model model) {
		Sheet sheet = sheetRepository.findById(sheetId);
		SheetInput sheetInput = new SheetInput();
		sheetInput.setId(sheetId);
		sheetInput.setTitle(sheet.getTitle());
		sheetInput.setComposers(
				sheet.getComposers().toString().substring(1, sheet.getComposers().toString().length() - 1));
		sheetInput.setInstruments(
				sheet.getInstruments().toString().substring(1, sheet.getInstruments().toString().length() - 1));
		sheetInput.setImages(sheet.getImagesPath());
		
		model.addAttribute("sheetInput", sheetInput);
		logger.debug("In edit page.");
		return "edit";
	}

	@PostMapping("/sheets/edit/{id}")
	public String editSheet(@PathVariable("id") int sheetId, @Valid SheetInput sheetInput, BindingResult result) {
		sheetInput.setId(sheetId);
		
		Sheet sheet = sheetRepository.findById(sheetId);
		sheetInput.setUser(sheet.getUser());
		
		sheetRepository.save(sheetService.updateSheet(sheetInput));
		logger.debug("Successfully updated sheet.");
		return "redirect:/profile?editsuccess";
	}
	
	@GetMapping("/sheets/delete/{id}")
	public String deleteSheet(@PathVariable("id") int sheetId, Model model) {
		Sheet sheet = sheetRepository.findById(sheetId);
		sheetRepository.delete(sheet);
		logger.debug("Successfully deleted sheet.");
		return "redirect:/profile?deletesuccess";
	}
	
	@GetMapping("/profile")
	public String profile(Model model, Authentication authentication) {
		User user = userRepository.findByEmail(authentication.getName());
		List<SheetInput> sheets = sheetService.convertSheet(sheetRepository.findByUser(user));
		model.addAttribute("user", user);
		model.addAttribute("sheets", sheets);
		return "profile";
	}
}
