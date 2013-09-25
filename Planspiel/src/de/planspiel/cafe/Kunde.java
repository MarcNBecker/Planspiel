package de.planspiel.cafe;
import java.util.Vector;

/**
 * 
 * @author Daniel, Max
 *
 */

public class Kunde {
	
	private Vector<Unternehmenskette> kettenListe;
	private Vector<Produkt> produkte;
	private Praeferenz praeferenz; 
	private Standort standort; 
	
	/**
	 * Erstellt einen neuen Kunden.
	 * @param standort Gibt an, an welchem Standort der Kunde agiert.
	 * @param praeferenz Gibt an, welche Praefernez der Kunde hat (siehe ENUM Praeferenz)
	 */
	public Kunde(Standort standort, Praeferenz praeferenz) {
		// TODO Dran denken, beim Kundenkreis generieren die kennenlernen-Methode einmal aufzurufen
		kettenListe = new Vector<Unternehmenskette>();
		
		// Produktliste - ANFANG
		produkte = new Vector<Produkt>();
		// zufallszahl = Anzahl Produkte (mind. 1, max. anzProdukttypen)
		int zufallszahl = (int) Math.random()*Produkttyp.values().length+1;
		for (int i=0; i<zufallszahl;i++) {
			int zufallsprodukt = (int) (Math.random()*Produkttyp.values().length);
			// TODO Menge festlegen, die ein Kunde max. kaufen soll
			int zufallsmenge = 1;
			//int zufallsmenge = (int) (Math.random()*3+1);
			
			 // Würde kein Produkt in der Produktliste sein, dann würde die For-Schleife nicht aufgerufen werden
			if (produkte.size()==0) {
				produkte.add(new Produkt(Produkttyp.values()[zufallsprodukt], zufallsmenge));
			} else {
				// Sicherstellen, dass kein Produkt zweimal in die Produktliste kommt.
			for (int j=0; j<produkte.size(); j++) {
					if (produkte.get(j).holeName()==Produkttyp.values()[zufallsprodukt]) {i--;
					} else {
						produkte.add(new Produkt(Produkttyp.values()[zufallsprodukt], zufallsmenge));
					} // if
				
				}// for
			}
		} // for
		// Zusätzliche Eigenschaften festlegen
		for (int i=0; i<produkte.size(); i++) {
			produkte.get(i).setzeQualitaet(Zufall.generierenZufallszahl(1));
			produkte.get(i).setzePreis(Zufall.generierenZufallszahl(15));
		}
		// Produktliste -- ENDE
		
		this.praeferenz = praeferenz;
		this.standort = standort;
	}
	
	/**
	 * Der Kunde lernt eine neue Unternehmenskette kennen, insofern er sie nicht schon kennt.
	 * @param kette Gibt an, welche Unternehmenskette der Kunde kennenlernen soll.
	 */
	public void kennenlernen(Unternehmenskette kette) {
		if (!(kettenListe.contains(kette))) {
			hinzufuegenUnternehmenskette(kette);
		}
	}
	
