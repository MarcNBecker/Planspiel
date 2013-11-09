package de.planspiel.cafe;

import java.util.Vector;

import de.planspiel.spiel.Spiel;
import de.planspiel.spiel.Zufall;

/**
 * Klasse zur Verwaltung des Standortes
 * @author Marc Becker
 */
public class Standort {

	private Standorttyp name;
	private Vector<Kunde> kundenkreis;
	private Vector<Filiale> filialenListe;
	private double laufendeFilialkosten;
	private double startFilialkosten;
	private double anteilFilialverkauf;
	private int minKunden; // Anzahl der zu bedienenden Kunden ab einem
							// Mitarbeiter
	private int maxKunden; // Anzahl der zu bedienenden Kunden ab maxMitarbeiter
							// Mitarbeiter
	private int maxMitarbeiter;

	/**
	 * Erzeugt einen neuen Standort
	 * 
	 * @param name
	 * 			Standorttyp, der alle Eigenschaften enthält
	 */
	public Standort(Standorttyp name) {
		this.name = name;
		this.kundenkreis = new Vector<Kunde>();
		this.filialenListe = new Vector<Filiale>();
		setzeLaufendeFilialkosten(name.holeLaufendeFilialkosten());
		setzeStartFilialkosten(name.holeStartFilialkosten());
		setzeAnteilFilialverkauf(0.5);
		setzeMinKunden(name.holeMinKunden());
		setzeMaxKunden(name.holeMaxKunden());
		setzeMaxMitarbeiter(name.holeMaxMitarbeiter());
		generierenKundenliste();
	}

	/**
	 * Generiert die Kundenliste des Standorts Die Anzahl der Kunden eines
	 * Stadorts ergibt sich aus Anzahl der Unternehmensketten * maximale Kunden
	 * einer Filiale / 2 Über eine Zufallszahl wird entschieden, welche
	 * Präferenz der Kunde hat Kunde wird in die Kundenliste des Standorts
	 * eingefügt
	 */
	public void generierenKundenliste() {
		// Die Anzahl der Kunden wird bestimmt durch alle Ketten multipliziert
		// mit der Hälfte von maxKunden
		Vector<Unternehmenskette> ketten = Spiel.holeSpiel().holeKettenListe();
		int anzahlKunden = ketten.size() * this.holeMaxKunden(); // /2 entfernt
		for (int i = 0; i < anzahlKunden; i++) {
			this.hinzufuegenKunde(new Kunde(this));
		}
		for (int i = 0; i < ketten.size(); i++) {
			beeinflussenKundenProzentual(ketten.get(i), 0.3);
		}
	}

	/**
	 * Beeinflusst die Kunden am Standort. Alle Kunden an diesem Standort werden
	 * durchlaufen und lernen mit der Wahrscheinlichkeit p die Unternehmenskette
	 * kennen.
	 * 
	 * @param kette
	 *            Kette, die der Kunde kennen lernen wird
	 * @param p
	 *            Wahrscheinlichkeit mit der der Kunde, die Kette kennenlernt
	 */
	public void beeinflussenKunden(Unternehmenskette kette, double p) {
		for (int i = 0; i < this.holeKundenkreis().size(); i++) {
			Kunde kunde = holeKundenkreis().get(i);
			if (Zufall.treffenEntscheidung(p)) {
				kunde.kennenlernen(kette);
			}
		}
	}

	/**
	 * Beeinflusst die Kunden am Standort. Alle Kunden an diesem Standort werden
	 * durchlaufen und (p*100) % lernen die Kette kennen
	 * 
	 * @param kette
	 *            Kette, die der Kunde kennen lernen wird
	 * @param p
	 *            (p*100)% Kunden an diesem Standort lernen die Kette kennen
	 */
	public void beeinflussenKundenProzentual(Unternehmenskette kette, double p) {
		int c = (int) (holeKundenkreis().size() * p); // so viele Kunden
														// entsprechen (p*100)%
		int f = 0; // Anzahl der Zufallsfehler
		while (c > 0) {
			int zufallsZahl = (int) Zufall.generierenZufallszahl(holeKundenkreis().size());
			Kunde kunde = holeKundenkreis().get(zufallsZahl);
			if (kunde != null && !kunde.holeKettenListe().contains(kette)) {
				kunde.kennenlernen(kette); // Kunde lernt die Kette kennen
				c--; // also muss ein Kunde weniger die Kette kennen lernen
			} else {
				f++; // es ist ein Zufallsfehler aufgetreten (Kunde kam zum 2.
						// Mal dran)
				if (f > 10) {
					c--; // nach 10 Zufallsfehler wird kein weiterer mehr
							// abgefangen
				}
			}
		}
	}

	/**
	 * Berechnet die Kunden, die an diesem Standort von der übergebenen Menge an
	 * Mitarbeiter bedient werden können. Dem liegt eine lineare Funktion zu
	 * Grunde, die durch die Attribute minKunden, maxKunden und maxMitarbeiter
	 * des Standortes definiert werden
	 * 
	 * @param mitarbeiter
	 *            Aktuelle Mitarbeiteranzahl der Filiale, für die die Kapazität
	 *            berechnet werden soll
	 * @return Kundenkapazität, also die Anzahl der Kunden, die in dieser
	 *         Periode bedient werden können
	 */
	public int berechnenKapazitaet(int mitarbeiter) {
		if (mitarbeiter < 1) {
			return 0;
		} else if (mitarbeiter == 1) {
			return minKunden;
		} else if (mitarbeiter >= maxMitarbeiter) {
			return maxKunden;
		} else {
			// f(x) = m*x + b
			double b = (holeMaxKunden() - (holeMinKunden() * holeMaxMitarbeiter())) / (1 - holeMaxKunden());
			double m = holeMinKunden() - b;
			int kundenKapazitaet = (int) ((m * mitarbeiter) + b);
			return kundenKapazitaet;
		}
	}

