package de.planspiel.cafe;

/**
 * 
 * Verschiedene Produkttypen, die ein Händler anbieten kann
 * 
 * @author Marc Becker
 * 
 */
public enum Produkttyp {
	KAFFEE(0.15, 6), //
	TEE(0.15, 6), KUCHEN(0.20, 5);

	private final double maxEK;
	private final double maxVK;

	/**
	 * Erzeugt einen neuen Produkttypen mit einem maximalen Einkaufspreis und
	 * einem maximalen Verkaufspreis.
	 * 
	 * @param maxEK
	 * 			maximaler Einkaufspreis
	 * @param maxVK
	 * 			maximaler Verkaufspreis
	 */
	private Produkttyp(double maxEK, double maxVK) {
		this.maxEK = maxEK;
		this.maxVK = maxVK;
	}

	/**
	 * 
	 * @return maximaler Einkaufspreis des Produkttypen
	 */
	public double holeMaxEK() {
		return maxEK;
	}

	/**
	 * 
	 * @return maximaler Verkaufspreis des Produkttypen
	 */
	public double holeMaxVK() {
		return maxVK;
	}
}
