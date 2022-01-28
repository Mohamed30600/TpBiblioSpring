package fr.diginamic.webmvc01.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Emprunt {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_DEBUT")
	private Date  date_debut;
	@Column(name="DELAI")
	private int delai;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="DATE_FIN")
	@Temporal(TemporalType.DATE)
	private Date date_fin;
	
	@ManyToOne
	@JoinColumn(name="id_client")
	private Client clientEmprunt;
	
	@Transient
	private Set<Livre> empruntLivres;
	
	
	
	
	
	
public Set<Livre> getEmpruntLivres() {
		return empruntLivres;
	}

	public void setEmpruntLivres(Set<Livre> empruntLivres) {
		this.empruntLivres = empruntLivres;
	}

public Emprunt() {
	empruntLivres= new HashSet<>();
		
	}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Date getDate_debut() {
	return date_debut;
}

public void setDate_debut(Date date_debut) {
	this.date_debut = date_debut;
}

public int getDelai() {
	return delai;
}

public void setDelai(int delai) {
	this.delai = delai;
}

public Date getDate_fin() {
	return date_fin;
}

public void setDate_fin(Date date_fin) {
	this.date_fin = date_fin;
}

public Client getClientEmprunt() {
	return clientEmprunt;
}

public void setClientEmprunt(Client clientEmprunt) {
	this.clientEmprunt = clientEmprunt;
}



@Override
public String toString() {
	return "Emprunt [id=" + id + ", date_debut=" + date_debut + ", delai=" + delai + ", date_fin=" + date_fin
			+ "]";
}
	
	


	
	
}
