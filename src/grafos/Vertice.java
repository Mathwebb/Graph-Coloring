package grafos;

import java.util.ArrayList;

public class Vertice<type> { 
	private type dado;
	private Double frequencia;
	private Boolean visitado;
	private ArrayList<Aresta<type>> arestas;
	
	public Vertice(type valor) {
		this.dado = valor;
		this.frequencia = 0.0;
		this.visitado = false;
		this.arestas = new ArrayList<Aresta<type>>();
	}
	
	public Vertice() {
		this.dado = null;
		this.frequencia = 0.0;
		this.visitado = false;
		this.arestas = new ArrayList<Aresta<type>>();
	}
	
//	public Vertice(Vertice<type> vertice) {
//		this.dado = vertice.dado;
//		this.frequencia = vertice.frequencia;
//		this.visitado = vertice.visitado;
//		this.arestas = new ArrayList<Aresta<type>>();
//		for (Integer i = 0; i < vertice.arestas.size(); i++) {
//			this.arestas.add(i, vertice.arestas.get(i));
//		}
//	}
	
	public Object clone() {
		Vertice<type> verticeCopia = new Vertice<type>();
		ArrayList<Aresta<type>> arestasCopia = new ArrayList<Aresta<type>>();
		verticeCopia.setDado(dado);
		verticeCopia.setFrequencia(frequencia);
		verticeCopia.setVisitado(visitado);
		verticeCopia.arestas = arestasCopia;
		for (Integer i = 0; i < arestas.size(); i++) {
			arestasCopia.add(i, arestas.get(i));
		}
		return verticeCopia;
	}

	public type getDado() {
		return dado;
	}

	public void setDado(type dado) {
		this.dado = dado;
	}
	
	public Double getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Double frequencia) {
		this.frequencia = frequencia;
	}

	public Boolean getVisitado() {
		return visitado;
	}

	public void setVisitado(Boolean visitado) {
		this.visitado = visitado;
	}

	public ArrayList<Aresta<type>> getArestas() {
		return arestas;
	}

	public void adicionarAresta(Aresta<type> aresta) {
		this.arestas.add(aresta);
	}
}
