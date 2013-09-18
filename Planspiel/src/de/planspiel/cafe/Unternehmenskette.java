package de.planspiel.cafe;

import java.util.Vector;

public class Unternehmenskette {
	
	private String name;
	private double kapital;
	private Vector<Filiale> filialenListe;	
	private Lager lager;
	private Vector<Kredit> kreditListe;
	private double gehalt;

	public Unternehmenskette(String name){
		this.name = name;
		this.filialenListe = new Vector<Filiale>();
		this.kreditListe = new Vector<Kredit>();
		this.lager = new Lager();
		this.kapital = 0; // TODO Start-Wert hier einpflegen!
		this.gehalt = 0; // TODO Start-Wert ???
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
	
	public void berechnenKosten() {
		// TODO
	}
	
	public void holeAnzahlFilialen(){
		// TODO
	}
	
	public String getName() {
		return name;
	}
	
	public double getKapital() {
		return kapital;
	}
	
	public void setKapital(double kapital) {
		this.kapital = kapital;
	}
	
	public Vector<Filiale> getFilialenListe() {
		return filialenListe;
	}
	
	public void hinzufuegenFiliale(Filiale filiale) {
		filialenListe.add(filiale);
	}
	
	public Lager getLager() {
		return lager;
	}
	
	public Vector<Kredit> getKreditListe() {
		return kreditListe;
	}
	
	public void hinzufuegenKredit(Kredit kredit) {
		kreditListe.add(kredit);
	}
	
	public double getGehalt() {
		return gehalt;
	}
	
	public void setGehalt(double gehalt) {
		this.gehalt = gehalt;
	}
	
}
