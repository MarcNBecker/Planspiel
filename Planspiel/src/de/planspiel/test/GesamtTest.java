package de.planspiel.test;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.*;

import de.planspiel.cafe.*;
import de.planspiel.entscheidung.*;
import de.planspiel.ereignis.*;
import de.planspiel.konsolengui.EntscheidungTreffenGUI;
import de.planspiel.konsolengui.SpielBeendenGUI;
import de.planspiel.konsolengui.SpielStartenGUI;
import de.planspiel.spiel.*;

public class GesamtTest {
	private static Spiel spiel;
	private Vector<Unternehmenskette> kettenListe;
	private Vector<Entscheidung> rundenEntscheidungen;
	private Marktanteil aktuellerMarktanteil;
	private Unternehmenskette ukette1;
	private Unternehmenskette ukette2;
	
	@BeforeClass
	public static void vorbereiten(){
		Zufall.setzeTestmodus(true);
	}
	
	@Before
	public void erstelleSUT(){
		spiel = new Spiel();
	}
	
	@Test
	public void testenSpielen(){
			//Spieler aufnehmen
			ukette1 = new Unternehmenskette("Kette1");	
			ukette2 = new Unternehmenskette("Kette2");
			spiel.hinzufuegenUnternehmenskette(ukette1);
			spiel.hinzufuegenUnternehmenskette(ukette2);
						
			// Erzeugen der Standorte
				Standorttyp[] standorte = Standorttyp.values();
				for(int i=0; i<standorte.length; i++){
					spiel.hinzufuegenStandort(new Standort(standorte[i]));
				}
				
				// Erzeugen der Händler
				Haendlertyp[] haendler = Haendlertyp.values();
				for(int i=0; i<haendler.length; i++) {
					spiel.hinzufuegenHaendler(new Haendler(haendler[i]));
				}
				
				// Rundenorganisation - Runde 1
					// Kreditlaufzeit setzen Achtung - hier nicht sehr lang, da Nur 2 Runden gespielt werden
					Kredit.setzeAktuelleLaufzeit(spiel.holeRundenzahl() - (spiel.holeAktuelleRunde() - 1));
					
					// Marktanteil erzeugen
					spiel.setzeAktuellerMarktanteil(new Marktanteil());
					
					// Report für jedes Unternehmen erzeugen
					for(int i=0; i<spiel.holeKettenListe().size(); i++) {
						Unternehmenskette kette = spiel.holeKettenListe().get(i);
						// Ignoriere pleite Unternehmen
						if (!kette.holePleite()) {
							Report report = new Report(spiel.holeAktuelleRunde(), kette);
							report.setzeMarktanteil(spiel.holeAktuellerMarktanteil());
							kette.hinzufuegenReport(report);
						}
					}
					
					// Entscheidungs HashMap initialisieren
					rundenEntscheidungen = new Vector<Entscheidung>();
					
					
					//Entscheidungen treffen - hier im Test
					//Für UKette1:
					FilialeEroeffnenEntscheidung e1 = new FilialeEroeffnenEntscheidung(ukette1, spiel.holeStandortListe().get(1), 5);
					KreditEntscheidung e2 = new KreditEntscheidung(ukette1, 200.0);
					RohstoffEntscheidung e3 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(1), Produkttyp.KAFFEE, 50);
					RohstoffEntscheidung e4 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(1), Produkttyp.TEE, 20);
					RohstoffEntscheidung e5 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(1), Produkttyp.KUCHEN, 30);
					VerkaufspreisEntscheidung e6 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.KAFFEE, 4.00);
					VerkaufspreisEntscheidung e7 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.TEE, 3.00);
					VerkaufspreisEntscheidung e8 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.KUCHEN, 2.50);
					spiel.hinzufuegenRundenEntscheidung(e1);
					spiel.hinzufuegenRundenEntscheidung(e2);
					spiel.hinzufuegenRundenEntscheidung(e3);
					spiel.hinzufuegenRundenEntscheidung(e4);
					spiel.hinzufuegenRundenEntscheidung(e5);
					spiel.hinzufuegenRundenEntscheidung(e6);
					spiel.hinzufuegenRundenEntscheidung(e7);
					spiel.hinzufuegenRundenEntscheidung(e8);
					
					//Für UKette2:
					FilialeEroeffnenEntscheidung e9 = new FilialeEroeffnenEntscheidung(ukette1, spiel.holeStandortListe().get(1), 3);
					KreditEntscheidung e10 = new KreditEntscheidung(ukette1, 100.0);
					RohstoffEntscheidung e11 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(2), Produkttyp.KAFFEE, 30);
					RohstoffEntscheidung e12 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(2), Produkttyp.TEE, 30);
					RohstoffEntscheidung e13 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(2), Produkttyp.KUCHEN, 40);
					VerkaufspreisEntscheidung e14 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.KAFFEE, 3.00);
					VerkaufspreisEntscheidung e15 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.TEE, 2.50);
					VerkaufspreisEntscheidung e16 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.KUCHEN, 5.00);
					spiel.hinzufuegenRundenEntscheidung(e9);
					spiel.hinzufuegenRundenEntscheidung(e10);
					spiel.hinzufuegenRundenEntscheidung(e11);
					spiel.hinzufuegenRundenEntscheidung(e12);
					spiel.hinzufuegenRundenEntscheidung(e13);
					spiel.hinzufuegenRundenEntscheidung(e14);
					spiel.hinzufuegenRundenEntscheidung(e15);
					spiel.hinzufuegenRundenEntscheidung(e16);
										
					// Entscheidungen ausführen
					for(int i=0; i<spiel.holeRundenEntscheidungen().size(); i++){
						Entscheidung e = spiel.holeRundenEntscheidungen().get(i);
						// Ignoriere pleite Unternehmen
						if(!e.holeKette().holePleite()) {
							e.ausfuehren();
						}
					}
					
					// Kapazitäten initialisieren
					for(int i=0; i<spiel.holeKettenListe().size(); i++) {
						Unternehmenskette kette = spiel.holeKettenListe().get(i);
						// Ignoriere pleite Unternehmen
						if(!kette.holePleite()) {
							for(int j=0; j<kette.holeAnzahlFilialen(); j++){
								Filiale filiale = kette.holeFilialenListe().get(j);
								filiale.initialisierenKapazitaet();
							}
						}
					}
					
					// Kunden simulieren
					for(int i=0; i<spiel.holeStandortListe().size(); i++) {
						Standort standort = spiel.holeStandortListe().get(i);
						for(int j=0; j<standort.holeKundenkreis().size(); j++){
							Kunde kunde = standort.holeKundenkreis().get(j);
							kunde.simulierenEinkauf();
						}
					}
					
					// Unternehmenskosten berechnen und Runde abschließen
					for(int i=0; i<spiel.holeKettenListe().size(); i++) {
						Unternehmenskette kette = spiel.holeKettenListe().get(i);
						// Ignoriere pleite Unternehmen
						if(!kette.holePleite()) {
							kette.berechnenKosten();
							Report report = kette.holeReportListe().get(spiel.holeAktuelleRunde() - 1);
							report.abschließenRunde();
						}
					}
					
					//Hier Werte überprüfen - AUS REPORT??? am besten, oder?
					
					// Runde neu setzen
					spiel.setzeAktuelleRunde(spiel.holeAktuelleRunde() + 1);					
					
				
				//Rundenorganisation - Runde 2	
					// Kreditlaufzeit setzen Achtung - hier nicht sehr lang, da Nur 2 Runden gespielt werden
					Kredit.setzeAktuelleLaufzeit(spiel.holeRundenzahl() - (spiel.holeAktuelleRunde() - 1));
					
					// Marktanteil erzeugen
					spiel.setzeAktuellerMarktanteil(new Marktanteil());
					
					// Report für jedes Unternehmen erzeugen
					for(int i=0; i<spiel.holeKettenListe().size(); i++) {
						Unternehmenskette kette = spiel.holeKettenListe().get(i);
						// Ignoriere pleite Unternehmen
						if (!kette.holePleite()) {
							Report report = new Report(spiel.holeAktuelleRunde(), kette);
							report.setzeMarktanteil(spiel.holeAktuellerMarktanteil());
							kette.hinzufuegenReport(report);
						}
					}
					
					// Entscheidungs HashMap initialisieren
					rundenEntscheidungen = new Vector<Entscheidung>();
								
					//Entscheidungen treffen - hier im Test - WERTE NOCH ÄNDERN!!! AUCH UNTEN
					//Für UKette1:
					e1 = new FilialeEroeffnenEntscheidung(ukette1, spiel.holeStandortListe().get(1), 5);
					e2 = new KreditEntscheidung(ukette1, 200.0);
					e3 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(1), Produkttyp.KAFFEE, 50);
					e4 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(1), Produkttyp.TEE, 20);
					e5 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(1), Produkttyp.KUCHEN, 30);
					e6 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.KAFFEE, 4.00);
					e7 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.TEE, 3.00);
					e8 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.KUCHEN, 2.50);
					spiel.hinzufuegenRundenEntscheidung(e1);
					spiel.hinzufuegenRundenEntscheidung(e2);
					spiel.hinzufuegenRundenEntscheidung(e3);
					spiel.hinzufuegenRundenEntscheidung(e4);
					spiel.hinzufuegenRundenEntscheidung(e5);
					spiel.hinzufuegenRundenEntscheidung(e6);
					spiel.hinzufuegenRundenEntscheidung(e7);
					spiel.hinzufuegenRundenEntscheidung(e8);
					
					//Für UKette2:
					e9 = new FilialeEroeffnenEntscheidung(ukette1, spiel.holeStandortListe().get(1), 3);
					e10 = new KreditEntscheidung(ukette1, 100.0);
					e11 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(2), Produkttyp.KAFFEE, 30);
					e12 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(2), Produkttyp.TEE, 30);
					e13 = new RohstoffEntscheidung(ukette1, spiel.holeHaendlerListe().get(2), Produkttyp.KUCHEN, 40);
					e14 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.KAFFEE, 3.00);
					e15 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.TEE, 2.50);
					e16 = new VerkaufspreisEntscheidung(ukette1, Produkttyp.KUCHEN, 5.00);
					spiel.hinzufuegenRundenEntscheidung(e9);
					spiel.hinzufuegenRundenEntscheidung(e10);
					spiel.hinzufuegenRundenEntscheidung(e11);
					spiel.hinzufuegenRundenEntscheidung(e12);
					spiel.hinzufuegenRundenEntscheidung(e13);
					spiel.hinzufuegenRundenEntscheidung(e14);
					spiel.hinzufuegenRundenEntscheidung(e15);
					spiel.hinzufuegenRundenEntscheidung(e16);
					
					// Entscheidungen ausführen
					for(int i=0; i<spiel.holeRundenEntscheidungen().size(); i++){
						Entscheidung e = spiel.holeRundenEntscheidungen().get(i);
						// Ignoriere pleite Unternehmen
						if(!e.holeKette().holePleite()) {
							e.ausfuehren();
						}
					}
					
					// Kapazitäten initialisieren
					for(int i=0; i<spiel.holeKettenListe().size(); i++) {
						Unternehmenskette kette = spiel.holeKettenListe().get(i);
						// Ignoriere pleite Unternehmen
						if(!kette.holePleite()) {
							for(int j=0; j<kette.holeAnzahlFilialen(); j++){
								Filiale filiale = kette.holeFilialenListe().get(j);
								filiale.initialisierenKapazitaet();
							}
						}
					}
					
					// Kunden simulieren
					for(int i=0; i<spiel.holeStandortListe().size(); i++) {
						Standort standort = spiel.holeStandortListe().get(i);
						for(int j=0; j<standort.holeKundenkreis().size(); j++){
							Kunde kunde = standort.holeKundenkreis().get(j);
							kunde.simulierenEinkauf();
						}
					}
					
					// Unternehmenskosten berechnen und Runde abschließen
					for(int i=0; i<spiel.holeKettenListe().size(); i++) {
						Unternehmenskette kette = spiel.holeKettenListe().get(i);
						// Ignoriere pleite Unternehmen
						if(!kette.holePleite()) {
							kette.berechnenKosten();
							Report report = kette.holeReportListe().get(spiel.holeAktuelleRunde() - 1);
							report.abschließenRunde();
						}
					}
					
					// Runde neu setzen
					spiel.setzeAktuelleRunde(spiel.holeAktuelleRunde() + 1);
				
				
				new SpielBeendenGUI().run();
				
			}
	
	
	
	@AfterClass
	public static void beenden(){
		Zufall.setzeTestmodus(false);
	}
}
