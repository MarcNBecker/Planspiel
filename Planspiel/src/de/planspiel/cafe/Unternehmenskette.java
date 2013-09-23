package de.planspiel.cafe;

import java.util.Vector;

public class Unternehmenskette {
	
	private String name;
	private double kapital;
	private Vector<Filiale> filialenListe;	
	private Lager lager;
	private Vector<Kredit> kreditListe;
	private double gehalt;
	private double entlassungskosten;

	public Unternehmenskette(String name){
		this.name = name;
		this.filialenListe = new Vector<Filiale>();
		this.kreditListe = new Vector<Kredit>();
		this.lager = new Lager();
		this.kapital = 0; // TODO Start-Wert
		this.gehalt = 0; // TODO Start-Wert
		this.entlassungskosten = 0; // TODO Start-Wert
	}
	
	public void eroeffnenFiliale(Filiale filiale) {
		// TODO
	}
	
	public void schlieﬂenFiliale(Filiale filiale) {
		// TODO
	}
	
	public void aufnehmenKredit(Kredit kredit) {
		// TODO	
	}
	
	public void entfernenKredit(Kredit kredit) {
		// TODO
	}
	
	public void verbuchenKosten(Kostenverursacher verursacher, double betrag){
		// TODO
	}
	
	public void berechnenFilialKosten() {
		// TODO
	}
	
	public void holeAnzahlFilialen(){
		// TODO
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
