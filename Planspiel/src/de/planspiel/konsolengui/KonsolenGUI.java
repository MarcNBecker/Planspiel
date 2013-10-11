package de.planspiel.konsolengui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import de.planspiel.spiel.Spiel;

public abstract class KonsolenGUI {
	
	protected Spiel spiel;
	protected BufferedReader reader;
	
	public KonsolenGUI() {
		spiel = Spiel.holeSpiel();
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public abstract void run();
}
