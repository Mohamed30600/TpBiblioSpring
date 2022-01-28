package fr.diginamic.webmvc01.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.webmvc01.model.Emprunt;
import fr.diginamic.webmvc01.model.Livre;

public interface JpaEmprunt extends CrudRepository<Emprunt, Integer>{
	
	
	@Query("select l from Livre l where :emp MEMBER OF l.livreEmpruntes")
    public Iterable<Livre> findByLivre(Emprunt emp);
	



}
