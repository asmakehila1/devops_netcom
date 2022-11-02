package com.esprit.examen.services;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import com.esprit.examen.entities.DetailFournisseur;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
public class FournisseurServiceImplTest {
	@Autowired
	IFournisseurService fournisseurService;
	
	@Mock
	SecteurActiviteRepository secteurRepo;
	
	@InjectMocks
	SecteurActiviteServiceImpl secteurService;
	
	@Ignore
	public void testAddFournisseur()
	{
		List<Fournisseur> fournisseurs = fournisseurService.retrieveAllFournisseurs();
		Fournisseur f = new Fournisseur();
		int expected = fournisseurs.size();
		f = new Fournisseur("FO1", "fournisseur produits laitier", CategorieFournisseur.ORDINAIRE, 
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
	
	@Ignore
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
	
	@Ignore
	public void deleteFournisseur() 
	{
		Fournisseur f = new Fournisseur("FO2", "fournisseur produits xxxx", 
				CategorieFournisseur.CONVENTIONNE,  null, null, null);
		Fournisseur savedFournisseur = fournisseurService.addFournisseur(f);
		assertNotNull(savedFournisseur);
		fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
		assertNull(fournisseurService.retrieveFournisseur(savedFournisseur.getIdFournisseur()));
		
	}
	
	@Test
	public void assignSecteurActiviteToFournisseur()
	{
		SecteurActivite secActivite = new SecteurActivite((long) 1, "sec004", "secteur ariana", null);
		when(secteurRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(secActivite));
		
		SecteurActivite retrievedSecteurActivite = secteurService.retrieveSecteurActivite((long) 1);
		assertNotNull(retrievedSecteurActivite);
		
		
		Fournisseur fournisseur = fournisseurService.retrieveFournisseur((long) 7);
		assertNotNull(fournisseur);
		
		assertTrue(fournisseurService.assignSecteurActiviteToFournisseur(
				retrievedSecteurActivite.getIdSecteurActivite(), fournisseur.getIdFournisseur()));
	}
	
	

}
