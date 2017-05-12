package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;


public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();
			List<Corso> list = new ArrayList<Corso>() ;
			while (rs.next()) {
				Corso c= new Corso(rs.getString("codice"), rs.getString("nome"));
				list.add(c);
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo Corso alla lista
			}
			conn.close();
			return list;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String corso) {
		return null;
	}
		
	public LinkedList<Corso> getElencoCorsi(String matricola){
		String sql=
				"SELECT corso.codice, corso.nome "+
				"FROM corso, studente, corsistudenti "+
				"WHERE corsistudenti.codice=corso.codice AND corsistudenti.matricola=studente.matricola AND studente.matricola=?";
		
	String jdbcURL="jdbc:mysql://localhost/segreteriastudenti?user=root&password=root";
	
	LinkedList<Corso> elenco= new LinkedList <Corso>();
	
	try {
		Connection conn = DriverManager.getConnection(jdbcURL);
		PreparedStatement st=conn.prepareStatement(sql);
		st.setString(1, matricola);
		ResultSet res=st.executeQuery();
		while(res.next()){
			Corso cor= new Corso(res.getString("codice"),res.getString("nome"));
			elenco.add(cor);
		}
		conn.close();
		return elenco;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
		
	}


	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		return false;
	}
}
