package de.planspiel.cafe;

import java.util.Vector;

/**
 * Klasse zur Simulation der Unternehmenskette.
 * Diese ist die oberste Einheit eines Spielers und ist direkt dem Spiel untergeordnet.
 * @author Marc Becker
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
		setzeKapital(0); // TODO Start-Wert
		setzeGehalt(0); // TODO Start-Wert
		setzeEntlassungskosten(0); // TODO Start-Wert
	}
	
	/**
	 * Erzeugt ein neues Filale-Objekt und gibt dieses an den Standort weiter
	 * @param standort Standort an dem das Filialeobjekt erzeugt werden soll
	 */
	public void eroeffnenFiliale(Standort standort) {
		Filiale neueFiliale = new Filiale(standort, this);
		hinzufuegenFiliale(neueFiliale);
	}
	
	/**
	 * Entfernt eine Filiale vom Standort und der Unternehmenskette
	 * @param filiale Filiale, die geschlossen werden soll
	 */
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
	
	/**
	 * Entfernt einen Kredit vom Unternehmen
	 * @param kredit
	 */
	public void entfernenKredit(Kredit kredit) {
		if(kredit != null && kredit.holeRestbetrag() == 0.0){
			kreditListe.remove(kredit);
		}
	}
	
	public void verbuchenKosten(Kostenverursacher verursacher, double betrag){
		// TODO
	}
	
	public void verbuchenErtrag(Ertragsverursacher verursacher, double betrag){
		// TODO
	}
	
	public void berechnenFilialKosten() {
		// TODO
	}
	
	/**
	 * @return Anzahl der Filialen des Unternehmens
	 */
	public int holeAnzahlFilialen(){
		return filialenListe.size();
	}
	
	/**
	 * @return Name der Unternehmenskette
	 */
	public String holeName() {
		return name;
	}
	
	/**
	 * @return Kapital der Unternehmenskette
	 */
	public double holeKapital() {
		return kapital;
	}
	
	/**
	 * Setzt das Kapital der Unternehmenskette neu
	 * @param kapital
	 */
	public void setzeKapital(double kapital) {
		if(kapital >= 0) {
			this.kapital = kapital;
		} else {
			// TODO Was passiert wenn das Kapital unter 0 f�llt?
		}
	}
	
	/**
	 * @return Liste aller Filialen des Unternehmens
	 */
	public Vector<Filiale> holeFilialenListe() {
		return filialenListe;
	}
	
	/**
	 * F�gt eine Filiale zum Unternehmen hinzu
	 * @param filiale Filiale ungleich null
	 */
	private void hinzufuegenFiliale(Filiale filiale) {
		if(filiale != null) {
			filialenListe.add(filiale);
		}
	}
	
	/**
	 * @return Liste aller Reports des Unternehmens
	 */
	public Vector<Report> holeReportListe() {
		return reportListe;
	}
	
	/**
	 * F�gt einen neuen Report hinzu
	 * @param report Report ungleich null
	 */
	public void hinzufuegenReport(Report report) {
		if(report != null) {
			reportListe.add(report);
		}
	}
	
	/**
	 * @return Gibt das Lager zur�ck
	 */
	public Lager holeLager() {
		return lager;
	}
	
	/**
	 * @return Gibt eine Liste aller Kredite zur�ck
	 */
	public Vector<Kredit> holeKreditListe() {
		return kreditListe;
	}
	
	/**
	 * F�gt einen Kredit der Kreditliste hinzu
	 * @param kredit Kredit ungleich null
	 */
	private void hinzufuegenKredit(Kredit kredit) {
		if(kredit != null) {
			kreditListe.add(kredit);
		}
	}
	
	/**
	 * @return Mitarbeitergehalt
	 */
	public double holeGehalt() {
		return gehalt;
	}
	
	/**
	 * Setzt das Mitarbeitergehalt
	 * @param gehalt Gehalt gr��er gleich 0
	 */
	public void setzeGehalt(double gehalt) {
		if(gehalt >= 0) {
			this.gehalt = gehalt;
		}
	}
	
	/**
	 * Setzt die Kosten zur Entlassung eines Mitarbeiters
	 * @param entlassungskosten Kosten gr��er gleich 0
	 */
	public void setzeEntlassungskosten(double entlassungskosten) {
		if(entlassungskosten >= 0) {
			this.entlassungskosten = entlassungskosten;
		}
	}
	
	/**
	 * @return Entlassungskosten
	 */
	public double holeEntlassungskosten() {
		return entlassungskosten;
	}
}
