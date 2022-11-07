package com.esprit.examen.services;


import static org.junit.Assert.*;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.esprit.examen.entities.Reglement;



import java.text.ParseException;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ReglementServiceImplTest {
	@Autowired
	IReglementService reglementService;
	
	@Test
	public void testAddReglement() {
		//	List<Reglement> = reglementService.retrieveAllReglements();
		//	int expected=reglement.size();
		Reglement r = new Reglement();
			Reglement savedReglement= reglementService.addReglement(r);
			
		//	assertEquals(expected+1, reglementServic.retrieveAllReglements().size());
			assertNotNull(savedReglement.getIdReglement());
			reglementService.retrieveReglement(savedReglement.getIdReglement());
			
		} 
	
	
	@Test
	public void testRetrieveAllReglements() throws ParseException {
		List<Reglement> reglements = reglementService.retrieveAllReglements();
		int expected = reglements.size();
		Reglement r = new Reglement();
		Reglement reglement = reglementService.addReglement(r);
		assertEquals(expected + 1, reglementService.retrieveAllReglements().size());
		reglementService.retrieveReglement(reglement.getIdReglement());

	}
	
	
	

}
