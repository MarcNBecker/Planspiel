package de.planspiel.test;

/**
 * Diese Klasse dient zur Verwaltung von Siegbedingungen und Siegerf�llungen Ein
 * JUnit Test der die KonsolenGUI Interpreter Klassen benutzt sollte von dieser
 * Klasse erben. Dann kann die Siegbedingung in der JUnit Input Test Datei durch
 * den Befehl SIEGBEDINGUNG spielerName w�hrend der Spieler hinzuf�gen Phase
 * definiert werden. Die Siegerf�llung wird vom Interpreter gesetzt, welcher das
 * Spiel beendet. Der JUnit Test sollte letztendlich Siegbedingung und
 * Siegerf�llung vergleichen
 * 
 * @author Marc Becker
 */
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
