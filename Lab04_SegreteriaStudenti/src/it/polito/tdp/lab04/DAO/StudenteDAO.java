package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.*;

public class StudenteDAO {
	
	public StudenteDAO(){
		
	}

	public boolean addStudente(Studente s, String codiceCorso){
		
		String prova=
				"SELECT corso.codice "+
				"FROM corso, studente, corsistudenti "+
				"WHERE corsistudenti.codice=corso.codice AND corsistudenti.matricola=studente.matricola AND  studente.matricola=?";
		
	String jdbcURL="jdbc:mysql://localhost/segreteriastudenti?user=root&password=root";
	LinkedList<String> codiciPresenti= new LinkedList<String>();
	try {
		Connection conn = DriverManager.getConnection(jdbcURL);
		PreparedStatement st=conn.prepareStatement(prova);
		st.setString(1, s.getMatricola());
		ResultSet res=st.executeQuery();
		while(res.next()){
			
			codiciPresenti.add(res.getString("codice"));
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
		
	}
		
		boolean flag=false;
		for(String stringa: codiciPresenti){
			if(stringa.equals(codiceCorso))
				flag=true;
		}
		
		
		
		
		
		
		if(flag!=true){
		
		String sql="INSERT INTO `segreteriastudenti`.`studente` (`matricola`, `nome`, `cognome`) VALUES (?, ?, ?)";
		String sql2="INSERT INTO `segreteriastudenti`.`corsistudenti` (`matricola`, `codice`) VALUES (?, ?)";

		

		try {
			Connection conn=DriverManager.getConnection(jdbcURL);
			
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, s.getMatricola());
			st.setString(2, s.getNome());
			st.setString(3, s.getCognome());
			
			int result=st.executeUpdate();
			
			PreparedStatement st2=conn.prepareStatement(sql2);
			st2.setString(1, s.getMatricola());
			st2.setString(2, codiceCorso);
			
			int result2=st2.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e1) {
			try{
				Connection conn=DriverManager.getConnection(jdbcURL);
				PreparedStatement st2=conn.prepareStatement(sql2);
				st2.setString(1, s.getMatricola());
				st2.setString(2, codiceCorso);
				
				int result2=st2.executeUpdate();
				return true;
				
			}catch (SQLException e) {
				e1.printStackTrace();
			}
		
		}
			return false;
		
		}
	else
		return false;
	}
	
}
	


