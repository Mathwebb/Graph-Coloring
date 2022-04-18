package main;

import grafos.Grafo;
import coloracao.Baseline;

public class Principal {
	public static void main(String[] args) {
		Grafo<String> resultado;
		Baseline<String> baseline = new Baseline<String>();
		Grafo<String> grafo = new Grafo<String>();
		

		grafo.addVertices("A");
		grafo.addVertices("B");
		grafo.addVertices("C");
		grafo.addVertices("D");
		grafo.addVertices("E");
		grafo.addVertices("F");
		grafo.addVertices("G");

		grafo.addAresta("A", "B");
		grafo.addAresta("A", "C");
		grafo.addAresta("C", "D");
		grafo.addAresta("C", "E");
		grafo.addAresta("A", "F");
		grafo.addAresta("B", "G");
		
		resultado = baseline.algoritmoExato(grafo);
		
		for (Integer i = 0; i < resultado.size(); i++) {
			System.out.println(resultado.getElementosGrafo().get(i).get(0).getFrequencia());
		}
	}
}