	/**
	 * Der Kunde kauft ein.
	 */
	public void simulierenEinkauf() {
		// Unternehmensketten herausfiltern, die eine Filiale am Standort des Kunden haben.

		Vector<Filiale> filialenListe = this.standort.holeFilialenListe();
		//Filtern nach: Welche KENNE ich davon überhaupt?
		
		// Filialen herausfiltern, die mein Prio1 Produkt verkaufen
		Vector<Filiale> favoriten = new Vector<Filiale>();
		for (int i=0; i<filialenListe.size();i++) {
			for(int k=0; k<filialenListe.get(i).holeKette().holeLager().holeProduktliste().size();k++) {
				// prüft, ob das Produkt passt und ob die Menge wenigstens größer 0 ist und noch genug Kapazitäten vorhanden sind. 
				// VORSICHT! eigentlich muss produkte.get(o).vergleichen(LAGERPRODUKT) machen!
					if (filialenListe.get(i).holeKette().holeLager().holeProduktliste().get(k).vergleichen(produkte.get(0)) && filialenListe.get(i).holeKette().holeLager().holeProduktliste().get(k).holeMenge()>0 && filialenListe.get(i).holeFreieKapazitaet()>0) {
						// Damit ich die jeweilige Filiale der Unternehmenskette in meiner favoriten-Liste speichern kann, muss ich nochmal über die naheKetten loopen
						favoriten.add(filialenListe.get(i));
					}
			}
		}
		// 3 Fälle - Die Favoritenliste hat ein Element, hat mehr als 1 Element oder kein Element
		if (favoriten.size()==1) { 
			//favoritenliste hat nur eine Unternehmenskette
			favoriten.get(0).verkaufen(this.produkte.get(0).holeName(), produkte.get(0).holeMenge()); // ---> Untermethode?
			// Prüfen, ob das einzige Element in der Favoritenliste auch das zweite Produkt, das der Kunde mag, anbietet. Wenn ja, kauft der Kunde das. 
			// MUSS ANGEPASST WERDEN! vergleichen, menge und kapazität prüfen!
			
			for (int i=1; i<this.produkte.size();i++) {
				if (produkte.get(i)!=null && favoriten.get(0).holeKette().holeLager().suchenProdukt(produkte.get(i).holeName())!=null) {
					favoriten.get(0).verkaufen(produkte.get(i).holeName(), produkte.get(i).holeMenge());
				}
			}
		}
		else if (favoriten.size()!=0) {
			// favoritenliste hat mehrere Unternehmenskette
			
			// Die erste Filiale in der Favoritenliste ist pauschal der erste Favorit.
			Filiale favorit = favoriten.get(0);
			Vector <Filiale> gleichheitsListe = new Vector<Filiale>();
			for (int i=1; i<favoriten.size();i++) { //Vertauschen von If-Abfrage und For-Schleife ;)
				if(this.praeferenz==Praeferenz.PREIS) {
					if (favorit.holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis()==favoriten.get(i).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis()) {
						if (!gleichheitsListe.contains(favorit)) gleichheitsListe.add(favorit);
						gleichheitsListe.add(favoriten.get(i));
					} else if (favorit.holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis()>favoriten.get(i).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis()) {	
							favorit = favoriten.get(i);
							gleichheitsListe.removeAllElements();
					}
				} else if (this.praeferenz==Praeferenz.QUALITAET) {
					if (favorit.holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet()==favoriten.get(i).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet()) {
						if (!gleichheitsListe.contains(favorit)) gleichheitsListe.add(favorit);
						gleichheitsListe.add(favoriten.get(i));
					} else if (favorit.holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet()>favoriten.get(i).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet()) {	
							favorit = favoriten.get(i);
							gleichheitsListe.removeAllElements();
					}
				} else  {
					// Für die Average-Präferenz
					
					//ggf. mehrmaliges Ausrechnen durch einen boolean abfangen, der sagt ob der Teil schon berechnet wurde.
					//Variablen für den Average-Case
					double preis = 0;
					double qualität = 0;
					double avg = 0.0;
					// berechnen der Average-Werte
					for (int p=0; p<favoriten.size();p++) {
						preis = preis + favoriten.get(p).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis();
						qualität = qualität + favoriten.get(p).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet();
					}
					avg = (preis+qualität)/favoriten.size();
					

					double avgBestDifference = favorit.holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis() + favorit.holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet(); 
					// aktuelles Objekt aus der for-Schleife
					double avgAktuell = favoriten.get(i).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis() + favoriten.get(i).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet(); 
					if (Math.abs(avgBestDifference-avg)>Math.abs(avgAktuell-avg)) {
						favorit = favoriten.get(i);
					}
				}// else-AVG
			} // for
			if (gleichheitsListe.size()>0) {
				int zufallszahl = (int) (Math.random()*gleichheitsListe.size());
				favorit = gleichheitsListe.get((int) zufallszahl); 
			}
			//Evtl in weitere Untermethode auslagern (s. oben)
			favorit.verkaufen(produkte.get(0).holeName(), produkte.get(0).holeMenge());
			// Prüfen, ob unser Favorit auch des Kunden zweites Produkt hat
			for (int j=1; j<this.produkte.size();j++) {
				if (produkte.get(j)!=null && favorit.holeKette().holeLager().suchenProdukt(produkte.get(j).holeName())!=null) {
					favorit.verkaufen(produkte.get(j).holeName(), produkte.get(j).holeMenge());
				}
			}
		} // if
		else {
			// favoritenliste ist leer
		}	
		
	}
	
	public Vector<Unternehmenskette> holeKettenListe() {
		return this.kettenListe;
	}
	
	public void hinzufuegenUnternehmenskette (Unternehmenskette kette) {
		this.kettenListe.add(kette);
	}
	
	public Vector<Produkt> holeProdukte() {
		return this.produkte;
	}
	
	public void hinzufuegenProdukt (Produkt produkt) {
		this.produkte.add(produkt);
	}
	
	public Praeferenz holePraeferenz() {
		return this.praeferenz;
	}
	
	public void setzePraeferenz (Praeferenz praeferenz) {
		this.praeferenz=praeferenz;
	}
	public Standort holeStandort() {
		return this.standort;
	}

}
