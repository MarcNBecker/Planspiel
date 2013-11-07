package de.planspiel.cafe;

import java.util.Vector;

public class VerkaufsListe extends ProduktVerwalter {
	private Unternehmenskette kette;

	/**
	 * Erzeugt eine neue VerkaufsListe, welche die Anzahl der verkauften
	 * Produkte angibt
	 * 
	 * @param kette
	 */
	public VerkaufsListe(Unternehmenskette kette) {
		super();
		this.kette = kette;
		uebertragenProdukte(kette.holeLager());
	}

	/**
	 * �bertr�gt die Produkte des Lagers in die Verkaufsliste, �bernimmt
	 * Qualit�t, EKPreis und Preis, setzt Menge aber auf 0
	 * 
	 * @param lager
	 *            Lager, dessen Produkte �bertragen werden
	 */
	private void uebertragenProdukte(Lager lager) {
		Vector<Produkt> lagerProdukte = lager.holeProduktliste();
		for (int i = 0; i < lagerProdukte.size(); i++) {
			Produkt lagerProdukt = lagerProdukte.get(i);
			Produkt verkaufsProdukt = new Produkt(lagerProdukt.holeName(), lagerProdukt.holeQualitaet(), lagerProdukt.holeEkpreis());
			verkaufsProdukt.setzePreis(lagerProdukt.holePreis());
			verkaufsProdukt.setzeMenge(0);
			holeProduktliste().add(verkaufsProdukt);
		}
	}

	/**
	 * Erh�ht die gespeicherte Verkaufsmenge eines Produkts um die angegebene
	 * Menge
	 * 
	 * @param name
	 *            Produkttyp, um welches Produkt es sich handelt
	 * @param menge
	 *            Menge, um die die Verkaufsmenge erh�ht wird
	 */
	public void erhoehenVerkaufsmenge(Produkttyp name, int menge) {
		Produkt verkauftesProdukt = suchenProdukt(name);
		if (verkauftesProdukt != null) {
			verkauftesProdukt.setzeMenge(verkauftesProdukt.holeMenge() + menge);
		}
	}

	/**
	 * @return Gibt die Kette des Lagers zur�ck
	 */
	public Unternehmenskette holeKette() {
		return kette;
	}

}
