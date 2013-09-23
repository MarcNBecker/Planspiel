package de.planspiel.cafe;

public class Filiale {

	private int mitarbeiter;
	private Standort standort;
	private Unternehmenskette kette;
	private int freieKapazitaet;
	
	public Filiale(Standort standort, Unternehmenskette kette){
		this.standort = standort;
		this.kette = kette;
		this.mitarbeiter = 0;
		this.freieKapazitaet = 0;
	}
	
	public int verkaufen(String name, int menge) {
		// TODO
		return 0;
	}
	
	public boolean pruefenKundenprodukt(Produkt produkt) {
		// TODO
		return false;
	}
	
	public void berechnenKapazitaet() {
		// TODO
	}
	
	public void berechnenKosten() {
		// TODO
	}
	
	/**
	 * @author Daniel
	 */
	public void einstellenMitarbeiter() {
		// TODO Kostet das Einstellen von Mitarbeitern Geld?
		this.setzeMitarbeiter(this.holeMitarbeiter()+1);
	}
	
	/**
	 * @author Daniel
	 */
	public void entlassenMitarbeiter() {
		// TODO Muss Geld daf�r bezahlt werden, um ein Mitarbeiter zu entlassen?
		this.setzeMitarbeiter(this.holeMitarbeiter()-1);
		// Soll ein Mitarbeiter entlassen werden, dann k�nnen wir auf das ENUM f�r die Kostentr�ger zur�ckgreifen. (Kosten f�r die Entlasstung von Mitarbeitern)
		// Verbuchung mit kette.verbuchen();
	}
	
	public void initialisierenKapazitaet() {
		// TODO
	}
	
	public int holeMitarbeiter() {
		return mitarbeiter;
	}
	
	public void setzeMitarbeiter(int mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	
	public Standort holeStandort() {
		return standort;
	}
	
	public Unternehmenskette holeKette() {
		return kette;
	}
	
	public int holeFreieKapazitaet() {
		return freieKapazitaet;
	}
	
	public void setzeFreieKapazitaet(int freieKapazitaet) {
		this.freieKapazitaet = freieKapazitaet;
	}
	
}
