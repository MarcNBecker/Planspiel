package de.planspiel.test;

import java.util.Vector;

import static org.junit.Assert.*;

import org.junit.*;

import de.planspiel.cafe.Haendler;
import de.planspiel.cafe.Haendlertyp;
import de.planspiel.cafe.Lager;
import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Report;
import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.spiel.Spiel;
import de.planspiel.spiel.Zufall;

/**
 * Test der Klasse Lager, der die Methoden Einkaufen, Einlagern und Auslagern
 * testet, wobei bei testenAuslagern zwei verschiedene Fälle getestet werden:
 * Fall 1: Die noch im Lager vorhandene Menge des Produkts ist größer als die
 * auszulagernde Menge Fall 2: Die noch im Lager vorhandene Menge des Produkts
 * ist kleiner als die auszulagernde Menge
 * 
 * @author Natalie Buchner
 */
public class LagerTest {

	private Spiel spiel;
	private Unternehmenskette kette;
	private Lager lager;
	private Vector<Produkt> einkaufsliste;
	private Haendler haendler;
	private Report report;

	@Before
	public void erstelleSUT() {
		Zufall.setzeTestmodus(true);
		Zufall.setzeTestZufallszahl(5.0);
		spiel = new Spiel();
		kette = new Unternehmenskette("Test");
		lager = new Lager(kette);
		einkaufsliste = new Vector<Produkt>();
		haendler = new Haendler(Haendlertyp.Haendler1);
	}

	@Test
	public void testenEinlagern() {
		einkaufsliste.add(new Produkt(Produkttyp.KAFFEE, 0.5, 4));
		lager.einlagern(einkaufsliste.get(0));

		assertEquals(lager.suchenProdukt(Produkttyp.KAFFEE), einkaufsliste.get(0));
	}

	@Test
	public void testenAuslagernMehr() {
		// Fall 1: Es ist mehr auf Lager als ausgelagert werden soll
		einkaufsliste.add(new Produkt(Produkttyp.TEE, 50));
		lager.einlagern(einkaufsliste.get(0));
		Produkt ausgelagertesProdukt = lager.auslagern(Produkttyp.TEE, 10);

		assertEquals(ausgelagertesProdukt.holeName() == Produkttyp.TEE && ausgelagertesProdukt.holeMenge() == 10, true);
	}

	@Test
	public void testenAuslagernWeniger() {
		// Fall 2: Es ist weniger auf Lager als ausgelagert werden soll
		einkaufsliste.add(new Produkt(Produkttyp.TEE, 5));
		lager.einlagern(einkaufsliste.get(0));
		Produkt ausgelagertesProdukt = lager.auslagern(Produkttyp.TEE, 10);

		assertEquals(ausgelagertesProdukt.holeName() == Produkttyp.TEE && ausgelagertesProdukt.holeMenge() == 5, true);
	}

	@Test
	public void testenEinkaufen() {
		report = new Report(1, kette);
		kette.hinzufuegenReport(report);
		// Report vorbereiten, damit Kosten verbucht werden können!
		spiel.setzeAktuelleRunde(1);
		Vector<Produkt> kaufliste = new Vector<Produkt>();
		kaufliste.add(new Produkt(Produkttyp.KAFFEE, 100));
		kaufliste.add(new Produkt(Produkttyp.KUCHEN, 50));
		lager.einkaufen(kaufliste, haendler);
		assertEquals(lager.holeProduktliste().get(0).holeEkpreis(), 0.012833, 0.001);
		assertEquals(lager.holeProduktliste().get(1).holeEkpreis(), 0.0171113, 0.001);
	}

	@Test
	public void testenBerechnenWert() {
		einkaufsliste.add(new Produkt(Produkttyp.TEE, 10));
		einkaufsliste.get(0).setzeEkpreis(3.0);
		einkaufsliste.add(new Produkt(Produkttyp.KAFFEE, 20));
		einkaufsliste.get(1).setzeEkpreis(4.5);
		lager.einlagern(einkaufsliste.get(0));
		lager.einlagern(einkaufsliste.get(1));
		assertEquals(lager.berechnenWert(), (120.0), 0.0);
	}

	@After
	public void zuruecksetzen() {
		kette = null;
		lager = null;
		einkaufsliste = null;
		haendler = null;
		Zufall.setzeTestmodus(false);
	}
}
