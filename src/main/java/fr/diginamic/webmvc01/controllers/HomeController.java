package fr.diginamic.webmvc01.controllers;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	
public HomeController() {
	
}

@GetMapping
public String home(Model model) {
	String dtitre = "welcome..";
	model.addAttribute("titre", dtitre);
	return "home";
}
}
