package fr.diginamic.webmvc01.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import fr.diginamic.webmvc01.model.Livre;
import fr.diginamic.webmvc01.repository.JpaLivre;

@Controller
@RequestMapping("/livre")
public class LivreController {
	
	@Autowired
	JpaLivre jpaLivre;
	
	public LivreController() {
				
	}
	@GetMapping("/livres")
	public String findall(Model model) {
		model.addAttribute("livres", (List<Livre>)jpaLivre.findAll());
		model.addAttribute("titre", "liste des livres");
		return "livres/Liste";	
	}
	
	@GetMapping("/add")
	public String addT(Model model) {
		model.addAttribute("livreForm", new Livre());
		model.addAttribute("titre", "Ajouter livre");
		return "livres/add";
		
		
	}
	
	@PostMapping("/add")
	public String add(Model model ,@Valid @ModelAttribute("livreForm") Livre livreForm) {
		jpaLivre.save(livreForm);
		return "redirect:/livre/livres";
		
			}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer lid) throws Exception {
		Optional<Livre> l = jpaLivre.findById(lid);
		if (l.isEmpty()) {
			/**
			 * declation exception livreerror
			 */
			throw (new Exception("Livre id :" + lid + "n existe pas !"));
		}
		jpaLivre.deleteById(lid);
		return "redirect:/livre/livres";
	}
	
	@GetMapping("/update/{id}")
	public String edit(Model model,@PathVariable("id")Integer id) {
		Livre l = jpaLivre.findById(id).get();
		model.addAttribute("livreForm",l);		
		return "livres/update";
	}
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("livreForm") Livre livreForm, BindingResult result) {
		if(result.hasErrors()) {
			//gere les erreur
		}
	
	jpaLivre.save(livreForm);
	
	return "redirect:/livre/livres";
	}
	
	
}
