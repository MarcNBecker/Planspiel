package de.planspiel.cafe;

import java.util.Vector;

/**
 * Klasse zur Simulation der Unternehmenskette.
 * Diese ist die oberste Einheit eines Spielers und ist direkt dem Spiel untergeordnet.
 * @author D059166
 *
 */
public class Unternehmenskette {
	
	private String name;
	private double kapital;
	private Vector<Report> reportListe;
	private Vector<Filiale> filialenListe;	
	private Lager lager;
	private Vector<Kredit> kreditListe;
	private double gehalt;
	private double entlassungskosten;
	
	/**
	 * Erzeugt ein Unternehmen mit den Spiel Startwerten f�r Kapital, Gehalt und Entlassungskosten
	 * @param name Name des Unternehmens, dieser wird vom Spieler zu Beginn des Spiels festgelegt
	 */
	public Unternehmenskette(String name){
		this.name = name;
		this.filialenListe = new Vector<Filiale>();
		this.reportListe = new Vector<Report>();
		this.kreditListe = new Vector<Kredit>();
		this.lager = new Lager(this);
		this.kapital = 0; // TODO Start-Wert
		this.gehalt = 0; // TODO Start-Wert
		this.entlassungskosten = 0; // TODO Start-Wert
	}
	
	/**
	 * Erzeugt ein neues Filale-Objekt und gibt dieses an den Standort weiter
	 * @param standort Standort an dem das Filialeobjekt erzeugt werden soll
	 */
	public void eroeffnenFiliale(Standort standort) {
		Filiale neueFiliale = new Filiale(standort, this);
		hinzufuegenFiliale(neueFiliale);
	}
	
	public void schlie�enFiliale(Filiale filiale) {
		if(holeFilialenListe().contains(filiale)){
			holeFilialenListe().remove(filiale);
			filiale.holeStandort().entfernenFiliale(filiale);
		}
	}
	
	/**
	 * F�gt den Kredit dem Unternehmen hinzu, wenn der Betrag noch im Rahmen der Kreditw�rdigkeit liegt
	 * @param betrag
	 */
	public void aufnehmenKredit(double betrag) {
		if(betrag <= pruefeKreditwuerdigkeit()){
			hinzufuegenKredit(new Kredit(this, betrag));
			setzeKapital(holeKapital() + betrag);
		}
	}
	
	/**
	 * Hier wird gepr�ft welchen Kreditbetrag der Spieler maximal aufnehmen kann.
	 * Dabei wird das Verh�ltnis 1:3 von FK:EK eingehalten
	 * @return Maximaler Betrag, der dem Spieler als Kredit gew�hrt werden kann
	 */
	public double pruefeKreditwuerdigkeit() {
		double fremdkapital = 0.0;
		for(int i=0; i<holeKreditListe().size(); i++){
			Kredit kredit = holeKreditListe().get(i);
			fremdkapital = fremdkapital + kredit.holeRestbetrag();
		}
		double eigenkapital = holeKapital() - fremdkapital;
		double maxKredit = eigenkapital * 3 - fremdkapital;
		if (maxKredit <= 0){
			return 0;
		} else {
			return maxKredit;
		}
	}
	
	public void entfernenKredit(Kredit kredit) {
		if(kredit.holeRestbetrag() == 0.0){
			kreditListe.remove(kredit);
		}
	}
	
	public void verbuchenKosten(Kostenverursacher verursacher, double betrag){
		// TODO
	}
	
	public void berechnenFilialKosten() {
		
	}
	
	public int holeAnzahlFilialen(){
		return filialenListe.size();
	}
	
	public String holeName() {
		return name;
	}
	
	
	public double holeKapital() {
		return kapital;
	}
	
	public void setzeKapital(double kapital) {
		this.kapital = kapital;
	}
	
	public Vector<Filiale> holeFilialenListe() {
		return filialenListe;
	}
	
	public void hinzufuegenFiliale(Filiale filiale) {
		filialenListe.add(filiale);
	}
	
	public Vector<Report> holeReportListe() {
		return reportListe;
	}
	
	public void hinzufuegenReport(Report report) {
		reportListe.add(report);
	}
	
	public Lager holeLager() {
		return lager;
	}
	
	public Vector<Kredit> holeKreditListe() {
		return kreditListe;
	}
	
	public void hinzufuegenKredit(Kredit kredit) {
		kreditListe.add(kredit);
	}
	
	public double holeGehalt() {
		return gehalt;
	}
	
	public void setzeGehalt(double gehalt) {
		this.gehalt = gehalt;
	}
	
	public void setzeEntlassungskosten(double entlassungskosten) {
		this.entlassungskosten = entlassungskosten;
	}
	
	public double holeEntlassungskosten() {
		return entlassungskosten;
	}
}
