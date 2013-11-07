package de.planspiel.test;

import static org.junit.Assert.*;

import org.junit.*;

import de.planspiel.cafe.PreisQualitaetVerhaeltnis;
import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;

public class PreisQualitaetVTest {

	Produkt produkt1;
	Produkt produkt2;

	@Before
	public void erstellenSUT() {
		produkt1 = new Produkt(Produkttyp.KAFFEE);
		produkt1.setzeQualitaet(0.3);
		produkt2 = new Produkt(Produkttyp.TEE);
		produkt2.setzeQualitaet(0.8);
	}

	@Test
	public void testenBerechnenPreisfaktor() {
		assertEquals(PreisQualitaetVerhaeltnis.berechnenPreisFaktor(produkt1.holeQualitaet()), 0.177767, 0.00001);
		assertEquals(PreisQualitaetVerhaeltnis.berechnenPreisFaktor(produkt2.holeQualitaet()), 0.507395, 0.00001);
	}

	@After
	public void zuruecksetzen() {
		produkt1 = null;
		produkt2 = null;
	}

}
