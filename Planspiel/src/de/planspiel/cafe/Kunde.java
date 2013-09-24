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
		// TODO in das UML-Diagramm die Paramter einf�gen; Die Produktliste muss ordentlich generiert werden; mind. eine Unternehmenskette muss in die kettenListehinzugef�gt werden
		kettenListe = new Vector<Unternehmenskette>();
		produkte = new Vector<Produkt>();
		this.praeferenz = praeferenz;
		this.standort = standort; //new
	}
	
	/**
	 * Der Kunde lernt eine neue Unternehmenskette kennen.
	 * @param kette Gibt an, welche Unternehmenskette der Kunde kennenlernen soll.
	 */
	public void kennenlernen(Unternehmenskette kette) {
		// TODO
		if (!(kettenListe.contains(kette))) {
			hinzufuegenUnternehmenskette(kette);
		}
	}
	
	/**
	 * Der Kunde kauft ein.
	 */
	public void simulierenEinkauf() {
		// TODO muss aufjedenfall auf Fehler gepr�ft werden + Filialkapazit�t einbauen
		// Unternehmensketten herausfiltern, die eine Filiale am Standort des Kunden haben.

		Vector<Filiale> filialenListe = this.standort.holeFilialenListe();
		// Filialen herausfiltern, die mein Prio1 Produkt verkaufen
		Vector<Filiale> favoriten = new Vector<Filiale>();
		for (int i=0; i<filialenListe.size();i++) {
			for(int k=0; k<filialenListe.get(i).holeKette().holeLager().holeProduktliste().size();k++) {
				// pr�ft, ob das Produkt passt und ob die Menge wenigstens gr��er 0 ist.
					if (filialenListe.get(i).holeKette().holeLager().holeProduktliste().get(k).vergleichen(produkte.get(0)) && filialenListe.get(i).holeKette().holeLager().holeProduktliste().get(k).holeMenge()>0 && filialenListe.get(i).holeFreieKapazitaet()>0) {
						// Damit ich die jeweilige Filiale der Unternehmenskette in meiner favoriten-Liste speichern kann, muss ich nochmal �ber die naheKetten loopen
						favoriten.add(filialenListe.get(i));
					}
			}
		}
		// 3 F�lle - Die Favoritenliste hat ein Element, hat mehr als 1 Element oder kein Element
		if (favoriten.size()==1) {
			//favoritenliste hat nur eine Unternehmenskette
			favoriten.get(0).verkaufen(this.produkte.get(0).holeName(), produkte.get(0).holeMenge());
			// Pr�fen, ob das einzige Element in der Favoritenliste auch das zweite Produkt, das der Kunde mag, anbietet. Wenn ja, kauft der Kunde das.
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
			for (int i=1; i<favoriten.size();i++) {
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
					// F�r die Average-Pr�ferenz
					
					//ggf. mehrmaliges Ausrechnen durch einen boolean abfangen, der sagt ob der Teil schon berechnet wurde.
					//Variablen f�r den Average-Case
					double preis = 0;
					double qualit�t = 0;
					double avg = 0.0;
					// berechnen der Average-Werte
					for (int p=0; p<favoriten.size();p++) {
						preis = preis + favoriten.get(p).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis();
						qualit�t = qualit�t + favoriten.get(p).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet();
					}
					avg = (preis+qualit�t)/favoriten.size();
					

					double avgBestDifference = favorit.holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis() + favorit.holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet(); 
					// aktuelles Objekt aus der for-Schleife
					double avgAktuell = favoriten.get(i).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holePreis() + favoriten.get(i).holeKette().holeLager().suchenProdukt(produkte.get(0).holeName()).holeQualitaet(); 
					if (Math.abs(avgBestDifference-avg)>Math.abs(avgAktuell-avg)) {
						favorit = favoriten.get(i);
					}
				}// else-AVG
			} // for
			// TODO hier muss unbedint das Math.random() �berpr�ft werden
			if (gleichheitsListe.size()>0) {
				double zufallszahl = (int) (Math.random()*gleichheitsListe.size());
				favorit = gleichheitsListe.get((int) zufallszahl); 
			}
			favorit.verkaufen(produkte.get(0).holeName(), produkte.get(0).holeMenge());
			// Pr�fen, ob unser Favorit auch des Kunden zweites Produkt hat
			for (int j=0; j<this.produkte.size();j++) {
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

}
