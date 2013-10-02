package de.planspiel.cafe;

import java.util.Vector;

/**
 * 
 * @author Marc Becker
 * 
 */
public class Standort {

	private Vector<Kunde> kundenkreis;
	private double laufendeFilialkosten;
	private Vector<Filiale> filialenListe;
	private double startFilialkosten;
	private double anteilFilialverkauf;
	private int minKunden; // Anzahl der zu bedienenden Kunden ab einem
							// Mitarbeiter
	private int maxKunden; // Anzahl der zu bedienenden Kunden ab maxMitarbeiter
							// Mitarbeiter
	private int maxMitarbeiter;

	/**
	 * Erzeugt einen undefinierten Standort
	 */
	public Standort() {
		this.kundenkreis = new Vector<Kunde>();
		this.filialenListe = new Vector<Filiale>();
	}

	/**
	 * Erzeugt einen neuen Standort
	 * 
	 * @param laufendeFilialkosten
	 * @param startFilialkosten
	 * @param anteilFilialverkauf
	 *            Prozentualer Anteil an den startFilialkosten, die man bei
	 *            Verkauf einer Filiale zur�ckbekommt
	 * @param minKunden
	 *            Kunden, die von einen Mitarbeiter bedient werden k�nnen
	 * @param maxKunden
	 *            Kunden, die von maxMitarbeiter Mitarbeiter bedient werden
	 *            k�nnen
	 * @param maxMitarbeiter
	 *            Mitarbeiteranzahl, ab der sich zus�tzliche Mitarbeiter nicht
	 *            mehr lohnen
	 */
	public Standort(double laufendeFilialkosten, double startFilialkosten, int minKunden, int maxKunden, int maxMitarbeiter) {
		this();
		setzeLaufendeFilialkosten(laufendeFilialkosten);
		setzeStartFilialkosten(startFilialkosten);
		setzeAnteilFilialverkauf(0.5);
		setzeMinKunden(minKunden);
		setzeMaxKunden(maxKunden);
		setzeMaxMitarbeiter(maxMitarbeiter);
	}

	/**
	 * Die Anzahl der Kunden eines Stadorts ergibt sich aus Anzahl der
	 * Unternehmensketten * maximale Kunden einer Filiale / 2 �ber eine
	 * Zufallszahl wird entschieden, welche Pr�ferenz der Kunde hat Kunde wird
	 * in die Kundenliste des Standorts eingef�gt
	 */
	public void generierenKundenliste() {
		//Die Anzahl der Kunden wird bestimmt durch alle Ketten multipliziert mit der H�lfte von maxKunden
		Vector<Unternehmenskette> ketten = Spiel.holeSpiel().holeKettenListe();
		int anzahlKunden = ketten.size() * this.holeMaxKunden() / 2;
		for (int i = 0; i < anzahlKunden; i++) {
			double zahl = Zufall.generierenZufallszahl(3);
			Praeferenz praeferenz = null;
			if (zahl <= 1.0)
				praeferenz = Praeferenz.PREIS;
			else if (zahl <= 2.0)
				praeferenz = Praeferenz.QUALITAET;
			else if (zahl <= 3.0)
				praeferenz = Praeferenz.AVG;
			this.hinzufuegenKunde(new Kunde(this, praeferenz));
		}
		int c = ketten.size() * 2; // Jeder c. Kunde lernt Ketten kennen
		// TODO Dies ist noch nicht zuf�llig genug, bzw. fair genug
		for (int i = 0; i<ketten.size(); i++) {
			beeinflussenKunden(ketten.get(i), c);
		}
	}

	/**
	 * Beeinflusst die Kunden am Standort. Alle Kunden an diesem Standort werden durchlaufen
	 * und lernen mit der Wahrscheinlichkeit p die Unternehmenskette kennen.
	 * @param kette Kette, die der Kunde kennen lernen wird
	 * @param p Wahrscheinlichkeit mit der der Kunde, die Kette kennenlernt
	 */
	public void beeinflussenKunden(Unternehmenskette kette, Double p) {
		for (int i = 0; i < this.holeKundenkreis().size(); i++) {
			Kunde kunde = holeKundenkreis().get(i);
			if(Zufall.treffenEntscheidung(p)){
				kunde.kennenlernen(kette);
			}
		}
	}

