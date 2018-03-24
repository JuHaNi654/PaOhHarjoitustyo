package fi.hh.PaOh.Harjoitustyo.webControll;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.hh.PaOh.Harjoitustyo.model.SignUpForm;
import fi.hh.PaOh.Harjoitustyo.model.User;
import fi.hh.PaOh.Harjoitustyo.model.UserRepository;

@Controller
public class AccountController {
	@Autowired
	private UserRepository urepository;
	
	//Controller lähettää pyynnön thymeleaf signup tiedostolle ja tuo signup näkymä
	@RequestMapping(value="/signup")
	public String addNewUser(Model model) {
		model.addAttribute("signupform", new SignUpForm());
		return "signup";
	}
	
	//Methodi Tallentaa luodun käyttäjän, jos ei tulee Minkäälaista erroria 
	//ja jos tietyllä nimellä ei ole jo luotu käyttäjää
	@PostMapping(value="/saveuser")
	public String saveUser(@Valid @ModelAttribute("signupform") SignUpForm signUpForm, BindingResult bindingResult) {
		if(!bindingResult.hasErrors()) {
			if(signUpForm.getPassword().equals(signUpForm.getPasswordCheck())) {
				String password = signUpForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPassword = bc.encode(password);
				
				User newUser = new User();
				newUser.setPasswordHash(hashPassword);
				newUser.setUsername(signUpForm.getUsername());
				newUser.setEmail(signUpForm.getUserEmail());
				newUser.setRole("USER");
				if(urepository.findByUsername(signUpForm.getUsername()) == null) {
					urepository.save(newUser);
				}
				else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			}
			else {
				bindingResult.rejectValue("passwordCheck", "err.passwordCheck", "Password does not match");
				return "signup";
			}
		}
		else {
			return "signup";
		}
		return "redirect:/login";
	}
}
