package de.planspiel.test;

public abstract class SiegbedingungsTest {
	
	private String siegbedingung;
	private String siegerfuellung;
	
	public void setzeSiegbedingung(String siegbedingung) {
		this.siegbedingung = siegbedingung;
	}
	
	public String holeSiegbedingung() {
		return siegbedingung;
	}
	
	public void setzeSiegerfuellung(String siegerfuellung) {
		this.siegerfuellung = siegerfuellung;
	}
	
	public String holeSiegerfuellung() {
		return siegerfuellung;
	}
}
