package it.polito.tdp.lab04.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class SegreteriaStudentiController {

	private Model model;
	List<Corso> corsi = new LinkedList<Corso>();

	@FXML
	private ComboBox<Corso> comboCorso;

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnCercaNome;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnReset;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	public void setModel(Model model) {
		this.model = model ;
		comboCorso.getItems().addAll(this.model.getCorsi()) ;
	}

	@FXML
	void doReset(ActionEvent event) {
		txtResult.clear();
	}

	@FXML
	void doCercaNome(ActionEvent event) {
		String matricola=txtMatricola.getText();
		Studente s=model.trovaStudente(matricola);
		if(s!=null){
			txtNome.setText(s.getNome());
			txtCognome.setText(s.getCognome());
		}
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		
		if(comboCorso.getValue()!=null){
			String codice=comboCorso.getValue().getCodice();
			LinkedList<Studente> elenco=model.elencoStudentiIscritti(codice);
			if(elenco.size()>0)
				txtResult.appendText(elenco.toString()+"\n");
			else
				txtResult.appendText("Nessun iscritto alla materia");
		}else{
			txtResult.appendText("Materia non selezionata!\n");
		}
	}

	@FXML
	void doCercaCorsi(ActionEvent event) {
		String matricola=txtMatricola.getText();
    	String nome=txtNome.getText();
    	String cognome=txtCognome.getText();
    	if(matricola.length()==6){
    		List<Corso> elencoCorsi=model.elencoCorsi(matricola);
    		
    		if(elencoCorsi.size()==0){
    			txtResult.appendText("Lo studente non è stato ancora iscritto ad alcun corso\n");
    		}else{
    			txtResult.appendText(elencoCorsi.toString()+"\n");
    		}
    	}else{
    		txtResult.appendText("Matricola troppo corta\n");
    	}
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		String matricola=txtMatricola.getText();
    	String nome=txtNome.getText();
    	String cognome=txtCognome.getText();
    	Corso corso=comboCorso.getValue();
    	
    	if(matricola.length()<6 || nome.length()==0 || cognome.length()==0){
    		txtResult.appendText("Dati esame insufficienti\n");
    		return;
    	}
    	
    	if(corso!=null){
    	Studente s=new Studente(matricola, nome, cognome);
    	boolean result=model.iscrivi(s, corso.getCodice());
    	
    	if(result)
    		txtResult.appendText("Studente iscritto correttamente\n");
    	else
    		txtResult.appendText("Studente NON ISCRITTO (matricola duplicata)\n");
    	}else{
    		txtResult.appendText("Nessun esame selezionato!\n");
    	}
	}

	
	
	@FXML
	void initialize() {
		assert comboCorso != null : "fx:id=\"comboCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
	}

}
