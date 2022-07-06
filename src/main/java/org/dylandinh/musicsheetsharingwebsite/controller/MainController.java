package org.dylandinh.musicsheetsharingwebsite.controller;

import java.util.ArrayList;
import java.util.List;

import org.dylandinh.musicsheetsharingwebsite.model.Sheet;
import org.dylandinh.musicsheetsharingwebsite.repository.SheetRepository;
import org.dylandinh.musicsheetsharingwebsite.repository.UserRepository;
import org.dylandinh.musicsheetsharingwebsite.service.SheetService;
import org.dylandinh.musicsheetsharingwebsite.upload.SheetInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired
	SheetRepository sheetRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	SheetService sheetService;

	@GetMapping("/")
	public String root(Model model) {
		Sheet sheet1 = sheetRepository.findById(46);
		Sheet sheet2 = sheetRepository.findById(56);
		List<Sheet> sheetsList = new ArrayList<Sheet>();
		sheetsList.add(sheet1);
		sheetsList.add(sheet2);
		List<SheetInput> sheets = sheetService.convertSheet(sheetsList);
		
		model.addAttribute("sheets", sheets);
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
}