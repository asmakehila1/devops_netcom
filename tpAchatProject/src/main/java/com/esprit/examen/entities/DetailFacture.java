package com.esprit.examen.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailFacture implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDetailFacture;
	private Integer qteCommandee;
	private float prixTotalDetail;
	private Integer pourcentageRemise;
	private float montantRemise;
	@ManyToOne
	private Produit produit;
	@ManyToOne
	@JsonIgnore
	Facture facture;
	public Produit getProduit() {
		// TODO Auto-generated method stub
		return null;
	}
	public float getQteCommandee() {
		// TODO Auto-generated method stub
		return 0;
	}
	public float getPourcentageRemise() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setMontantFacture(float montantRemiseDetail) {
		// TODO Auto-generated method stub
		
	}
	public void setPrixTotalDetail(float prixTotalDetailRemise) {
		// TODO Auto-generated method stub
		
	}

}