	/**
	 * Beeinflusst die Kunden am Standort. Alle Kunden an diesem Standort werden durchlaufen
	 * und jeder "c." Kunde lernt die Unternehmenskette kennen
	 * @param kette Kette, die der Kunde kennen lernen wird
	 * @param c Jeder "c"te Kunde lernt die Kette kennen. Ist c = 5 bedeutet das, dass jeder 5. Kunde die Kette kennen lernt
	 */
	public void beeinflussenKunden(Unternehmenskette kette, int c) {
		int count = (int)Zufall.generierenZufallszahl(c);
		for (int i = 0; i < this.holeKundenkreis().size(); i++) {
			Kunde kunde = holeKundenkreis().get(i);
			count++;
			if(count >= c) {
				count = 0;
				kunde.kennenlernen(kette);
			}
		}
	}
	
	/**
	 * Berechnet die Kunden, die an diesem Standort von der �bergebenen Menge an
	 * Mitarbeiter bedient werden k�nnen. Dem liegt eine lineare Funktion zu
	 * Grunde, die durch die Attribute minKunden, maxKunden und maxMitarbeiter
	 * des Standortes definiert werden
	 * 
	 * @param mitarbeiter
	 *            Aktuelle Mitarbeiteranzahl der Filiale, f�r die die Kapazit�t
	 *            berechnet werden soll
	 * @return Kundenkapazit�t, also die Anzahl der Kunden, die in dieser
	 *         Periode bedient werden k�nnen
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
	 * @return Alle Kunden an diesem Standort
	 */
	public Vector<Kunde> holeKundenkreis() {
		return kundenkreis;
	}

	/**
	 * F�gt dem Standort einen Kunden hinzu
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
	 * @return Gibt die laufenden Filialkosten zur�ck
	 */
	public double holeLaufendeFilialkosten() {
		return laufendeFilialkosten;
	}

	/**
	 * Setzt die laufenden Filialkosten
	 * 
	 * @param laufendeFilialkosten
	 *            Filialkosten gr��er gleich 0
	 */
	public void setzeLaufendeFilialkosten(double laufendeFilialkosten) {
		if (laufendeFilialkosten >= 0) {
			this.laufendeFilialkosten = laufendeFilialkosten;
		}
	}

	/**
	 * @return Gibt eine Liste aller Filialen am Standort zur�ck
	 */
	public Vector<Filiale> holeFilialenListe() {
		return filialenListe;
	}

	/**
	 * F�gt eine Filiale zum Standort hinzu und verbucht die passenden Kosten
	 * mit der Unternehmenskette Diese Methode sollte nicht direkt aufgerufen
	 * werden!
	 * 
	 * @param filiale
	 *            neue Filiale
	 */
	public void hinzufuegenFiliale(Filiale filiale) {
		if (filiale != null) {
			if(filiale.holeKette().verbuchenKosten(Kostenverursacher.FILIALE_ANSCHAFFUNG, holeStartFilialkosten())){
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
	 * Gibt die Kosten zur�ck, die zum er�ffnen einer neuen Filiale ben�tigt
	 * werden
	 * 
	 * @return Kosten zur Er�ffnung
	 */
	public double holeStartFilialkosten() {
		return startFilialkosten;
	}

	/**
	 * Setzt die Kosten zur Er�ffnung einer Filiale
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
	 * @return Gibt den prozentualen Anteil an den startFilialkosten zur�ck, den
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
	 *            Kundenanzahl gr��er 0
	 */
	public void setzeMinKunden(int minKunden) {
		if (minKunden > 0) {
			this.minKunden = minKunden;
		}
	}

	/**
	 * @return Kundenanzahl, die von maxMitarbeiter Mitarbeiter bedient werden
	 *         k�nnen
	 */
	public int holeMaxKunden() {
		return maxKunden;
	}

	/**
	 * Setzt die Kundenanzahl, die von maxMitarbeiter Mitarbeiter bedient werden
	 * k�nnen
	 * 
	 * @param maxKunden
	 *            Kundenanzahl gr��er als minKunden
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
	 *            Maximale Mitarbeiteranzahl gr��er 1
	 */
	public void setzeMaxMitarbeiter(int maxMitarbeiter) {
		if (maxMitarbeiter > 1) {
			this.maxMitarbeiter = maxMitarbeiter;
		}
	}
}
