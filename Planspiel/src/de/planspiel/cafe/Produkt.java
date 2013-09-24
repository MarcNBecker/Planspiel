package de.planspiel.cafe;

public class Produkt {
	
	private Produkttyp name;
	private double qualitaet;
	private double preis;
	private double ekpreis;
	private int menge;
	
	public Produkt(Produkttyp name) {
		this.name = name;
		this.qualitaet = -1;
		this.preis = -1;
		this.ekpreis = -1;
		this.menge = 0;
	}
	
	/**
	 * Konstruktor für Produkte, die vom Händler zufällig erzeugt werden
	 * @param name Enum-Wert, um welches Produkt es sich handelt
	 * @param qualitaet Double-Wert, der die Produktqualitaet angibt
	 * @param ekpreis Double-Wert, der den Einkaufspreis angibt
	 * @author Natalie
	 */
	public Produkt (Produkttyp name, double qualitaet, double ekpreis){
		this.name = name;
		this.qualitaet = qualitaet;
		this.preis = -1;
		this.ekpreis = ekpreis;
		this.menge = 0;
	}
	
	/** 
	 * Vergleicht ein übergebenes Produkt mit sich selbst (Obergrenze Preis, Untergrenze Qualität)
	 * @param produkt Produkt, dessen Name, Qualitaet und Preis mit sich selbst verglichen werden
	 * @return true falls die Produkte übereinstimmen, false falls die Produkte nicht übereinstimmen
	 * @author Natalie
	 */
	public boolean vergleichen(Produkt produkt) {
		// Prüfen: Name gleich, Qualität gleich oder höher, Preis gleich oder kleiner
		if (produkt.holeName().equals(this.holeName()) 
				&& this.holeQualitaet() >= produkt.holeQualitaet() 
				&& this.holePreis() <= produkt.holePreis())
			return true;
		else
			return false;	
	}
	
	/**
	 * Verschmilzt das bereits vorhandene Produkt mit dem Übergebenen, berechnet neue Durchschnittsqualitaet,
	 * Durchschnittsekpreis und Gesamtmenge und übernimmt Verkaufspreis vom aufrufenden Produkt
	 * @param produkt Produkt, das mit dem Existierenden verschmolzen wird
	 * @return null falls es sich nicht um gleichnamige Produkte handet, Produkt falls es sich um die gleichen Produkte handelt 
	 * @author Natalie
	 */
	public Produkt verschmelzen(Produkt produkt) {
		if (produkt.holeName().equals(this.holeName()))
			return null;
		else { //Else-Zweig nicht zwingend notwendig
			//Berechne Gesamtmenge, neue durchschnittliche Qualität und neuen durchschnittlichen EkPreis, übernimm Werte 
			int gesamtmenge = this.holeMenge() + produkt.holeMenge();
			double ekPreisDurchschnitt = (this.holeEkpreis() * (double) this.holeMenge() + produkt.holeEkpreis() * (double) produkt.holeMenge()) / (double) (gesamtmenge);
			double qualitaetDurchschnitt = (this.holeQualitaet() * (double) this.holeMenge() + produkt.holeQualitaet() * (double) produkt.holeMenge()) / (double) (gesamtmenge);
			
			this.setzeQualitaet(qualitaetDurchschnitt);
			this.setzeEkpreis(ekPreisDurchschnitt);
			this.setzeMenge(gesamtmenge);
			return this;
		}
	}
	
	
	public Produkttyp holeName() {
		return this.name;
	}
	
	public double holeQualitaet() {
		return this.qualitaet;
	}
	
	public double holePreis() {
		return this.preis;
	}
	
	public double holeEkpreis() {
		return this.ekpreis;
	}
	
	public int holeMenge() {
		return this.menge;
	}
	
	public void setzeQualitaet(double qualitaet) {
		this.qualitaet = qualitaet;
	}
	
	public void setzePreis(double preis) {
		this.preis = preis;
	}
	
	public void setzeEkpreis(double ekpreis) {
		this.ekpreis = ekpreis;
	}
	
	public void setzeMenge(int menge) {
		//Negative Mengen sind nicht zulässig
		if (menge < 0)
			menge = 0;
		this.menge = menge;
	}
}
