package de.planspiel.test;


import org.junit.*;

import de.planspiel.cafe.Filiale;
import de.planspiel.cafe.Produkt;
import de.planspiel.cafe.Produkttyp;
import de.planspiel.cafe.Report;
import de.planspiel.cafe.Standort;
import de.planspiel.cafe.Unternehmenskette;
import de.planspiel.spiel.Spiel;

public class KundeTest {
	private Standort standort;
	
	private Unternehmenskette ukette;
	private Unternehmenskette ukette1;
	private Filiale fil1;
	private Filiale fil2;
	
	@Before
	public void erstelleSUT() {
		Spiel spiel = new Spiel(2);
		
		ukette  = new Unternehmenskette("KetteNummer1");
		ukette1 = new Unternehmenskette("KetteNummer2");
		Report report = new Report(1, ukette);
		Report report1 = new Report(1, ukette1);
		ukette.hinzufuegenReport(report);
		ukette1.hinzufuegenReport(report1);
		spiel.hinzufuegenUnternehmenskette(ukette);
		spiel.hinzufuegenUnternehmenskette(ukette1);
		standort = new Standort(5000, 1000, 100, 200, 5);
		fil1 = new Filiale(standort, ukette);
		fil1.setzeMitarbeiter(1);
		fil1.initialisierenKapazitaet();
		fil2 = new Filiale(standort, ukette1);
		fil2.setzeMitarbeiter(1);
		fil2.initialisierenKapazitaet();
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
	}
	
	@Test
	public void testenSimulierenEinkauf() {
		for (int i=0; i<standort.holeKundenkreis().size();i++) {
			standort.holeKundenkreis().get(i).simulierenEinkauf();
		}
	}
	
}
