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

import fr.diginamic.webmvc01.execptions.ErrorEmprunt;
import fr.diginamic.webmvc01.model.Client;
import fr.diginamic.webmvc01.model.Emprunt;
import fr.diginamic.webmvc01.model.Livre;
import fr.diginamic.webmvc01.repository.JpaClient;
import fr.diginamic.webmvc01.repository.JpaEmprunt;
import fr.diginamic.webmvc01.repository.JpaLivre;

@Controller
@RequestMapping("/emprunt")
public class EmpruntController {
	
	@Autowired
	JpaEmprunt  jpaEmprunt;
	
	@Autowired
	JpaClient jpaClient;
	
	@Autowired
	JpaLivre jpaLivre;
	
	private String message;
	
	public EmpruntController() {
		
	}
	
		
	/**
	 * recuepration et affichage liste des emprunt 
	 * @param model
	 * @return
	 */
	@GetMapping("/emprunts")
	public String findall(Model model) {
		model.addAttribute("emprunts", (List<Emprunt>)jpaEmprunt.findAll());
		
		model.addAttribute("titre", "liste des emprunt");
		return "emprunts/Liste";	
	}
	
	@GetMapping("/add")
	public String addT(Model model) {
		List<Livre> listLivres=(List<Livre>)jpaLivre.findAll();
		List<Client> listClients=(List<Client>)jpaClient.findAll();
		model.addAttribute("empruntForm", new Emprunt());
		model.addAttribute("listLivres",listLivres);
		model.addAttribute("listClients",listClients);
		model.addAttribute("titre", "Ajouter emprunt");
		
		return "emprunts/add";
		
		
	}
	/**
	 * Ajout  d'emprunt 
	 * @param model
	 * @param empruntForm
	 * @param result
	 * @return
	 * @throws Exception
	 */
	
	@PostMapping("/add")
	public String add(Model model , @Valid @ModelAttribute("empruntForm") Emprunt empruntForm ,BindingResult result) throws Exception {
		
		
		if(result.hasErrors()) {
			System.out.println(result);
			throw (new Exception(result.toString()));
		}
		jpaEmprunt.save(empruntForm);
		empruntForm.getEmpruntLivres().forEach(l->{
			l.getLivreEmpruntes().add(empruntForm);
			jpaLivre.save(l);
		});
		return "redirect:/emprunt/emprunts";
		
			}
	
	/**
	 * recuperation lsite livres et liste emprunts pour renvoyer au formulaire
	 * et parmettre la mise a jour  
	 * @param eid
	 * @param model
	 * @return
	 */
	@GetMapping("/update/{id}")
	public String updateT(@PathVariable ("id") Integer eid,  Model model) {
		Emprunt emprunt =jpaEmprunt.findById(eid).get();
				List<Livre> listLivres=(List<Livre>)jpaLivre.findAll();
				List<Client> listClients=(List<Client>)jpaClient.findAll();
				model.addAttribute("listLivres",listLivres);
				model.addAttribute("listClients",listClients);
				model.addAttribute("empruntForm", emprunt);
				model.addAttribute("titre", "Modifier emprunt");
				return "emprunts/update";
				
		
	}
	/**
	 * validation des modif faite a l emprunt via formulaire 
	 * @param empruntForm
	 * @param result
	 * @return
	 */
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("empruntForm") Emprunt empruntForm,BindingResult result)throws ErrorEmprunt {
		if(result.hasErrors()) {
			message="";
			result.getFieldErrors().forEach(e -> {
				message += e.getField() + " - " + e.getDefaultMessage() +" * ";
			});
			throw new ErrorEmprunt(message);
		
			
		}
		
		jpaEmprunt.save(empruntForm);
		empruntForm.getEmpruntLivres().forEach(l->{
			l.getLivreEmpruntes().add(empruntForm);
			jpaLivre.save(l);
		});
		return "redirect:/emprunt/emprunts";
		
	}
	

	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer lid) throws Exception {
		Emprunt e  = jpaEmprunt.findById(lid).get();
		jpaEmprunt.findByLivre(e).forEach(l->{
			l.getLivreEmpruntes().remove(e);
			jpaLivre.save(l);
		});
		
		jpaEmprunt.deleteById(lid);
		return "redirect:/emprunt/emprunts";
	}
	

	

}
