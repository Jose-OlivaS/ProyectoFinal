package com.example.proyectofinalbackend.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.proyectofinalbackend.appdata.AppDataImpl;

@Controller
public class LoginController {
	
	private final AppDataImpl appData;
	
	public LoginController(AppDataImpl appData) {
		this.appData = appData;
	}


	@GetMapping("/login")
	public String login(
			@RequestParam(value="error", required=false) String error, 
			@RequestParam(value="logout", required=false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal != null) 
			return "redirect:/";

		if(logout != null)
			model.addAttribute("exitoso","haz cerrado sesion");

		if(error != null) 
			model.addAttribute("error","usuario o contraseña incorrecta");
		
		model.addAttribute("applicationData", appData);
			
		return "login/login";
	}
	
}
