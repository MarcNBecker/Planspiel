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
	
	public Standort(){
		this.kundenkreis = new Vector<Kunde>();
		this.filialenListe = new Vector<Filiale>();
	}
	
	public Standort(double laufendeFilialkosten, double startFilialkosten, double anteilFilialverkauf, int minKunden, int maxKunden, int maxMitarbeiter){
		this();
		setzeLaufendeFilialkosten(laufendeFilialkosten);
		setzeStartFilialkosten(startFilialkosten);
		setzeAnteilFilialverkauf(anteilFilialverkauf);
		setzeMinKunden(minKunden);
		setzeMaxKunden(maxKunden);
		setzeMaxMitarbeiter(maxMitarbeiter);
	}
	
	public void generierenKundenliste() {
		// TODO
	}
	
	public void beeinflussenKunden(Unternehmenskette kette) {
		// TODO
	}
	
	/**
	 * Berechnet die Kunden, die an diesem Standort von der �bergebenen Menge an Mitarbeiter bedient werden k�nnen.
	 * Dem liegt eine lineare Funktion zu Grunde, die durch die Attribute minKunden, maxKunden und maxMitarbeiter des Standortes
	 * definiert werden
	 * @param mitarbeiter Aktuelle Mitarbeiteranzahl der Filiale, f�r die die Kapazit�t berechnet werden soll
	 * @return Kundenkapazit�t, also die Anzahl der Kunden, die in dieser Periode bedient werden k�nnen
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
	
	public Vector<Kunde> holeKundenkreis() {
		return kundenkreis;
	}
	
	public void hinzufuegenKunde(Kunde kunde) {
		kundenkreis.add(kunde);
	}
	
	public double holeLaufendeFilialkosten() {
		return laufendeFilialkosten;
	}
	
	public void setzeLaufendeFilialkosten(double laufendeFilialkosten) {
		if (laufendeFilialkosten >= 0) {
			this.laufendeFilialkosten = laufendeFilialkosten;
		}
	}
	
	public Vector<Filiale> holeFilialenListe() {
		return filialenListe;
	}
	
	/**
	 * F�gt eine Filiale zum Standort hinzu und verbucht die passenden Kosten mit der Unternehmenskette
	 * @param filiale neue Filiale
	 */
	public void hinzufuegenFiliale(Filiale filiale){
		Unternehmenskette kette = filiale.holeKette();
		kette.verbuchenKosten(Kostenverursacher.FILIALE_ANSCHAFFUNG, holeStartFilialkosten());
		filialenListe.add(filiale);
	}
	
	
	public void entfernenFiliale(Filiale filiale) {
		if(holeFilialenListe().contains(filiale)){
			holeFilialenListe().remove(filiale);
			filiale.holeKette().verbuchenErtrag(Ertragsverursacher.FILIALE_VERKAUF, holeAnteilFilialverkauf() * holeStartFilialkosten());
		}
	}
	/**
	 * Gibt die Kosten zur�ck, die zum er�ffnen einer neuen Filiale ben�tigt werden
	 * @return Kosten zur Er�ffnung
	 */
	public double holeStartFilialkosten() {
		return startFilialkosten;
	}
	
	/**
	 * Setzt die Kosten zur Er�ffnung einer Filiale
	 * @param startFilialkosten neue Kosten
	 */
	public void setzeStartFilialkosten(double startFilialkosten) {
		if(startFilialkosten >= 0) {
			this.startFilialkosten = startFilialkosten;
		}
	}
	
	public double holeAnteilFilialverkauf() {
		return anteilFilialverkauf;
	}
	
	public void setzeAnteilFilialverkauf(double anteilFilialverkauf) {
		if (anteilFilialverkauf <= 1 && anteilFilialverkauf >= 0){
			this.anteilFilialverkauf = anteilFilialverkauf;
		} else {
			this.anteilFilialverkauf = 0;
		}
	}
	
	public int holeMinKunden() {
		return minKunden;
	}
	
	public void setzeMinKunden(int minKunden) {
		if(minKunden > 0) {
			this.minKunden = minKunden;
		}
	}
	
	public int holeMaxKunden() {
		return maxKunden;
	}
	
	public void setzeMaxKunden(int maxKunden) {
		if (maxKunden > holeMinKunden()) {
			this.maxKunden = maxKunden;
		}
	}
	
	public int holeMaxMitarbeiter() {
		return maxMitarbeiter;
	}
	
	public void setzeMaxMitarbeiter(int maxMitarbeiter) {
		if(maxMitarbeiter > 1) {
			this.maxMitarbeiter = maxMitarbeiter;
		}
	}
}
