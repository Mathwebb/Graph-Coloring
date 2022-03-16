package main;

import coloracao.Colorir;
import grafos.Grafo;

public class Principal {

	public static void main(String[] args) {
		Grafo<String> grafo = new Grafo<String>();
		grafo.addVertices("A");
		grafo.addVertices("B");
		grafo.addVertices("C");
		grafo.addVertices("D");
		grafo.addVertices("E");
		grafo.addVertices("F");
		
		grafo.addAresta("A", "B");
		grafo.addAresta("A", "C");
		grafo.addAresta("B", "C");
		grafo.addAresta("A", "D");
		grafo.addAresta("B", "D");
		grafo.addAresta("C", "D");
		
		grafo.addAresta("A", "E");
		grafo.addAresta("B", "E");
		grafo.addAresta("C", "E");
		grafo.addAresta("D", "E");
		
		grafo.addAresta("A", "F");
		grafo.addAresta("B", "F");
		grafo.addAresta("C", "F");
		grafo.addAresta("D", "F");
		grafo.addAresta("A", "F");
		
		Grafo<Integer> grafoInt = new Grafo<Integer>();
		grafoInt.addVertices(1);
		grafoInt.addVertices(2);
		grafoInt.addVertices(3);
		
		grafoInt.addAresta(1, 2);
		grafoInt.addAresta(1, 3);
		grafoInt.addAresta(2, 3);
		
		Colorir<String> colorindo = new Colorir<String>();
		Grafo<String> grafoRes = colorindo.coloreGrafo(grafo);
		for (Integer i = 0; i < grafoRes.getVertices().size(); i++) {
            System.out.print("Vertice: " + grafoRes.getVertices().get(i).getDado());
            System.out.println(" - Frequência: " + grafoRes.getVertices().get(i).getFrequencia());
        }
		
		Colorir<Integer> colorindo2 = new Colorir<Integer>();
		Grafo<Integer> grafoRes2 = colorindo2.coloreGrafo(grafoInt);
		for (Integer i = 0; i < grafoRes2.getVertices().size(); i++) {
            System.out.print("Vertice: " + grafoRes2.getVertices().get(i).getDado());
            System.out.println(" - Frequência: " + grafoRes2.getVertices().get(i).getFrequencia());
        }
	}

}
