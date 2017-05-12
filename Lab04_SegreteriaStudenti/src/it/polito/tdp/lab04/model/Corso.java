package it.polito.tdp.lab04.model;

public class Corso {

	private String codice;
	private String nome;
	
	public Corso(String codice, String nome) {
		
		this.codice = codice;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return codice + " " + nome;
	}

	public String getCodice() {
		return codice;
	}

	public String getNome() {
		return nome;
	}
	
	
	
}
