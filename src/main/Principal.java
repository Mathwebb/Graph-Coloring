package main;

import coloracao.Colorir;
import grafos.Grafo;
import grafos.Vertice;

public class Principal {

	public static void main(String[] args) throws CloneNotSupportedException {
		Grafo<String> grafo = new Grafo<String>();
		grafo.adicionarVertice("A");
		grafo.adicionarVertice("B");
		grafo.adicionarVertice("C");
		grafo.adicionarAresta("A", "B");
		grafo.adicionarAresta("C", "A");
		grafo.adicionarAresta("B", "C");
		
		Grafo<String> grafoCopia = (Grafo<String>) grafo.clone();
		
		grafoCopia.adicionarVertice("G");
		System.out.println(grafo.vertices.get(0).getArestas());
		System.out.println(grafoCopia.vertices.get(0).getArestas());

		
		Colorir<String> colorindo = new Colorir<String>();
		Grafo<String> grafoColorido = colorindo.coloreGrafo(grafo);
		for (Vertice<String> vertice : grafoCopia.getListaVertices()) {
			System.out.println("Vertice: " + vertice.getDado() + "  Freq: " + vertice.getFrequencia());
		}
	}

}
