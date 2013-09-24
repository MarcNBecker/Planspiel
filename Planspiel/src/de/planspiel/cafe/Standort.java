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
	private int minKunden; //Anzahl der zu bedienenden Kunden ab einem Mitarbeiter
	private int maxKunden; //Anzahl der zu bedienenden Kunden ab maxMitarbeiter Mitarbeiter
	private int maxMitarbeiter;
	
	/**
	 * Erzeugt einen undefinierten Standort
	 */
	public Standort(){
		this.kundenkreis = new Vector<Kunde>();
		this.filialenListe = new Vector<Filiale>();
	}
	
	/**
	 * Erzeugt einen neuen Standort
	 * @param laufendeFilialkosten
	 * @param startFilialkosten
	 * @param anteilFilialverkauf Prozentualer Anteil an den startFilialkosten, die man bei Verkauf einer Filiale zurückbekommt
	 * @param minKunden Kunden, die von einen Mitarbeiter bedient werden können
	 * @param maxKunden Kunden, die von maxMitarbeiter Mitarbeiter bedient werden können
	 * @param maxMitarbeiter Mitarbeiteranzahl, ab der sich zusätzliche Mitarbeiter nicht mehr lohnen
	 */
	public Standort(double laufendeFilialkosten, double startFilialkosten, double anteilFilialverkauf, int minKunden, int maxKunden, int maxMitarbeiter){
		this();
		setzeLaufendeFilialkosten(laufendeFilialkosten);
		setzeStartFilialkosten(startFilialkosten);
		setzeAnteilFilialverkauf(anteilFilialverkauf);
		setzeMinKunden(minKunden);
		setzeMaxKunden(maxKunden);
		setzeMaxMitarbeiter(maxMitarbeiter);
	}
	
	/**
	 * 
	 */
	public void generierenKundenliste() {
		// TODO
	}
	
	/**
	 * 
	 * @param kette
	 */
	public void beeinflussenKunden(Unternehmenskette kette) {
		// TODO
	}
	
	/**
	 * Berechnet die Kunden, die an diesem Standort von der übergebenen Menge an Mitarbeiter bedient werden können.
	 * Dem liegt eine lineare Funktion zu Grunde, die durch die Attribute minKunden, maxKunden und maxMitarbeiter des Standortes
	 * definiert werden
	 * @param mitarbeiter Aktuelle Mitarbeiteranzahl der Filiale, für die die Kapazität berechnet werden soll
	 * @return Kundenkapazität, also die Anzahl der Kunden, die in dieser Periode bedient werden können
	 */
	public int berechnenKapazitaet(int mitarbeiter) {
		if(mitarbeiter < 1){
			return 0;
		} else if (mitarbeiter == 1) {
			return minKunden;
		} else if (mitarbeiter >= maxMitarbeiter){
			return maxKunden;
		} else {
			//f(x) = m*x + b
			double b = (holeMaxKunden() - (holeMinKunden() * holeMaxMitarbeiter())) / (1 - holeMaxKunden());
			double m = holeMinKunden() - b;
			int kundenKapazitaet = (int)((m * mitarbeiter) + b);
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
	 * Fügt dem Standort einen Kunden hinzu
	 * @param kunde Kunde ungleich null
	 */
	public void hinzufuegenKunde(Kunde kunde) {
		if(kunde != null) {
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
	 * @param laufendeFilialkosten Filialkosten größer gleich 0
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
	 * Fügt eine Filiale zum Standort hinzu und verbucht die passenden Kosten mit der Unternehmenskette
	 * @param filiale neue Filiale
	 */
	public void hinzufuegenFiliale(Filiale filiale){
		if (filiale != null) {
			Unternehmenskette kette = filiale.holeKette();
			kette.verbuchenKosten(Kostenverursacher.FILIALE_ANSCHAFFUNG, holeStartFilialkosten());
			filialenListe.add(filiale);
		}
	}
	
	/**
	 * Entfernt eine Filiale aus dem Standort und verbucht den Verkaufsertrag mit der Unternehmenskette
	 * @param filiale
	 */
	public void entfernenFiliale(Filiale filiale) {
		if(holeFilialenListe().contains(filiale)){
			holeFilialenListe().remove(filiale);
			filiale.holeKette().verbuchenErtrag(Ertragsverursacher.FILIALE_VERKAUF, holeAnteilFilialverkauf() * holeStartFilialkosten());
		}
	}
	
	/**
	 * Gibt die Kosten zurück, die zum eröffnen einer neuen Filiale benötigt werden
	 * @return Kosten zur Eröffnung
	 */
	public double holeStartFilialkosten() {
		return startFilialkosten;
	}
	
	/**
	 * Setzt die Kosten zur Eröffnung einer Filiale
	 * @param startFilialkosten neue Kosten
	 */
	public void setzeStartFilialkosten(double startFilialkosten) {
		if(startFilialkosten >= 0) {
			this.startFilialkosten = startFilialkosten;
		}
	}
	
	/**
	 * @return Gibt den prozentualen Anteil an den startFilialkosten zurück, den man beim Verkauf bekommt
	 */
	public double holeAnteilFilialverkauf() {
		return anteilFilialverkauf;
	}
	
	/**
	 * Setzt den prozentualen Anteil an den startFilialksoten, den man beim Verkauf einer Filiale bekommt
	 * @param anteilFilialverkauf Zwischen 0 und 1
	 */
	public void setzeAnteilFilialverkauf(double anteilFilialverkauf) {
		if (anteilFilialverkauf <= 1 && anteilFilialverkauf >= 0){
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
	 * @param minKunden Kundenanzahl größer 0
	 */
	public void setzeMinKunden(int minKunden) {
		if(minKunden > 0) {
			this.minKunden = minKunden;
		}
	}
	
	/**
	 * @return Kundenanzahl, die von maxMitarbeiter Mitarbeiter bedient werden können
	 */
	public int holeMaxKunden() {
		return maxKunden;
	}
	
	/**
	 * Setzt die Kundenanzahl, die von maxMitarbeiter Mitarbeiter bedient werden können
	 * @param maxKunden Kundenanzahl größer als minKunden
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
	 * @param maxMitarbeiter Maximale Mitarbeiteranzahl größer 1
	 */
	public void setzeMaxMitarbeiter(int maxMitarbeiter) {
		if(maxMitarbeiter > 1) {
			this.maxMitarbeiter = maxMitarbeiter;
		}
	}
}
