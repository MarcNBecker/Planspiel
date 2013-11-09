package de.planspiel.test;

import static org.junit.Assert.*;

import org.junit.*;

import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;

/**
 * Test der Klasse Produkte, der die Methoden Vergleichen und Verschmelzen testet.
 * @author Natalie Buchner
 */
public class ProduktTest {

	private Produkt testProdukt1;
	private Produkt testProdukt2;

	@Before
	public void erstelleSUT() {
		testProdukt1 = new Produkt(Produkttyp.KAFFEE);
		testProdukt2 = new Produkt(Produkttyp.KAFFEE);
	}

	@Test
	public void testenVergleichen() {
		// Stellt Kundenprodukt dar mit Obergrenze Preis und Untergrenze
		// Qualitaet
		testProdukt1.setzePreis(7.50);
		testProdukt1.setzeQualitaet(0.60);
		// Stellt existierendes Produkt dar
		testProdukt2.setzePreis(4.30);
		testProdukt2.setzeQualitaet(0.70);
		assertEquals(testProdukt1.vergleichen(testProdukt2), true);
	}

	@Test
	public void testenVerschmelzen() {
		// Werte für erstes Produkt "im Lager"
		testProdukt1.setzeEkpreis(3.20);
		testProdukt1.setzeQualitaet(0.7);
		testProdukt1.setzeMenge(20);
		// Werte für zweites Produkt, welches dazukommt
		testProdukt2.setzeEkpreis(4.90);
		testProdukt2.setzeQualitaet(0.9);
		testProdukt2.setzeMenge(10);

		double testEK = 113.0 / 30.0;
		double testQuali = 23.0 / 30.0;

		testProdukt1.verschmelzen(testProdukt2);
		assertEquals(testProdukt1.holeEkpreis() == testEK && testProdukt1.holeQualitaet() == testQuali && testProdukt1.holeMenge() == 30, true);
	}

	@After
	public void zuruecksetzen() {
		testProdukt1 = null;
		testProdukt2 = null;
	}
}
