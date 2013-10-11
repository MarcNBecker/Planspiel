package de.planspiel.cafe;

public enum Standorttyp {
	Standort1(5000, 1000, 100, 200, 5),
	Standort2(0, 0, 0, 0, 0),
	Standort3(0, 0, 0, 0, 0);
	
	private double laufendeFilialkosten;
	private double startFilialkosten;
	private int minKunden;
	private int maxKunden;
	private int maxMitarbeiter;
	
	private Standorttyp(double laufendeFilialkosten, double startFilialkosten, int minKunden, int maxKunden, int maxMitarbeiter) {
		this.laufendeFilialkosten = laufendeFilialkosten;
		this.startFilialkosten = startFilialkosten;
		this.minKunden = minKunden;
		this.maxKunden = maxKunden;
		this.maxMitarbeiter = maxMitarbeiter;
	}
	
	public double holeLaufendeFilialkosten() {
		return laufendeFilialkosten;
	}
	
	public double holeStartFilialkosten() {
		return startFilialkosten;
	}
	
	public int holeMinKunden() {
		return minKunden;
	}
	
	public int holeMaxKunden() {
		return maxKunden;
	}
	
	public int holeMaxMitarbeiter() {
		return maxMitarbeiter;
	}
}
