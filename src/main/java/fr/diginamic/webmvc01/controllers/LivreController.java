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

import fr.diginamic.webmvc01.execptions.ErrorLivre;
import fr.diginamic.webmvc01.model.Client;
import fr.diginamic.webmvc01.model.Livre;
import fr.diginamic.webmvc01.repository.JpaLivre;

@Controller
@RequestMapping("/livre")
public class LivreController {
	
	@Autowired
	JpaLivre jpaLivre;
	
	private String message;
	
	public LivreController() {
		
	}
	
	private void verifLivre(Integer lid) throws ErrorLivre {
		if(jpaLivre.findById(lid).isEmpty()) {
			throw new ErrorLivre ("N° du clivre " + lid + " n'existe pas veuillez le creez!");
		}
	}
	/**
	 * liste des livres
	 * @param model
	 * @return
	 */
	@GetMapping("/livres")
	public String findall(Model model) {
		model.addAttribute("livres", (List<Livre>)jpaLivre.findAll());
		model.addAttribute("titre", "liste des livres");
		return "livres/Liste";	
	}
	
	/**
	 * lien avec la page livres/add
	 * @param model
	 * @return
	 */
	@GetMapping("/add")
	public String addT(Model model) {
		model.addAttribute("livreForm", new Livre());
		model.addAttribute("titre", "Ajouter livre");
		return "livres/add";
		
		
	}
	/**
	 * traite les donne valider par l utilisateur a traver le formulaire ajout 
	 * livre et renvoi ver la liste de livres
	 * @param model
	 * @param livreForm
	 * @return
	 */
	@PostMapping("/add")
	public String add(Model model ,@Valid @ModelAttribute("livreForm") Livre livreForm) {
		jpaLivre.save(livreForm);
		return "redirect:/livre/livres";
		
			}
	/**
	 * supprimer un livre via son id 
	 * @param lid
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer lid) throws Exception {
		Optional<Livre> l = jpaLivre.findById(lid);
		verifLivre(lid);
		jpaLivre.deleteById(lid);
		return "redirect:/livre/livres";
	}
	
	/**
	 * renvoie vers livres/update pour update  autheur et titre via son id 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/update/{id}")
	public String edit(Model model,@PathVariable("id")Integer id) {
		Livre l = jpaLivre.findById(id).get();
		model.addAttribute("livreForm",l);		
		return "livres/update";
	}
	/**
	 * enregistrement des donnee en base de donnees
	 * @param livreForm
	 * @param result
	 * @return
	 */
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("livreForm") Livre livreForm, BindingResult result)throws ErrorLivre {
		if(result.hasErrors()) {
			message = "";

			result.getFieldErrors().forEach(e -> {
				message += e.getField() + " - " + e.getDefaultMessage() + " * ";
			}); throw new ErrorLivre(message);
		}
	
	jpaLivre.save(livreForm);
	
	return "redirect:/livre/livres";
	}
	
	
}
