package de.planspiel.test;

/**
 * @author Daniel
 */

import org.junit.*;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Report;
import de.planspiel.cafe.Standort;
import de.planspiel.cafe.Standorttyp;
import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.spiel.Spiel;
import de.planspiel.spiel.Zufall;

public class KundeTest {
	private Standort standort;
	
	private Unternehmenskette ukette;
	private Unternehmenskette ukette1;
	private Filiale fil1;
	private Filiale fil2;
	private static int ctr;
	
	@BeforeClass
	public static void erstelleSUTgesamt() {
		ctr=0;
	}
	
	@Before
	public void erstelleSUT() {
		//Spiel mit Anzahl der Runden wird erstellt.
		Spiel spiel = new Spiel();
		
		//Für jeden Spieler eine Unternehmenskette, damit eine Konkurrenzsituation entsteht
		ukette  = new Unternehmenskette("KetteNummer1");
		ukette1 = new Unternehmenskette("KetteNummer2");
		//Es werden für Unternehmenskette ein Report erstellt. Pro Runde brauchen wir eigentlich ein Report für jede Kette.
		Report report = new Report(1, ukette);
		Report report1 = new Report(1, ukette1);
		ukette.hinzufuegenReport(report);
		ukette1.hinzufuegenReport(report1);
		//Dem Spiel werden die Unternehmensketten zugeordnet
		spiel.hinzufuegenUnternehmenskette(ukette);
		spiel.hinzufuegenUnternehmenskette(ukette1);
		// Ein Standort, an dem die Ketten konkurrieren sollen, wird angelegt
		
		
		//für den Kunden:
		// Praeferenz für ALLE ist Qualität
		Zufall.setzeTestmodus(true);
		Zufall.setzeTestZufallszahl(2);
		Zufall.setzeTestQualitaet(0.4);
		standort = new Standort(Standorttyp.Standort1);
		fil1 = new Filiale(standort, ukette);
		fil1.setzeMitarbeiter(1);
		fil1.initialisierenKapazitaet();
		fil2 = new Filiale(standort, ukette1);
		fil2.setzeMitarbeiter(1);
		fil2.initialisierenKapazitaet();
		Zufall.setzeTestmodus(false);
		standort.beeinflussenKunden(ukette, 99);
		standort.beeinflussenKunden(ukette1, 99);
		Zufall.setzeTestmodus(true);
		Zufall.setzeTestZufallszahl(2);
		Zufall.setzeTestQualitaet(0.4);
		Produkt p1 = new Produkt(Produkttyp.KAFFEE, 20);
		Produkt p2 = new Produkt(Produkttyp.KUCHEN, 10);
		p1.setzePreis(1);
		p1.setzeQualitaet(0.56);
		p2.setzePreis(1.2);
		p2.setzeQualitaet(0.6);
		ukette.holeLager().einlagern(p1);
		ukette.holeLager().einlagern(p2);
		p1.setzePreis(0.8);
		p1.setzeQualitaet(0.5);
		ukette1.holeLager().einlagern(p1);	
		// Kette1 bietet Kaffee (P:1; Q:0.56) und Kuchen (P:1.2; Q:0.6) an
		// Kette2 bietet Kaffe (P:0.8, Q:0.5) an.
	}
	
	@Test
	public void testenSimulierenEinkauf() {
		System.out.println("Kap. UKette: " + fil1.holeFreieKapazitaet());
		System.out.println("Kap. UKette1: " + fil2.holeFreieKapazitaet());
		// Praeferenz ist die Qualität
		for (int i=0; i<standort.holeKundenkreis().size();i++) {
			standort.holeKundenkreis().get(i).simulierenEinkauf();
			System.out.println("Kap. UKette: " + fil1.holeFreieKapazitaet());
			System.out.println("Kap. UKette1: " + fil2.holeFreieKapazitaet());
		}
		
	}
	
}
