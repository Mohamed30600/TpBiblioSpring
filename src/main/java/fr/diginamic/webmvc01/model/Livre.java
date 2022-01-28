package fr.diginamic.webmvc01.model;

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
@Entity
public class Livre {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int id ;
	@Column(name="TITRE",length = 255,unique=true)
	private String titre;
	@Column(name="AUTEUR",length = 255,unique=true)
	private String auteur;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable (name="compo",
		joinColumns=@JoinColumn(name="ID_LIV",referencedColumnName = "ID"),
		inverseJoinColumns = @JoinColumn(name="ID_EMP",referencedColumnName="ID"))
	private Set<Emprunt> livreEmpruntes;
	
	public Livre() {
		super();
	}


	public Livre(String titre) {
		super();
		this.titre = titre;
	}


	public Livre(int id, String titre, String auteur) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getAuteur() {
		return auteur;
	}


	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}


	public Set<Emprunt> getLivreEmpruntes() {
		return livreEmpruntes;
	}


	public void setLivreEmpruntes(Set<Emprunt> livreEmpruntes) {
		this.livreEmpruntes = livreEmpruntes;
	}


	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", auteur=" + auteur 
				+ "]";
	}


	

	
	
	
}
