package de.planspiel.cafe;

import static org.junit.Assert.*;
import org.junit.*;

public class FilialeTest {

	private Standort standort;
	private Unternehmenskette ukette;
	private Filiale fil1;
	
	@Before
	public void erstelleSUT() {
		standort = new Standort();
		ukette  = new Unternehmenskette("KetteNummer1");
		fil1 = new Filiale(standort, ukette);
		fil1.setzeMitarbeiter(1);
		fil1.initialisierenKapazitaet();
		Produkt p1 = new Produkt(Produkttyp.KAFFEE, 20);
		p1.setzePreis(12);
		p1.setzeQualitaet(0.56);
		ukette.holeLager().einlagern(p1);
	}
	
	@Test
	public void testenVerkaufen() {
		fil1.verkaufen(Produkttyp.KAFFEE, 10);
		Produkt vergleichsProdukt = new Produkt(Produkttyp.KAFFEE, 20);
		//assertEquals(ukette.holeLager().suchenProdukt(Produkttyp.KAFFEE).holeMenge(), 10);
		assertEquals(fil1.holeFreieKapazitaet(),0);
	}
	
	@Test
	public void testenPruefenKundenprodukt() {
		Produkt kundenProdukt = new Produkt(Produkttyp.KAFFEE,5);
		kundenProdukt.setzePreis(15);
		kundenProdukt.setzeQualitaet(0.4);
		assertEquals(fil1.pruefenKundenprodukt(kundenProdukt),true);
	}
	
	/*@Test
	 *Mietkosten und Gehalt muss hier festgelegt werden
	public void testenBerechnenKosten() {
		fil1.berechnenKosten();
	}
	*/
	
	@After
	public void zurücksetzen() {
		ukette.holeLager().einlagern(new Produkt(Produkttyp.KAFFEE,10));
	}
}
