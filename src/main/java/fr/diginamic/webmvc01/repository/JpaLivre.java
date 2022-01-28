package fr.diginamic.webmvc01.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.webmvc01.model.Livre;

public interface JpaLivre  extends CrudRepository<Livre, Integer>{

}
