package de.planspiel.cafe;

/**
 * Definition eines Standorts
 * 
 * @author Marc Becker
 */

public enum Standorttyp {
	Standort1(5000, 1000, 100, 200, 5), // Test-Standort
	Standort2(2000, 100000, 3200, 12800, 4), Standort3(3000, 150000, 3200, 16000, 5), Standort4(3500, 200000, 3200, 22400, 7), Standort5(0, 150000, 0, 0, 0);

	private double laufendeFilialkosten;
	private double startFilialkosten;
	private int minKunden;
	private int maxKunden;
	private int maxMitarbeiter;

	/**
	 * Erzeugt einen neuen Standorttypen und setzt die laufenden Filialkosten,
	 * die Start-Filialkosten, die Mindestanzahl an Kunden, die maximale Anzahl
	 * an Kunden und die maximale Anzahl an Mitarbeitern, die sich lohnt
	 * einzustellen
	 * 
	 * @param laufendeFilialkosten
	 *            laufende Filialkosten pro Runde
	 * @param startFilialkosten
	 *            Start-Filialkosten für die Eröffnung an diesem Standorttypen
	 * @param minKunden
	 *            Kunden, die von einen Mitarbeiter bedient werden können
	 * @param maxKunden
	 *            Kunden, die von maxMitarbeiter Mitarbeiter bedient werden
	 *            können
	 * @param maxMitarbeiter
	 *            Mitarbeiteranzahl, ab der sich zusätzliche Mitarbeiter nicht
	 *            mehr lohnen
	 */
	private Standorttyp(double laufendeFilialkosten, double startFilialkosten, int minKunden, int maxKunden, int maxMitarbeiter) {
		this.laufendeFilialkosten = laufendeFilialkosten;
		this.startFilialkosten = startFilialkosten;
		this.minKunden = minKunden;
		this.maxKunden = maxKunden;
		this.maxMitarbeiter = maxMitarbeiter;
	}

	/**
	 * 
	 * @return Liefert die laufenden Filialkosten an diesem Standorttypen zurück
	 */
	public double holeLaufendeFilialkosten() {
		return laufendeFilialkosten;
	}

	/**
	 * 
	 * @return Liefert die Start-Filialkosten an diesem Standorttypen zurück
	 */
	public double holeStartFilialkosten() {
		return startFilialkosten;
	}

	/**
	 * 
	 * @return Liefert Kunden, die von einen Mitarbeiter bedient werden können,
	 *         zurück
	 */
	public int holeMinKunden() {
		return minKunden;
	}

	/**
	 * 
	 * @return Liefert Kunden, die von maxMitarbeiter Mitarbeiter bedient werden
	 *         können, zurück
	 */
	public int holeMaxKunden() {
		return maxKunden;
	}

	/**
	 * 
	 * @return Liefert Mitarbeiteranzahl, ab der sich zusätzliche Mitarbeiter nicht
	 *            mehr lohnen zurück
	 */
	public int holeMaxMitarbeiter() {
		return maxMitarbeiter;
	}
}
