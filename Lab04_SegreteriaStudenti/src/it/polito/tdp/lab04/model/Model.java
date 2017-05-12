package it.polito.tdp.lab04.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.DAO.*;


public class Model {
	List<Corso> list;
	CorsoDAO corsodao=new CorsoDAO();
	StudenteDAO sdao=new StudenteDAO();
	
	public Model() {
		
		this.list=corsodao.getTuttiICorsi();
	
	}
	
	public Studente trovaStudente(String matricola){
		//QUESTA FUNZIONE ANDREBBE IN STUDENTEDAO
		String sql=
				"SELECT studente.* "+
				"FROM studente "+
				"WHERE studente.matricola=?";
		
	String jdbcURL="jdbc:mysql://localhost/segreteriastudenti?user=root&password=root";
	Studente stu=null;
	LinkedList<Studente> elenco= new LinkedList <Studente>();
	
	try {
		Connection conn = DriverManager.getConnection(jdbcURL);
		PreparedStatement st=conn.prepareStatement(sql);
		st.setString(1, matricola);
		ResultSet res=st.executeQuery();
		if(res.next()){
			stu=new Studente(res.getString("matricola"), res.getString("nome"), res.getString("cognome"));
			elenco.add(stu);
		}
		conn.close();
		return stu;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	}
		
	
	
	public List<Corso> getCorsi(){
		return list;
	}
	
	public boolean iscrivi(Studente s, String codiceCorso){
		
		return sdao.addStudente(s, codiceCorso);
	}
	
	public LinkedList<Corso> elencoCorsi(String matricola){
		/*LinkedList<String> prova=sdao.elencoCorsi(matricola);
		LinkedList<Corso> elenco= new LinkedList<Corso>();
		for(String s : prova){
			Corso c=corsodao.getCorso(s);
			elenco.add(c);
		}*/
		LinkedList<Corso> elenco=corsodao.getElencoCorsi(matricola);
		return elenco;
	}
	
	public LinkedList<Studente> elencoStudentiIscritti(String codice){
		//QUESTA FUNZIONE ANDREBBE IN COSRSODAO
		String sql=
				"SELECT studente.* "+
				"FROM corso, studente, corsistudenti "+
				"WHERE corsistudenti.codice=corso.codice AND corsistudenti.matricola=studente.matricola AND corso.codice=?";
		
	String jdbcURL="jdbc:mysql://localhost/segreteriastudenti?user=root&password=root";
	
	LinkedList<Studente> elenco= new LinkedList <Studente>();
	
	try {
		Connection conn = DriverManager.getConnection(jdbcURL);
		PreparedStatement st=conn.prepareStatement(sql);
		st.setString(1, codice);
		ResultSet res=st.executeQuery();
		while(res.next()){
			Studente stu=new Studente(res.getString("matricola"), res.getString("nome"), res.getString("cognome"));
			elenco.add(stu);
		}
		conn.close();
		return elenco;
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
	}
}
