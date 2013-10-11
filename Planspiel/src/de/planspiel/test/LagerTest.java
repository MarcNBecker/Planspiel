package de.planspiel.test;

import java.util.Vector;

import static org.junit.Assert.*;

import org.junit.*;

import de.planspiel.cafe.Haendler;
import de.planspiel.cafe.Haendlertyp;
import de.planspiel.cafe.Lager;
import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Unternehmenskette;

public class LagerTest {
	
	private Unternehmenskette kette;
	private Lager lager;
	private Vector<Produkt> einkaufsliste;
	private Haendler haendler;
	
	@Before 
	public void erstelleSUT(){
		//Zufall.setzeTestmodus(true); Zufall.setzeTestZufallszahl(5.0)
		kette = new Unternehmenskette("Test");
		lager = new Lager(kette);
		einkaufsliste = new Vector<Produkt>();
		haendler = new Haendler(Haendlertyp.Haendler1);
		haendler.generierenAngebot(); //Hier kommen Zufallszahlen rein - das wollen wir nicht!
	}
	
	@Test
	public void testenProduktEinlagern(){	
		einkaufsliste.add(new Produkt(Produkttyp.KAFFEE, 0.5, 4));	
		lager.einlagern(einkaufsliste.get(0));
		
		assertEquals(lager.suchenProdukt(Produkttyp.KAFFEE), einkaufsliste.get(0));
	}
	
	@Test 
	public void testenProduktAuslagernMehr(){
		einkaufsliste.add(new Produkt(Produkttyp.TEE, 50)); //Name und Menge übergeben
		lager.einlagern(einkaufsliste.get(0));
		Produkt ausgelagertesProdukt = lager.auslagern(Produkttyp.TEE, 10);
		
		assertEquals(ausgelagertesProdukt.holeName() == Produkttyp.TEE 
					&& ausgelagertesProdukt.holeMenge() == 10, 
					true);
	}
	
	@Test 
	public void testenProduktAuslagernWeniger(){
		einkaufsliste.add(new Produkt(Produkttyp.TEE, 5)); //Name und Menge übergeben
		lager.einlagern(einkaufsliste.get(0));
		Produkt ausgelagertesProdukt = lager.auslagern(Produkttyp.TEE, 10);
		
		assertEquals(ausgelagertesProdukt.holeName() == Produkttyp.TEE 
					&& ausgelagertesProdukt.holeMenge() == 5, 
					true);
	}
	
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
		//Zufall.setzeTestmodus(false);
	}
}
