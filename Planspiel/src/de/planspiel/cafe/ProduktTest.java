package de.planspiel.cafe;

import static org.junit.Assert.*;
import org.junit.*;

public class ProduktTest {
	
	private Produkt vergleichProdukt;
	private Produkt testProdukt1;
	private Produkt testProdukt2;	
	
	@Before
	public void erstelleSUT(){
		testProdukt1 = new Produkt(Produkttyp.KAFFEE);
		testProdukt2 = new Produkt(Produkttyp.KAFFEE);
		vergleichProdukt = new Produkt(Produkttyp.KAFFEE);		
	}
	
	@Test
	public void testenVergleichen(){
		//Stellt Kundenprodukt dar mit Obergrenze Preis und Untergrenze Qualitaet
		testProdukt1.setzePreis(7.50);
		testProdukt1.setzeQualitaet(0.60);
		//Stellt existierendes Produkt dar
		testProdukt2.setzePreis(4.30);
		testProdukt2.setzeQualitaet(0.70);
		assertEquals(testProdukt1.vergleichen(testProdukt2), true);
	}
	
	@Test
	public void testenVerschmelzen(){
		//Werte f�r erstes Produkt "im Lager"
		testProdukt1.setzeEkpreis(3.20);
		testProdukt1.setzeQualitaet(0.7);
		testProdukt1.setzeMenge(20);
		//Werte f�r zweites Produkt, welches dazukommt
		testProdukt2.setzeEkpreis(4.90);
		testProdukt2.setzeQualitaet(0.9);
		testProdukt2.setzeMenge(10);
		//Werte f�r das Vergleichsprodukt festlegen
		vergleichProdukt.setzeMenge(30);
		vergleichProdukt.setzeEkpreis(113/30);
		vergleichProdukt.setzeQualitaet(23/30);
		assertEquals(testProdukt1.verschmelzen(testProdukt2), vergleichProdukt);
		
	}
	
	@After
	public void zuruecksetzen(){
		vergleichProdukt = null;
		testProdukt1 = null;
		testProdukt2 = null;
	}
}
