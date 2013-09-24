package de.planspiel.cafe;

import java.util.Vector;

import static org.junit.Assert.*;
import org.junit.*;

public class LagerTest {
	
	private Unternehmenskette kette;
	private Lager lager;
	private Vector<Produkt> einkaufsliste;
	private Haendler haendler;
	
	@Before 
	public void erstelleSUT(){
		kette = new Unternehmenskette("Test");
		lager = new Lager(kette);
		einkaufsliste = new Vector<Produkt>();
		einkaufsliste.add(new Produkt(Produkttyp.KAFFEE, 0.5, 4));
		haendler = new Haendler();
		haendler.generierenAngebot();
	}
	
	@Test
	public void testenProduktEinlagern(){		
		lager.einlagern(einkaufsliste.get(0));
		assertEquals(lager.suchenProdukt(Produkttyp.KAFFEE), einkaufsliste.get(0));
	}
	
	/*@Test 
	public void testenProduktAuslagern(){
		lager.einlagern(einkaufsliste.get(0));
		Produkt ausgelagertesProdukt = lager.auslagern(Produkttyp.KAFFEE, 10);
		if (einkaufsliste.get(0).holeMenge() < 10){
			assertEquals(ausgelagertesProdukt, einkaufsliste.get(0));
		}			
		else {
			einkaufsliste.get(0).setzeMenge(10);
			assertEquals(ausgelagertesProdukt, einkaufsliste.get(0));
		}
	}*/
	
	/*@Test
	public void testenProduktEinkaufen(){
		lager.einkaufen(einkaufsliste, haendler);
		
	}*/
	
	@After
	public void zuruecksetzen(){
		kette = null;
		lager = null;
		einkaufsliste = null;
		haendler = null;
	}
}
