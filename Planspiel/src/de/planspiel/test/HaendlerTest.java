package de.planspiel.test;

import static org.junit.Assert.*;

import org.junit.*;

import de.planspiel.cafe.Haendler;
import de.planspiel.cafe.Haendlertyp;
import de.planspiel.cafe.PreisQualitaetVerhaeltnis;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.spiel.Zufall;

/**
 * Test der Klasse Haendler, der die Angebotserstellung testet, wobei eine feste
 * Testqualität für alle Produkte gesetzt wird.
 * 
 * @author Natalie Buchner
 */
public class HaendlerTest {
	Haendler haendler;

	@BeforeClass
	public static void vorbereiten() {
		Zufall.setzeTestmodus(true);
		Zufall.setzeTestQualitaet(0.8);
	}

	@Before
	public void erstelleSUT() {
		haendler = new Haendler(Haendlertyp.Haendler1);
	}

	@Test
	public void testenErstellenAngebot() {
		Double[] ekArray = new Double[Produkttyp.values().length];
		for (int i = 0; i < ekArray.length; i++) { // Array erstellen, das
													// erwartete EKPreise
													// enthält
			ekArray[i] = Produkttyp.values()[i].holeMaxEK() * PreisQualitaetVerhaeltnis.berechnenPreisFaktor(0.8);
		}

		for (int j = 0; j < haendler.holeProduktliste().size(); j++) {
			assertEquals(haendler.holeProduktliste().get(j).holeName(), Produkttyp.values()[j]);
			assertEquals(haendler.holeProduktliste().get(j).holeQualitaet(), 0.8, 0.0);
			assertEquals(haendler.holeProduktliste().get(j).holeEkpreis(), ekArray[j], 0.0);
		}
	}

	@After
	public void zuruecksetzen() {
		haendler = null;
	}

	@AfterClass
	public static void beenden() {
		Zufall.setzeTestmodus(false);
	}

}
