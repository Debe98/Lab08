package it.polito.tdp.extflightdelays.model;

/**
 * 
 * @author lucad
 * @deprecated <h1>Deprecata!!</h1>
 * @category
 * Questa classe doveva servire come chiave per la mappa di archi, ma me la sono cavata
 * dando i parametri qui sotto come valore di hash direttaente a collegamento. 
 *
 */
public class Tratta {
	private String partenza;
	private String destinazione;
	
	
	public Tratta(String partenza, String destinazione) {
		super();
		this.partenza = partenza;
		this.destinazione = destinazione;
	}
	
	
	

}
