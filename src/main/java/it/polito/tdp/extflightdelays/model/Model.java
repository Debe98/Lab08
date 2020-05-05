package it.polito.tdp.extflightdelays.model;

import java.util.*;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.*;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {

	private Graph <Airport, DefaultWeightedEdge> grafo;
	private Map <Integer, Airport> areoportiIdMap = new HashMap <> ();
	
	public Graph creaGrafo(int dMin) throws Exception {
		grafo = new SimpleWeightedGraph <>(DefaultWeightedEdge.class);
		if (!creaNodi())
			throw new Exception("Errore nel caricamento degli aeroporti");
		if (!creaArchi(dMin))
			throw new Exception("Errore nel caricamento dei voli");
		return grafo;
	} 
	
	public boolean creaNodi() {
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		List <Airport> aeroportiLista = dao.loadAllAirports();
		if (aeroportiLista.size() == 0)
			return false;
		for (Airport a : aeroportiLista)
			areoportiIdMap.put(a.getId(), a);
		Graphs.addAllVertices(grafo, aeroportiLista);
		return true;
	}
	
	public boolean creaArchi(int dMin) {
		ExtFlightDelaysDAO dao = new ExtFlightDelaysDAO();
		List <Collegamento> collegamenti = dao.loadCollegamentiAndata();
		if (collegamenti.size() == 0)
			return false;
		Map <Collegamento, Collegamento> archi = new HashMap<>();
		for (Collegamento c : collegamenti)
			archi.put(c, c);
		collegamenti = dao.loadCollegamentiRitorno();
		if (collegamenti.size() == 0)
			return false;
		for (Collegamento c : collegamenti) {
			c.reverseToAndata();
			if (archi.containsKey(c)) {
				archi.get(c).mergeAndataRitorno(c);
			}
			else {
				archi.put(c, c);
			}
		}	
		int avgD;
		for (Collegamento c : archi.keySet()) {
			if ((avgD = c.getTotDistance()/c.getCnt()) > dMin)
				Graphs.addEdge(grafo, areoportiIdMap.get(c.getOriginAirportId()),
						areoportiIdMap.get(c.getDestinationAirportId()), avgD);
		}
		//System.out.println(grafo.getEdgeWeight(grafo.getEdge(areoportiIdMap.get(0), areoportiIdMap.get(20))));
		//System.out.println(archi.get(new Collegamento(0, 20, 1, 1)).getTotDistance());
		//System.out.println(archi.get(new Collegamento(0, 20, 1, 1)).getCnt());
		return true;
	}
	
}
