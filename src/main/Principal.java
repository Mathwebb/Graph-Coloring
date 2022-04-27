package main;

import grafos.Grafo;
import coloracao.Baseline;
import coloracao.Heuristica;

public class Principal {
	public static void main(String[] args) {
		Grafo<String> resultadoBaseline;
		Grafo<String> resultadoHeuristica;
		Baseline<String> baseline = new Baseline<String>();
		Heuristica<String> heuristica = new Heuristica<String>();
		Grafo<String> grafo = new Grafo<String>();

		grafo.addVertices("A");
		grafo.addVertices("B");
		grafo.addVertices("C");
		grafo.addVertices("D");
		grafo.addVertices("E");
		
		grafo.addAresta("A", "B");
		grafo.addAresta("A", "C");
		grafo.addAresta("A", "D");
		grafo.addAresta("A", "E");
		grafo.addAresta("B", "E");
		grafo.addAresta("C", "E");
		grafo.addAresta("C", "D");
		
		Grafo<String> grafoHeur = new Grafo<String>(grafo);
		
		resultadoHeuristica = heuristica.welshPowel(grafoHeur);
		
		resultadoBaseline = baseline.algoritmoExato(grafo);
		
		for (Integer i = 0; i < resultadoBaseline.size(); i++) {
			System.out.println(resultadoBaseline.get(i).get(0).getFrequencia());
		}
		
		System.out.println();
		
		for (Integer i = 0; i < resultadoHeuristica.size(); i++) {
			System.out.println(resultadoHeuristica.get(i).get(0).getFrequencia());
		}
	}
}