	/**
	 * @return Name des Standorts
	 */
	public String holeName() {
		return name.toString();
	}

	/**
	 * @return Alle Kunden an diesem Standort
	 */
	public Vector<Kunde> holeKundenkreis() {
		return kundenkreis;
	}

	/**
	 * Fügt dem Standort einen Kunden hinzu
	 * 
	 * @param kunde
	 *            Kunde ungleich null
	 */
	public void hinzufuegenKunde(Kunde kunde) {
		if (kunde != null) {
			kundenkreis.add(kunde);
		}
	}

	/**
	 * @return Gibt die laufenden Filialkosten zurück
	 */
	public double holeLaufendeFilialkosten() {
		return laufendeFilialkosten;
	}

	/**
	 * Setzt die laufenden Filialkosten
	 * 
	 * @param laufendeFilialkosten
	 *            Filialkosten größer gleich 0
	 */
	public void setzeLaufendeFilialkosten(double laufendeFilialkosten) {
		if (laufendeFilialkosten >= 0) {
			this.laufendeFilialkosten = laufendeFilialkosten;
		}
	}

	/**
	 * @return Gibt eine Liste aller Filialen am Standort zurück
	 */
	public Vector<Filiale> holeFilialenListe() {
		return filialenListe;
	}

	/**
	 * Fügt eine Filiale zum Standort hinzu und verbucht die passenden Kosten
	 * mit der Unternehmenskette Diese Methode sollte nicht direkt aufgerufen
	 * werden!
	 * 
	 * @param filiale
	 *            neue Filiale
	 */
	public void hinzufuegenFiliale(Filiale filiale) {
		if (filiale != null) {
			if (filiale.holeKette().verbuchenKosten(Kostenverursacher.FILIALE_ANSCHAFFUNG, holeStartFilialkosten())) {
				holeFilialenListe().add(filiale);
			}
		}
	}

	/**
	 * Entfernt eine Filiale aus dem Standort und verbucht den Verkaufsertrag
	 * mit der Unternehmenskette Diese Methode sollte nicht direkt aufgerufen
	 * werden!
	 * 
	 * @param filiale
	 */
	public void entfernenFiliale(Filiale filiale) {
		if (holeFilialenListe().contains(filiale)) {
			holeFilialenListe().remove(filiale);
			filiale.holeKette().verbuchenErtrag(Ertragsverursacher.FILIALE_VERKAUF, holeAnteilFilialverkauf() * holeStartFilialkosten());
		}
	}

	/**
	 * Gibt die Kosten zurück, die zum eröffnen einer neuen Filiale benötigt
	 * werden
	 * 
	 * @return Kosten zur Eröffnung
	 */
	public double holeStartFilialkosten() {
		return startFilialkosten;
	}

	/**
	 * Setzt die Kosten zur Eröffnung einer Filiale
	 * 
	 * @param startFilialkosten
	 *            neue Kosten
	 */
	public void setzeStartFilialkosten(double startFilialkosten) {
		if (startFilialkosten >= 0) {
			this.startFilialkosten = startFilialkosten;
		}
	}

	/**
	 * @return Gibt den prozentualen Anteil an den startFilialkosten zurück, den
	 *         man beim Verkauf bekommt
	 */
	public double holeAnteilFilialverkauf() {
		return anteilFilialverkauf;
	}

	/**
	 * Setzt den prozentualen Anteil an den startFilialksoten, den man beim
	 * Verkauf einer Filiale bekommt
	 * 
	 * @param anteilFilialverkauf
	 *            Zwischen 0 und 1
	 */
	public void setzeAnteilFilialverkauf(double anteilFilialverkauf) {
		if (anteilFilialverkauf <= 1 && anteilFilialverkauf >= 0) {
			this.anteilFilialverkauf = anteilFilialverkauf;
		} else {
			this.anteilFilialverkauf = 0;
		}
	}

	/**
	 * @return Kundenanzahl, die von einem Mitarbeiter bedient werden kann
	 */
	public int holeMinKunden() {
		return minKunden;
	}

	/**
	 * Setzt die Kundenanzahl, die von einem Mitarbeiter bedient werden kann
	 * 
	 * @param minKunden
	 *            Kundenanzahl größer 0
	 */
	public void setzeMinKunden(int minKunden) {
		if (minKunden > 0) {
			this.minKunden = minKunden;
		}
	}

	/**
	 * @return Kundenanzahl, die von maxMitarbeiter Mitarbeiter bedient werden
	 *         können
	 */
	public int holeMaxKunden() {
		return maxKunden;
	}

	/**
	 * Setzt die Kundenanzahl, die von maxMitarbeiter Mitarbeiter bedient werden
	 * können
	 * 
	 * @param maxKunden
	 *            Kundenanzahl größer als minKunden
	 */
	public void setzeMaxKunden(int maxKunden) {
		if (maxKunden > holeMinKunden()) {
			this.maxKunden = maxKunden;
		}
	}

	/**
	 * @return maximale Mitarbeiterzahl, die es sich lohnt einzustellen
	 */
	public int holeMaxMitarbeiter() {
		return maxMitarbeiter;
	}

	/**
	 * Setzt die maximale Mitarbeiterzahl, bis zu der es sich lohnt einzustellen
	 * 
	 * @param maxMitarbeiter
	 *            Maximale Mitarbeiteranzahl größer 1
	 */
	public void setzeMaxMitarbeiter(int maxMitarbeiter) {
		if (maxMitarbeiter > 1) {
			this.maxMitarbeiter = maxMitarbeiter;
		}
	}
}
