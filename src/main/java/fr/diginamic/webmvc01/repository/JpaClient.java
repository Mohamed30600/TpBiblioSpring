package fr.diginamic.webmvc01.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.webmvc01.model.Client;
import fr.diginamic.webmvc01.model.Emprunt;

public interface JpaClient extends CrudRepository<Client, Integer> {
	
	@Query("select emp from Emprunt emp where emp.clientEmprunt.id= :id")
	public Iterable<Emprunt> getEmpruntByClient(Integer id);

}


