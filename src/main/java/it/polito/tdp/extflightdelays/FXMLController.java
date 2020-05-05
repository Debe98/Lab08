/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Airport;
import it.polito.tdp.extflightdelays.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="distanzaMinima"
    private TextField distanzaMinima; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizza"
    private Button btnAnalizza; // Value injected by FXMLLoader

    @FXML
    void doAnalizzaAeroporti(ActionEvent event) {
    	int dMin;
    	try {
    		String raw = distanzaMinima.getText();
    		if (raw.contentEquals("")) raw = "0";
    		dMin = Integer.parseInt(raw);
    	} catch (Exception e) {
    		txtResult.setText("Inserisci un numero valido!");
    		return;
		}
    	try {
    		Graph <Airport, DefaultWeightedEdge> grafo = model.creaGrafo(dMin);
    		String ritorno = String.format("Creato grafo con %d aeroporti e %d tratte:\n\n", grafo.vertexSet().size(), grafo.edgeSet().size());
    		for (DefaultWeightedEdge e : grafo.edgeSet()) {
    			ritorno += String.format("Partenza: %20s\nArrivo: %20s\nDistanza media: (%.0f)\n\n", grafo.getEdgeSource(e).getAirportName(), 
    					grafo.getEdgeTarget(e).getAirportName(), grafo.getEdgeWeight(e));
    		}
    		txtResult.setText(ritorno.substring(0, ritorno.length()-2));
    	} catch (Exception e) {
			txtResult.setText(e.getMessage());
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert distanzaMinima != null : "fx:id=\"distanzaMinima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizza != null : "fx:id=\"btnAnalizza\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
