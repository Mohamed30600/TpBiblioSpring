package fr.diginamic.webmvc01.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.InstanceManagerBindings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.diginamic.webmvc01.model.Client;
import fr.diginamic.webmvc01.repository.JpaClient;


@Controller
@RequestMapping("/client")
public class ClientContoller {
	
	@Autowired
	JpaClient jpaClient;

	public ClientContoller() {	
		
			}
	
	@GetMapping("/clients")
	public String findall(Model model) {
		model.addAttribute("clients", (List<Client>)jpaClient.findAll());
		model.addAttribute("titre", "liste des clients");
		return "clients/Liste";	
	}
	
	
	@GetMapping("/add")
	public String addT(Model model) {
		model.addAttribute("clientForm", new Client());
		model.addAttribute("titre", "Ajouter client");
		return "clients/add";
		
	}
	@PostMapping("/add")
	public String add(Model model , @Valid @ModelAttribute("clientForm") Client clientForm , BindingResult result) {
		if(result.hasErrors()) {
			//gere les erreur
		}
		jpaClient.save(clientForm);
		return "redirect:/client/clients";
		
			}
		
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws Exception {
		Optional<Client> c = jpaClient.findById(pid);
		if (c.isEmpty()) {
			/**
			 * declation exception clienterror
			 */
			throw (new Exception("Client id :" + pid + "n existe pas !"));
		}
		jpaClient.deleteById(pid);
		return "redirect:/client/clients";
	}
	
	@GetMapping("/update/{id}")
	public String edit(Model model,@PathVariable("id")Integer id) {
		Client p = jpaClient.findById(id).get();
		model.addAttribute("clientForm",p);		
		return "clients/update";
	}
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("clientForm") Client clientForm, BindingResult result) {
		if(result.hasErrors()) {
			//gere les erreur
		}
	
	jpaClient.save(clientForm);
	
	return "redirect:/client/clients";
	}
	

}
