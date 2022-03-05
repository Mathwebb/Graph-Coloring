package grafos;

import java.util.ArrayList;

public class Vertice<type> { 
	private type dado;
	private ArrayList<Aresta<type>> arestasEntrada;
	private ArrayList<Aresta<type>> arestasSaida;
	
	public Vertice(type valor) {
		this.dado = valor;
		this.arestasEntrada = new ArrayList<Aresta<type>>();
		this.arestasSaida = new ArrayList<Aresta<type>>();
	}

	public type getDado() {
		return dado;
	}

	public void setDado(type dado) {
		this.dado = dado;
	}
	
	public void adicionarArestaEntrada(Aresta<type> arestaEntrada) {
		this.arestasEntrada.add(arestaEntrada);
	}
	
	public void adicionarArestaSaida(Aresta<type> arestaSaida) {
		this.arestasSaida.add(arestaSaida);
	}
}
