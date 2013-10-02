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
	private boolean pleite;
	private double kasse;
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
		this.lager = new Lager(this);
		setzePleite(false);
		setzeKasse(0); // TODO Start-Wert
		setzeGehalt(0); // TODO Start-Wert
		setzeEntlassungskosten(0); // TODO Start-Wert
	}
	
	/**
	 * Erzeugt ein neues Filale-Objekt und gibt dieses an den Standort weiter
	 * @param standort Standort an dem das Filialeobjekt erzeugt werden soll
	 */
	public void eroeffnenFiliale(Standort standort) {
		//Der Konstrukter von Filiale ruft die entsprechende Standort-Methode auf
		Filiale neueFiliale = new Filiale(standort, this);
		hinzufuegenFiliale(neueFiliale);
	}
	
	/**
	 * Entfernt eine Filiale ohne Mitarbeiter vom Standort und der Unternehmenskette
	 * @param filiale Filiale, die geschlossen werden soll
	 */
	public void schließenFiliale(Filiale filiale) {
		if(holeFilialenListe().contains(filiale) && filiale.holeMitarbeiter() == 0){
			holeFilialenListe().remove(filiale);
			filiale.holeStandort().entfernenFiliale(filiale);
		}
	}
	
	/**
	 * Berechnet das gesamte Kapital der Unternehmenskette.
	 * Dieses setzt sich aus der Kasse (Kapital), dem Wert der Filialen und dem Wert des Lagers zusammen
	 * @return gesamtes Kapital des Unternehmens
	 */
	public double berechnenGesamtkapital() {
		double filialenWert = 0.0;
		for(int i=0; i<holeFilialenListe().size(); i++) {
			Filiale filiale = holeFilialenListe().get(i);
			filialenWert += filiale.berechnenWert();
		}
		double gesamtkapital = holeKasse() + filialenWert + holeLager().berechnenWert();
		return gesamtkapital;
	}
	
	/**
	 * Berechnet das Fremdkapital im Unternehmen. Dies entspricht den Restbeträgen aller Kredite
	 * @return Fremdkapital des Unternehmen
	 */
	public double berechnenFremdkapital() {
		double fremdkapital = 0.0;
		for(int i=0; i<holeKreditListe().size(); i++){
			Kredit kredit = holeKreditListe().get(i);
			fremdkapital = fremdkapital + kredit.holeRestbetrag();
		}
		return fremdkapital;
	}
	
	/**
	 * Fügt den Kredit dem Unternehmen hinzu, wenn der Betrag noch im Rahmen der Kreditwürdigkeit liegt
	 * @param betrag
	 */
	public void aufnehmenKredit(double betrag) {
		if(betrag <= pruefeKreditwuerdigkeit()){
			hinzufuegenKredit(new Kredit(this, betrag));
			setzeKasse(holeKasse() + betrag);
		}
	}
	
	/**
	 * Hier wird geprüft welchen Kreditbetrag der Spieler maximal aufnehmen kann.
	 * Dabei wird das Verhältnis 1:3 von FK:EK eingehalten
	 * @return Maximaler Betrag, der dem Spieler als Kredit gewährt werden kann
	 */
	public double pruefeKreditwuerdigkeit() {
		double fremdkapital = berechnenFremdkapital();
		double eigenkapital = berechnenGesamtkapital() - fremdkapital;
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
	
	public boolean verbuchenKosten(Kostenverursacher verursacher, double betrag){
		if(verursacher == null) {
			return false;
		}
		
		//Die Reports werden an der Stelle "Runde des Reports" - 1 gespeichert!!
		Report aktuellerReport = holeReportListe().get(Spiel.holeSpiel().holeAktuelleRunde()-1);
		setzeKasse(holeKasse() - betrag);
		if(holePleite()){
			return false; //TODO Exception Konzept muss eigentlich umgesetzt werden
		}
		aktuellerReport.setzeKapital(aktuellerReport.holeKapital() - betrag);
		
		switch(verursacher){
		case FILIALE_ANSCHAFFUNG:
			aktuellerReport.setzeAnschaffungskosten(aktuellerReport.holeAnschaffungskosten() + betrag);
		case FILIALE_UNTERHALTUNG:
			aktuellerReport.setzeUnterhaltungskosten(aktuellerReport.holeUnterhaltungskosten() + betrag);
		case KREDIT:
			aktuellerReport.setzeKreditkosten(aktuellerReport.holeKreditkosten() + betrag);
		case MARKETING:
			aktuellerReport.setzeMarketingkosten(aktuellerReport.holeMarketingkosten() + betrag);
		case PERSONAL:
			aktuellerReport.setzePersonalkosten(aktuellerReport.holePersonalkosten() + betrag);
		case ROHSTOFF:
			aktuellerReport.setzeRohstoffkosten(aktuellerReport.holeRohstoffkosten() + betrag);
		}
		return true;
	}
	
	public void verbuchenErtrag(Ertragsverursacher verursacher, double betrag){
		if(verursacher == null) {
			return;
		}
		
		//Die Reports werden an der Stelle "Runde des Reports" - 1 gespeichert!!
		Report aktuellerReport = holeReportListe().get(Spiel.holeSpiel().holeAktuelleRunde()-1);
		aktuellerReport.setzeKapital(aktuellerReport.holeKapital() + betrag);
		setzeKasse(holeKasse() + betrag);
		
		switch(verursacher) {
		case FILIALE_VERKAUF:
			aktuellerReport.setzeSonstigeErloese(aktuellerReport.holeSonstigeErloese() + betrag);
		case UMSATZERLOESE:
			aktuellerReport.setzeUmsatzerloese(aktuellerReport.holeUmsatzerloese() + betrag);
		}
	}
	
	public void berechnenFilialKosten() {
		for (int i=0; i<holeFilialenListe().size(); i++){
			Filiale filiale = holeFilialenListe().get(i);
			filiale.berechnenKosten();
		}
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
	 * @return Gibt zurück ob das Unternehmen pleite gegangen ist
	 */
	public boolean holePleite() {
		return pleite;
	}
	
	/**
	 * Setzt das Unternehmen pleite
	 * @param pleite
	 */
	public void setzePleite(boolean pleite) {
		this.pleite = pleite;
	}
	
	/**
	 * @return Kasse der Unternehmenskette
	 */
	public double holeKasse() {
		return kasse;
	}
	
	/**
	 * Setzt die Kasse der Unternehmenskette neu
	 * @param kasse
	 */
	public void setzeKasse(double kasse) {
		if(kasse >= 0) {
			this.kasse = kasse;
		} else {
			aufnehmenKredit(Math.abs(kasse)); //Versuche einen Kredit aufzunehmen
			if(holeKasse() < 0) { //Versuch fehlgeschlagen => Pleite, da keine Kreditwürdigkeit mehr
				setzePleite(true);
			}
		}
	}
	
	/**
	 * @return Liste aller Filialen des Unternehmens
	 */
	public Vector<Filiale> holeFilialenListe() {
		return filialenListe;
	}
	
	/**
	 * Fügt eine Filiale zum Unternehmen hinzu
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
	 * Fügt einen neuen Report hinzu
	 * @param report Report ungleich null
	 */
	public void hinzufuegenReport(Report report) {
		if(report != null) {
			reportListe.add(report);
		}
	}
	
	/**
	 * @return Gibt das Lager zurück
	 */
	public Lager holeLager() {
		return lager;
	}
	
	/**
	 * @return Gibt eine Liste aller Kredite zurück
	 */
	public Vector<Kredit> holeKreditListe() {
		return kreditListe;
	}
	
	/**
	 * Fügt einen Kredit der Kreditliste hinzu
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
	 * @param gehalt Gehalt größer gleich 0
	 */
	public void setzeGehalt(double gehalt) {
		if(gehalt >= 0) {
			this.gehalt = gehalt;
		}
	}
	
	/**
	 * Setzt die Kosten zur Entlassung eines Mitarbeiters
	 * @param entlassungskosten Kosten größer gleich 0
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
