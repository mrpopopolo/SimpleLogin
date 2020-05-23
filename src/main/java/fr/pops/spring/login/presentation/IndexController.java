package fr.pops.spring.login.presentation;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.pops.spring.login.business.entity.User;
import fr.pops.spring.login.business.service.UserService;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userServ;
	
	@GetMapping({"/index", "/"})
	public String index(User user, Model model) {
		model.addAttribute(user);
		return "index.html";
	}
	
	@PostMapping("/newUser")
	public String newUser(User user) {
		this.userServ.createUser(user);
		return "login.html";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login.html";
	}
	
	@GetMapping("/valid/{token}")
	public String enableUser(@PathVariable String token) {
		this.userServ.validateUser(token);
		return "redirect:/index";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password) {
		if(this.userServ.login(email, password)) {
			return "index.html";
		}
		else {return "login.html";}
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("name", userServ.loadUserByUsername(principal.getName()).getName());
		return "dashboard.html";
	}
}
