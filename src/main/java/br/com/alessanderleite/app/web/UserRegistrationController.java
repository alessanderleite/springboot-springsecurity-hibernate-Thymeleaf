package br.com.alessanderleite.app.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alessanderleite.app.model.User;
import br.com.alessanderleite.app.service.UserService;
import br.com.alessanderleite.app.web.dto.UserRegistrationDto;

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
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
									BindingResult result) {
		
		User existing = userService.findByEmail(userDto.getEmail());
		if (existing != null) {
			result.rejectValue("email", null, "There is already an account registered with this email");;
		}
		
		if (result.hasErrors()) {
			return "registration";
		}
		
		userService.save(userDto);
		return "redirect:/registration?success";
	}
}
