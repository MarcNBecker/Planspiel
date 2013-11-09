package de.planspiel.test;

import static org.junit.Assert.*;

import org.junit.*;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Marktanteil;
import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Report;
import de.planspiel.cafe.Standort;
import de.planspiel.cafe.Standorttyp;
import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.spiel.Spiel;

/**
 * Test der Klasse Filiale, der die Methoden verkaufen und pruefenKundenprodukt
 * testet.
 * 
 * @author Daniel Degraf
 */
public class FilialeTest {

	private Standort standort;
	private Unternehmenskette ukette;
	private Filiale fil1;
	private Spiel spiel = new Spiel();
	private Report report;
	private Marktanteil mAnteil;

	@Before
	public void erstelleSUT() {

		standort = new Standort(Standorttyp.Standort1);
		ukette = new Unternehmenskette("KetteNummer1");
		report = new Report(0, ukette);
		ukette.hinzufuegenReport(report);
		mAnteil = new Marktanteil();
		spiel.setzeAktuellerMarktanteil(mAnteil);
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
		assertEquals(fil1.holeFreieKapazitaet(), 99);
	}

	@Test
	public void testenPruefenKundenprodukt() {
		Produkt kundenProdukt = new Produkt(Produkttyp.KAFFEE, 5);
		kundenProdukt.setzePreis(15);
		kundenProdukt.setzeQualitaet(0.4);
		assertEquals(fil1.pruefenKundenprodukt(kundenProdukt), true);
	}

	@After
	public void zuruecksetzen() {
		ukette.holeLager().einlagern(new Produkt(Produkttyp.KAFFEE, 10));
	}
}
