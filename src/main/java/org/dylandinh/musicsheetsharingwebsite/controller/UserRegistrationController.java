package org.dylandinh.musicsheetsharingwebsite.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.dylandinh.musicsheetsharingwebsite.model.User;
import org.dylandinh.musicsheetsharingwebsite.security.UserRegistrationDto;
import org.dylandinh.musicsheetsharingwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
			BindingResult result) {

		User existing = userService.findByEmail(userDto.getEmail());
		if(existing != null) {
			result.rejectValue("email", null, "There is already an account registered with that email");
		}
		
		existing = userService.findByUsername(userDto.getUsername());
		if(existing != null) {
			result.rejectValue("username", null, "Username is already taken");
		}
		
		String email = userDto.getEmail();
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()) {
			result.rejectValue("email", null, "Please enter valid email");
		}
		
		String password = userDto.getPassword();
		if(password.length() < 6) {
			result.rejectValue("password", null, "Password must be 6 characters or more");
		}
		
		String confirmPassword = userDto.getConfirmPassword();
		if(!password.equals(confirmPassword)) {
			result.rejectValue("confirmPassword", null, "Please enter the same password");
		}

		if(result.hasErrors()) {
			return "registration";
		}

		userService.save(userDto);
		return "redirect:/registration?success";
	}
}
