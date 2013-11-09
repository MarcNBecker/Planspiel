package de.planspiel.ereignis;

/**
 * Interface f�r Ereignisse
 * @author Marc Becker
 */
public interface Ereignis {
	
	/**
	 * Diese Methode kann vom Spiel f�r jedes Ereignis aufgerufen werden, um
	 * den Effekt des Ereignisses durchzuf�hren
	 */
	public void starten();
	
}
