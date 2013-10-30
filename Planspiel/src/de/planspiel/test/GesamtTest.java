package de.planspiel.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.*;

import de.planspiel.cafe.*;
import de.planspiel.entscheidung.*;
import de.planspiel.ereignis.*;
import de.planspiel.konsolengui.EntscheidungTreffenGUI;
import de.planspiel.konsolengui.KonsolenGUI;
import de.planspiel.konsolengui.SpielBeendenGUI;
import de.planspiel.konsolengui.SpielStartenGUI;
import de.planspiel.spiel.*;

public class GesamtTest {
	private static Spiel spiel;
	
	
	@BeforeClass
	public static void vorbereiten(){
		Zufall.setzeTestmodus(true);
	}
	
	@Before
	public void erstelleSUT(){
		//Beim Zufall den richtigen Testmodus (Zahlen einlesen) angeben
		KonsolenGUI.setzeTestModus(true);
		Zufall.setzeTestmodus(true);
		Zufall.setzeDateiTestmodus(true);
		//Zufall.setzeProtokollModus(true);
		spiel = new Spiel();
		//Zufall.schliessenProtokoll();
		
	}
	
	/*@Test
	public void testenSpielen(){
		spiel.spielen();
	}*/
		
	
	@AfterClass
	public static void beenden(){
		Zufall.setzeTestmodus(false);
		KonsolenGUI.setzeTestModus(false);
		}
}
