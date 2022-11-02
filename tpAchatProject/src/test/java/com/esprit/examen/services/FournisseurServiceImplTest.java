package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.DetailFournisseur;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FournisseurServiceImplTest {
	@Autowired
	IFournisseurService fournisseurService;
	
	@Ignore
	public void testAddFournisseur()
	{
		List<Fournisseur> fournisseurs = fournisseurService.retrieveAllFournisseurs();
		Fournisseur f = new Fournisseur();
		int expected = fournisseurs.size();
		 f = new Fournisseur((long) 3, "FO1", "fournisseur produits laitier", CategorieFournisseur.ORDINAIRE, 
				 null, null, null);
		Fournisseur savedFournisseur = fournisseurService.addFournisseur(f);
		assertEquals(expected+1, fournisseurService.retrieveAllFournisseurs().size());
		assertNotNull(savedFournisseur.getLibelle());
		
	}
	
	@Ignore
	public void  retrieveAllFournisseurs()
	{
		List<Fournisseur> fournisseurs = fournisseurService.retrieveAllFournisseurs();
		for (Fournisseur fournisseur : fournisseurs) {
			log.info(" fournisseur : " + fournisseur.getCode());
		}
	}
	
	@Test
	public void updateFournisseur()
	{
		Fournisseur fournisseur = fournisseurService.retrieveFournisseur((long) 3);
		assertNotNull(fournisseur);
		
		DetailFournisseur df = new DetailFournisseur() ;
		df.setFournisseur(fournisseur);
		df.setEmail("fournisseur@gmail.com");
		df.setAdresse("tunis");
		df.setMatricule("FFFk");
		
		fournisseur.setDetailFournisseur(df);
		
		Fournisseur savedFourniseur = fournisseurService.updateFournisseur(fournisseur);
		assertNotNull(savedFourniseur);
		assertNotNull(savedFourniseur.getDetailFournisseur());
		
	}

}
