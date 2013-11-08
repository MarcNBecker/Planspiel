package de.planspiel.cafe;

/**
 * Definition eines Standorts
 * @author Marc Becker
 */

public enum Standorttyp {
	Standort1(5000, 1000, 100, 200, 5), // Test-Standort
	Standort2(2000, 100000, 3200, 12800, 4),
	Standort3(3000, 150000, 3200, 16000, 5),
	Standort4(3500, 200000, 3200, 22400, 7),
	Standort5(0, 150000, 0, 0, 0);

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
