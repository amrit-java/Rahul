package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	@Autowired
	 UserService userService;
	
	@GetMapping({"/", "/viewUserList"})
	public String viewUserList(@ModelAttribute("message") String message, Model model) {
		model.addAttribute("userList" ,userService.getAllUsers());
	
		return "ViewUserList";	
	}
	
	@GetMapping("/addUser")
	public String addUser(@ModelAttribute("message") String message, Model model) {
		model.addAttribute("user", new User());
		return "AddUser";
	}
	
@PostMapping("/saveUser")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		if(userService.saveOrUpdateUser(user)) {
			
			return "redirect:/viewUserList";
		}
		return "redirect:/addUser";
	}

//EDITUSER
@GetMapping("/editUser/{id}")
public String editUser(@PathVariable Long id, Model model) {
	model.addAttribute("user", userService.getUserById(id));
	return "EditUser";
		
	}

@PostMapping("/editSaveUser")
public String editSaveUser(User user, RedirectAttributes redirectAttributes) {
	if(userService.saveOrUpdateUser(user)) {
		//redirectAttributes.addFlashAttribute("message","edit success");
		return "redirect:/viewUserList";
	}
	//redirectAttributes.addFlashAttribute("message","edit Failure");
	return "redirect:/editUser" +user.getId();
}

//delete
@GetMapping("/deleteUser/{id}")
public String deleteUser(@PathVariable Long id,RedirectAttributes redirectAttributes) {
	if(userService.deleteUser(id)) {
		//redirectAttributes.addFlashAttribute("message", "delete success");
	//}else {
	//redirectAttributes.addFlashAttribute("message","delete Failure");	
}
return "redirect:/viewUserList";
}
}
