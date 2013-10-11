package de.planspiel.test;

import static org.junit.Assert.*;

import org.junit.*;

import de.planspiel.cafe.Haendler;
import de.planspiel.cafe.Haendlertyp;
import de.planspiel.cafe.PreisQualitaetVerhaeltnis;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.spiel.Zufall;

public class HaendlerTest {
	Haendler haendler;
	
	@BeforeClass
	public void vorbereiten(){
		Zufall.setzeTestmodus(true);
		Zufall.setzeTestQualitaet(0.8);
	}
	
	@Before 
	public void erstelleSUT(){
		haendler = new Haendler(Haendlertyp.Haendler1);
	}
	
	@Test
	public void testenErstellenAngebot(){
		Double[] ekArray = new Double[Produkttyp.values().length];
		for (int i = 0; i< ekArray.length;i++){  //Array erstellen, das erwartete EKPreise enthält
			ekArray[i] = Produkttyp.values()[i].holeMaxEK() * PreisQualitaetVerhaeltnis.berechnenPreisFaktor(0.8);
		}
		
		haendler.generierenAngebot();
		for (int i = 0; i<haendler.holeProduktliste().size(); i++){
			assertEquals(haendler.holeProduktliste().get(i).holeName(), Produkttyp.values()[i]);
			assertEquals(haendler.holeProduktliste().get(i).holeQualitaet(), 0.8, 0.0);
			assertEquals(haendler.holeProduktliste().get(i).holeEkpreis(), ekArray[i], 0.0);	
		}
	}
	
	@AfterClass
	public void beenden(){
		Zufall.setzeTestmodus(false);
	}
	
}
