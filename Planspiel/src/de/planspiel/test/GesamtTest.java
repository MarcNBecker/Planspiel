package de.planspiel.test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import de.planspiel.konsolengui.KonsolenGUI;
import de.planspiel.spiel.Spiel;
import de.planspiel.spiel.Zufall;

public class GesamtTest extends SiegbedingungsTest {
	
	private Spiel spiel;
	
	@Before
	public void erstelleSUT(){
		KonsolenGUI.setzeTestModus(true, "gesamtTest/gesamtTestInput.txt", "gesamtTest/gesamtTestOutput.txt", this);
		Zufall.setzeTestmodus(true);
		Zufall.setzeDateiTestmodus(true, "gesamtTest/gesamtTestZufall.txt");
		//Zufall.setzeProtokollModus(true, "gesamtTest/gesamtTestZufall.txt");
		spiel = new Spiel(3);
	}
	
	@Test
	public void testenSpielen(){
		spiel.spielen();
		assertEquals(holeSiegbedingung(), holeSiegerfuellung());
	}
		
	
	@After
	public void zerstoereSUT(){
		//Zufall.schliessenProtokoll();
		//Zufall.setzeProtokollModus(false);
		Zufall.setzeDateiTestmodus(false);
		Zufall.setzeTestmodus(false);
		KonsolenGUI.setzeTestModus(false);
	}
}
