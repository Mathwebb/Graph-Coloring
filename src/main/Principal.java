package main;

import coloracao.Colorir;
import grafos.Grafo;

public class Principal {

	public static void main(String[] args) {
		Grafo<String> grafo = new Grafo<String>();
		grafo.addVertices("A");
		grafo.addVertices("B");
		grafo.addVertices("C");
		grafo.addArestas("A", "B");
		grafo.addArestas("A", "C");
		grafo.addArestas("B", "C");
		
//		for (Integer i = 0; i < grafo.getVertices().size(); i++) {
//			System.out.println("Vertice: " + grafo.getVertices().get(i).getDado());
//		}
//		for (Integer i = 0; i < grafo.getArestas().size(); i = i + 2) {
//			System.out.println("Aresta: " + grafo.getArestas().get(i).getDado() + "," + grafo.getArestas().get(i+1).getDado());
//		}
		
		Colorir<String> colorindo = new Colorir<String>();
		Grafo<String> grafoRes = colorindo.coloreGrafo(grafo);
		for (Integer i = 0; i < grafoRes.getVertices().size(); i++) {
            System.out.print("Vertice: " + grafoRes.getVertices().get(i).getDado());
            System.out.println(" - Frequência: " + grafoRes.getVertices().get(i).getFrequencia());
        }
		for (Integer i = 0; i < grafoRes.size(); i++) {
			System.out.println(grafoRes.getElementosGrafo().get(i));
		}
	}

}
