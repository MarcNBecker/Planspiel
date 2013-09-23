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
	 * Erzeugt ein Unternehmen mit den Spiel Startwerten für Kapital, Gehalt und Entlassungskosten
	 * @param name Name des Unternehmens, dieser wird vom Spieler zu Beginn des Spiels festgelegt
	 */
	public Unternehmenskette(String name){
		this.name = name;
		this.filialenListe = new Vector<Filiale>();
		this.reportListe = new Vector<Report>();
		this.kreditListe = new Vector<Kredit>();
		this.lager = new Lager();
		this.kapital = 0; // TODO Start-Wert
		this.gehalt = 0; // TODO Start-Wert
		this.entlassungskosten = 0; // TODO Start-Wert
	}
	
	public void eroeffnenFiliale(Filiale filiale) {
		// TODO
	}
	
	public void schließenFiliale(Filiale filiale) {
		// TODO
	}
	
	/**
	 * Fügt den Kredit dem Unternehmen hinzu, wenn der Betrag noch im Rahmen der Kreditwürdigkeit liegt
	 * @param betrag
	 */
	public void aufnehmenKredit(double betrag) {
		if(betrag <= pruefeKreditwuerdigkeit()){
			hinzufuegenKredit(new Kredit(this, betrag));
		}
	}
	
	/**
	 * Hier wird geprüft welchen Kreditbetrag der Spieler maximal aufnehmen kann.
	 * Dabei wird das Verhältnis 1:3 von FK:EK eingehalten
	 * @return Maximaler Betrag, der dem Spieler als Kredit gewährt werden kann
	 */
	public double pruefeKreditwuerdigkeit() {
		double fremdkapital = 0;
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
		if(kredit.holeRestbetrag() == 0){
			kreditListe.remove(kredit);
		}
	}
	
	public void verbuchenKosten(Kostenverursacher verursacher, double betrag){
		// TODO
	}
	
	public void berechnenFilialKosten() {
		// TODO
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
