package com.abccars.AbcCarsProject.Users;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.abccars.AbcCarsProject.SecurityConfiguration.CustomUserDetails;




@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@GetMapping("/Adminprofile")
	public String Adminprofile(Principal principal, Model model) {

		String username = principal.getName();
		System.out.println("Username" + username);
		User userdetails = userRepository.findByEmail(username);
		System.out.println("User" + userdetails);
		model.addAttribute("title", "To show profile of user ");
		model.addAttribute("user", userdetails);
		
		return "adminprofile";
	}

	@GetMapping("/profile")
	public String profile(Principal principal, Model model) {

		System.out.println("user_dashboard page only for user");
		String username = principal.getName();
		System.out.println("Username" + username);
		User userdetails = userRepository.findByEmail(username);
		System.out.println("User" + userdetails);
		model.addAttribute("title", "To show profile of user ");
		model.addAttribute("user", userdetails);
		System.out.println("To show data in dashboard");
		return "userprofile";
	}

	
	@GetMapping("/editprofile/edit/{id}")
	public String editUserProfile(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("user", user);
		
		return "usereditform";
	}
	
	@PostMapping("/updateusernow/save")
	public String UpdatedUsers(User user) {
		service.save(user);
		
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String Landing() {
		return "index";
	}
	
	@GetMapping("/Homepage")
	public String viewHomePage() {
		return "homepage1";
	}
	
	@GetMapping("/about-us")
	public String Aboutus() {
		return "aboutus1";
	}
	
	@GetMapping("/contact-us")
	public String CotactUs() {
		return "contactus1";
	}
	
	
	@GetMapping("/login")
	public String Loginpage() {
		return "login";
	}
	
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	
	
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		service.registerDefaultUser(user);
		
		return "register_success";
	}
	
	@GetMapping("/users")
	public String user() {
		return "homepage1";
	}
	
	

}
