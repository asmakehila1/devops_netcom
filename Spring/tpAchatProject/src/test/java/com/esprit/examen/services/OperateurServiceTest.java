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


import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)

public class OperateurServiceTest {
	@Autowired
	IOperateurService OperateurService;
	
	@Mock
	OperateurRepository OperateurRepository;
	
	@InjectMocks
	OperateurServiceImpl OperateurServiceImpl;
	
	
	@Test
	@Order(1)
	public void testAddOperateur()
	{
		List<Operateur> operateurs = OperateurService.retrieveAllOperateurs();
		Operateur o = new Operateur();
		o = new Operateur("nom_op1", "prenom_op1", "op1_password", null);
		Operateur savedOperateur = OperateurService.addOperateur(o);
		assertTrue(savedOperateur.getNom().length() > 0);
		assertTrue(savedOperateur.getPrenom().length() > 0);
		assertTrue(PasswordValidator.isValid(savedOperateur.getPassword()));
		OperateurService.deleteOperateur(savedOperateur.getIdOperateur());
	}
	



	@Test
	@Order(2)
	public void  retrieveAllOperateurs()
	{
		Operateur op = new Operateur();
		op = new Operateur("nom_op1", "prenom_op1", "op1_password", null);
		OperateurService.addOperateur(op);
		List<Operateur> operateurs = OperateurService.retrieveAllOperateurs();	
		assertNotNull(operateurs);
    	System.out.println("all operateur retrieved");

	}
	
	@Test
	@Order(3)
	public void updateOperateur()
	{
		Operateur operateur = OperateurService.retrieveOperateur((long) 2);
		assertNotNull(operateur);
		
		Operateur op = new Operateur() ;
		op.setNom("op2-nom");
		op.setPrenom("op2-prenom");
		op.setPassword("op2-password");
		Operateur savedOperateur = OperateurService.updateOperateur(operateur);
		assertNotNull(savedOperateur);
		
	}
	
	@Test
	@Order(4)
	public void deleteOperateur() 
	{
		Operateur op = new Operateur("nom_op1", "prenom_op1", "op1_password", null);
		Operateur savedOperateur = OperateurService.addOperateur(op);
		assertNotNull(savedOperateur);
		OperateurService.deleteOperateur(savedOperateur.getIdOperateur());
		assertNull(OperateurService.retrieveOperateur(savedOperateur.getIdOperateur()));
		
	}
	
	@Test
	@Order(5)
	public void compareSavedDataToNew()
	{
		Operateur op = new Operateur((long) 2,"nom_op1", "prenom_op1", "op1_password", null);
			when(OperateurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(op));
			assertThat(OperateurRepository.findById((long) 2)).isEqualTo(op);
		
	}
	
	

}
