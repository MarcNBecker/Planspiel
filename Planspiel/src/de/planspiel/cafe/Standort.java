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
	private int minKunden; //Anzahl der zu bedienenden Kunden ab einem Mitarbeiter
	private int maxKunden; //Anzahl der zu bedienenden Kunden ab maxMitarbeiter Mitarbeiter
	private int maxMitarbeiter;
	
	public Standort(){
		this.kundenkreis = new Vector<Kunde>();
		this.filialenListe = new Vector<Filiale>();
	}
	
	public Standort(double laufendeFilialkosten, double startFilialkosten, int minKunden, int maxKunden, int maxMitarbeiter){
		this();
		this.laufendeFilialkosten = laufendeFilialkosten;
		this.startFilialkosten = startFilialkosten;
		this.minKunden = minKunden;
		this.maxKunden = maxKunden;
		this.maxMitarbeiter = maxMitarbeiter;
	}
	
	public void generierenKundenliste() {
		// TODO
	}
	
	public void beeinflussenKunden(Unternehmenskette kette) {
		// TODO
	}
	
	public int berechnenKapazitaet(int mitarbeiter) {
		
		return 0;
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
		this.laufendeFilialkosten = laufendeFilialkosten;
	}
	
	public Vector<Filiale> holeFilialenListe() {
		return filialenListe;
	}
	
	public void hinzufuegenFiliale(Filiale filiale){
		// TODO ??
		filialenListe.add(filiale);
	}
	
	public double holeStartFilialkosten() {
		return startFilialkosten;
	}
	
	public void setzeStartFilialkosten(double startFilialkosten) {
		this.startFilialkosten = startFilialkosten;
	}
	
	public int holeMinKunden() {
		return minKunden;
	}
	
	public void setzeMinKunden(int minKunden) {
		this.minKunden = minKunden;
	}
	
	public int holeMaxKunden() {
		return maxKunden;
	}
	
	public void setzeMaxKunden(int maxKunden) {
		this.maxKunden = maxKunden;
	}
	
	public int holeMaxMitarbeiter() {
		return maxMitarbeiter;
	}
	
	public void setzeMaxMitarbeiter(int maxMitarbeiter) {
		this.maxMitarbeiter = maxMitarbeiter;
	}
}
