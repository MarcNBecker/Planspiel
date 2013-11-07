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
	TEE(0.15, 6),
	KUCHEN(0.20, 5);

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
