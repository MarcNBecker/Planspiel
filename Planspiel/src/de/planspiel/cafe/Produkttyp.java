package de.planspiel.cafe;

/**
 * 
 * Verschiedene Produkttypen, die ein Händler anbieten kann
 * 
 * @author Marc Becker
 * 
 */
public enum Produkttyp {
	KAFFEE(0.15, 6), // TODO Sinnvollen Start-Werte setzen
	TEE(0.15, 6), // TODO Sinnvollen Start-Werte setzen
	KUCHEN(0.20, 5); // TODO Sinnvollen Start-Werte setzen

	private final double maxEK;
	private final double maxVK;

	private Produkttyp(double maxEK, double maxVK) {
		this.maxEK = maxEK;
		this.maxVK = maxVK;
	}

	public double holeMaxEK() {
		return maxEK;
	}

	public double holeMaxVK() {
		return maxVK;
	}
}
