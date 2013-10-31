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
	private Spiel spiel;
	
	@BeforeClass
	public static void vorbereiten(){
		KonsolenGUI.setzeTestModus(true);
		//Zufall.setzeTestmodus(true);
		//Zufall.setzeDateiTestmodus(true);
		//Zufall.setzeProtokollModus(true);
	}
	
	@Before
	public void erstelleSUT(){
		spiel = new Spiel();
		
	}
	
	@Test
	public void testenSpielen(){
		spiel.spielen();
	}
		
	
	@AfterClass
	public static void beenden(){
		//Zufall.schliessenProtokoll();
		//Zufall.setzeProtokollModus(false);
		//Zufall.setzeDateiTestmodus(false);
		//Zufall.setzeTestmodus(false);
		KonsolenGUI.setzeTestModus(false);
	}
}
