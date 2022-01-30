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

import fr.diginamic.webmvc01.execptions.ErrorClient;
import fr.diginamic.webmvc01.model.Client;
import fr.diginamic.webmvc01.repository.JpaClient;
import fr.diginamic.webmvc01.repository.JpaEmprunt;
import fr.diginamic.webmvc01.repository.JpaLivre;

@Controller
@RequestMapping("/client")
public class ClientContoller {

	@Autowired
	JpaClient jpaClient;

	@Autowired
	JpaEmprunt jpaEmprunt;

	@Autowired
	JpaLivre jpaLivre;
	
	private String message;

	public ClientContoller() {

	}
	
	

	/**
	 * liste des clients
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/clients")
	public String findall(Model model) {
		model.addAttribute("clients", (List<Client>) jpaClient.findAll());
		model.addAttribute("titre", "liste des clients");
		return "clients/Liste";
	}
	
	private void verifClient(Integer pid) throws ErrorClient{
		if(jpaClient.findById(pid).isEmpty()) {
			throw new ErrorClient("N de client : "+ pid + "  non trouvé !");
		}
	}
	/**
	 * lien avec le template client/add
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/add")
	public String addT(Model model) {
		model.addAttribute("clientForm", new Client());
		model.addAttribute("titre", "Ajouter client");
		return "clients/add";

	}

	/**
	 * recuperation du formulaire client et enregistrement dans la base de donnes
	 * 
	 * @param model
	 * @param clientForm
	 * @param result
	 * @return
	 */
	@PostMapping("/add")
	public String add(Model model, @Valid @ModelAttribute("clientForm") Client clientForm, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			System.out.println(result);
			throw (new Exception(result.toString()));
		}
		jpaClient.save(clientForm);
		return "redirect:/client/clients";

	}
		/**
		 * suppression du client via son id recupere préalablement
		 * @param pid
		 * @return
		 * @throws Exception
		 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pid) throws Exception {
		Optional<Client> c = jpaClient.findById(pid);
		verifClient(pid);
		jpaClient.getEmpruntByClient(pid).forEach(e -> {
			//suppresion de tous liens avec la table compo
			jpaEmprunt.findByLivre(e).forEach(l -> {
				//on enleve l'emprunt rattacher a chaque livres
				l.getLivreEmpruntes().remove(e);
				//sauvegarde avec suppression des liens livres
				jpaLivre.save(l);
			});
			//sauvegarde avec suppression des liens emprunt
			jpaEmprunt.delete(e);
		});
		//le client peut etre suprimer et tous les lient sont automatiquement supprimer
		jpaClient.deleteById(pid);
		return "redirect:/client/clients";
	}

	
	/**
	 * mise a jour client meme  operation que ci-dessu mais avec id donc update
	 * @param clientForm
	 * @param result
	 * @return
	 */

	@GetMapping("/update/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Client p = jpaClient.findById(id).get();
		model.addAttribute("clientForm", p);
		return "clients/update";
	}
	
	@PostMapping("/update")
	public String update(@Valid @ModelAttribute("clientForm") Client clientForm, BindingResult result)throws ErrorClient {
		if(result.hasErrors()) {
			message="";
			result.getFieldErrors().forEach(e->{
				message += e.getField() + "-" + e.getDefaultMessage()+"*";
			});
			throw new  ErrorClient(message);
		}

		jpaClient.save(clientForm);

		return "redirect:/client/clients";
	}

}
