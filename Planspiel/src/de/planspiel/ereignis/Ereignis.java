package de.planspiel.ereignis;

/**
 * Interface für Ereignisse
 * @author Marc Becker
 */
public interface Ereignis {
	
	/**
	 * Diese Methode kann vom Spiel für jedes Ereignis aufgerufen werden, um
	 * den Effekt des Ereignisses durchzuführen
	 */
	public void starten();
	
}
