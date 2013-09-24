package de.planspiel.cafe;

/**
 * Klasse zur Organisation von Produkten
 * Diese Klasse wird auch als Kommunikation zwischen Produkt-Verwaltern genutzt
 * @author Natalie
 *
 */
public class Produkt {
	
	private Produkttyp name;
	private double qualitaet;
	private double preis;
	private double ekpreis;
	private int menge;
	
	/**
	 * Erstellt ein Produkt anhand seines Produkttyps
	 * @param name Produkttyp
	 */
	public Produkt(Produkttyp name) {
		this.name = name;
		setzeQualitaet(0);
		setzePreis(0);
		setzeEkpreis(0);
		setzeMenge(0);
	}
	
	/**
	 * Konstruktor f�r Produkte, die vom H�ndler zuf�llig erzeugt werden
	 * @param name Enum-Wert, um welches Produkt es sich handelt
	 * @param qualitaet Double-Wert, der die Produktqualitaet angibt
	 * @param ekpreis Double-Wert, der den Einkaufspreis angibt
	 * @author Natalie
	 */
	public Produkt (Produkttyp name, double qualitaet, double ekpreis){
		this.name = name;
		setzeQualitaet(qualitaet);
		setzePreis(0);
		setzeEkpreis(ekpreis);
		setzeMenge(0);
	}
	
	/** 
	 * Vergleicht ein �bergebenes Produkt mit sich selbst (Obergrenze Preis, Untergrenze Qualit�t)
	 * @param produkt Produkt, dessen Name, Qualitaet und Preis mit sich selbst verglichen werden
	 * @return true falls die Produkte �bereinstimmen, false falls die Produkte nicht �bereinstimmen
	 * @author Natalie
	 */
	public boolean vergleichen(Produkt produkt) {
		// Pr�fen: Name gleich, Qualit�t gleich oder h�her, Preis gleich oder kleiner
		if (produkt.holeName().equals(this.holeName()) 
				&& this.holeQualitaet() >= produkt.holeQualitaet() 
				&& this.holePreis() <= produkt.holePreis())
			return true;
		else
			return false;	
	}
	
	/**
	 * Verschmilzt das bereits vorhandene Produkt mit dem �bergebenen, berechnet neue Durchschnittsqualitaet,
	 * Durchschnittsekpreis und Gesamtmenge und �bernimmt Verkaufspreis vom aufrufenden Produkt
	 * @param produkt Produkt, das mit dem Existierenden verschmolzen wird
	 * @return null falls es sich nicht um gleichnamige Produkte handet, Produkt falls es sich um die gleichen Produkte handelt 
	 * @author Natalie
	 */
	public Produkt verschmelzen(Produkt produkt) {
		if (produkt.holeName().equals(this.holeName()))
			return null;
		else { //Else-Zweig nicht zwingend notwendig
			//Berechne Gesamtmenge, neue durchschnittliche Qualit�t und neuen durchschnittlichen EkPreis, �bernimm Werte 
			int gesamtmenge = this.holeMenge() + produkt.holeMenge();
			double ekPreisDurchschnitt = (this.holeEkpreis() * (double) this.holeMenge() + produkt.holeEkpreis() * (double) produkt.holeMenge()) / (double) (gesamtmenge);
			double qualitaetDurchschnitt = (this.holeQualitaet() * (double) this.holeMenge() + produkt.holeQualitaet() * (double) produkt.holeMenge()) / (double) (gesamtmenge);
			
			this.setzeQualitaet(qualitaetDurchschnitt);
			this.setzeEkpreis(ekPreisDurchschnitt);
			this.setzeMenge(gesamtmenge);
			return this;
		}
	}
	
	/**
	 * @return Produkttyp des Produkts
	 */
	public Produkttyp holeName() {
		return this.name;
	}
	
	/**
	 * @return Qualit�t des Produkts
	 */
	public double holeQualitaet() {
		return this.qualitaet;
	}
	
	/**
	 * @return Verkaufspreis des Produkts
	 */
	public double holePreis() {
		return this.preis;
	}
	
	/**
	 * @return Einkaufspreis des Produkts
	 */
	public double holeEkpreis() {
		return this.ekpreis;
	}
	
	/**
	 * @return Menge in der das Produkt vorliegt
	 */
	public int holeMenge() {
		return this.menge;
	}
	
	/**
	 * Setzt die neue Qualit�t des Produktes
	 * @param qualitaet Qualit�t zwischen 0 und 1
	 */
	public void setzeQualitaet(double qualitaet) {
		if(qualitaet >= 0 && qualitaet <= 1) {
			this.qualitaet = qualitaet;
		}
	}
	
	/**
	 * Setzt den neuen Verkaufspreis
	 * @param preis Preis gr��er 0
	 */
	public void setzePreis(double preis) {
		if(preis >= 0){
			this.preis = preis;
		}
	}
	
	/**
	 * Setzt den neuen Einkaufspreis
	 * @param ekpreis Preis gr��er 0
	 */
	public void setzeEkpreis(double ekpreis) {
		if(ekpreis >= 0){
			this.ekpreis = ekpreis;
		}
	}
	
	/**
	 * Setzt die neue Menge in der das Produkt vorliegt
	 * @param menge Menge wird auf 0 gekappt, wenn sie kleiner als 0 ist.
	 */
	public void setzeMenge(int menge) {
		//Negative Mengen sind nicht zul�ssig
		if (menge < 0) {
			menge = 0;
		}
		this.menge = menge;
	}
}
