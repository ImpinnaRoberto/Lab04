package it.polito.tdp.lab04.model;

public class Studente {
	private String matricola;
	private String nome;
	private String cognome;
	public Studente(String matricola, String nome, String cognome) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
	}
	@Override
	public String toString() {
		return matricola + " " + nome + " " + cognome;
	}
	public String getMatricola() {
		return matricola;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	
	
}
