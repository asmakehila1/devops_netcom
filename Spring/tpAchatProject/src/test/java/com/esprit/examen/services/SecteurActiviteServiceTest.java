package com.esprit.examen.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.ManyToMany;

import org.junit.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)

public class SecteurActiviteServiceTest {
	@Autowired
	ISecteurActiviteService SecteurActiviteService;
	@Mock
	SecteurActiviteRepository SecteurActiviteRepository;
	
	@InjectMocks
	SecteurActiviteServiceImpl SecteurActiviteServiceImpl;
	
	@Test
	@Order(1)
	public void testAddSecteurActivite()
	{
		List<SecteurActivite> secteurAcitivites = SecteurActiviteService.retrieveAllSecteurActivite();
		SecteurActivite sa = new SecteurActivite();
		sa = new SecteurActivite("sa01", "sa_libelle", null);
		SecteurActivite savedSecteurAcitivites = SecteurActiviteService.addSecteurActivite(sa);
		assertTrue(savedSecteurAcitivites.getLibelleSecteurActivite().length() > 0);
		assertTrue(savedSecteurAcitivites.getCodeSecteurActivite().length() > 0);
		SecteurActiviteService.deleteSecteurActivite(savedSecteurAcitivites.getIdSecteurActivite());
	}
	



	@Test
	@Order(2)
	public void  retrieveAllSecteurActivite()
	{
		SecteurActivite sa = new SecteurActivite();
		sa = new SecteurActivite("sa01", "sa1_libelle", null);
		SecteurActiviteService.addSecteurActivite(sa);
		List<SecteurActivite> SecteurActivites = SecteurActiviteService.retrieveAllSecteurActivite();	
		assertNotNull(SecteurActivites);
    	System.out.println("all SecteurActivite retrieved");

	}
	
	@Test
	@Order(3)
	public void updateSecteurActivite()
	{
		SecteurActivite secteurActivite = SecteurActiviteService.retrieveSecteurActivite((long) 2);
		assertNotNull(secteurActivite);
		
		SecteurActivite sa = new SecteurActivite() ;
		sa.setCodeSecteurActivite("sa02");
		sa.setLibelleSecteurActivite("sa2_libelle");
		SecteurActivite savedSecteurActivite = SecteurActiviteService.updateSecteurActivite(secteurActivite);
		assertNotNull(savedSecteurActivite);
		
	}
	
	@Test
	@Order(4)
	public void deleteSecteurActivite() 
	{
		SecteurActivite sa = new SecteurActivite("sa01", "sa1_libelle", null);
		SecteurActivite savedSecteurActivite = SecteurActiviteService.addSecteurActivite(sa);
		assertNotNull(savedSecteurActivite);
		SecteurActiviteService.deleteSecteurActivite(savedSecteurActivite.getIdSecteurActivite());
		assertNull(SecteurActiviteService.retrieveSecteurActivite(savedSecteurActivite.getIdSecteurActivite()));
		
	}
	
	@Test
	@Order(5)
	public void compareSavedDataToNew()
	{
		
			SecteurActivite sa = new SecteurActivite((long) 2, "sa01", "sa1_libelle", null);
			when(SecteurActiviteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(sa));
			assertThat(SecteurActiviteRepository.findById((long) 2)).isEqualTo(sa);
		
	}
	
	

}
