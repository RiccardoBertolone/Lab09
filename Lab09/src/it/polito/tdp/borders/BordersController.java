/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.GradoStato;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class BordersController {

	Model model;
	boolean flag = false;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader
	
	@FXML
    private ComboBox<Country> cbxStati;

	@FXML
    private Button btnTrovaVicini;
	

	@FXML
	void doCalcolaConfini(ActionEvent event) {

		txtResult.clear();
		String input = txtAnno.getText();
		int anno = 0;
		try {
			anno = Integer.parseInt(input);
		}
		catch (NumberFormatException e) {
			txtResult.setText("Errore nell'input");
			return;
		}
		if (anno<1816 || anno>2016) {
			txtResult.setText("Inserire un valore compreso tra 1816 e 2016");
			return;
		}

		List<GradoStato> gradiStati = model.calcolaConfini(anno);
		
		txtResult.setText("Elenco stati e loro numero di confini:\n");
		for(GradoStato g : gradiStati) {
			txtResult.appendText(g.getCountry()+":\t\t"+g.getGrado()+"\n");
		}
		
		txtResult.appendText("\nNumero di componenti connesse: "+model.getNumeroComponentiConnesse());
		flag = true;
	}
	
	@FXML
    void doTrovaTuttiIVicini(ActionEvent event) {
		txtResult.clear();
		if (flag == false) {
			txtResult.setText("Devi prima creare un grafo al punto precedente (#doCalcolaConfini()");
			return;
		}
		Country selezionato = cbxStati.getValue();
		if (selezionato == null) {
			txtResult.setText("Selezionare un paese");
		}
		
		long i1 = System.nanoTime();
		List<Country> vicini = model.trovaVicini(selezionato);
		long f1 = System.nanoTime();
		long i2 = System.nanoTime();
		Set<Country> viciniSet = model.trovaViciniRecursive(selezionato);
		long f2 = System.nanoTime();
		List<Country> viciniLista = new LinkedList<Country>(vicini);
		Collections.sort(viciniLista);
		txtResult.setText("Componente connessa di "+selezionato+":\n");
		for (Country c : viciniLista ) {
			txtResult.appendText(c.getStateNme()+"\n");
		}
		
		double tempo1 = (f1-i1)/(1000);
		double tempo2 = (f2-i2)/(1000);
		System.out.println(tempo1);
		System.out.println(tempo2);
    }


	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
		assert cbxStati != null : "fx:id=\"cbxStati\" was not injected: check your FXML file 'Borders.fxml'.";
	    assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Borders.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";
	}

	public void setModel(Model model) {
		this.model=model;
		cbxStati.getItems().addAll(model.getStati());

	}
}
