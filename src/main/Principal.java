package main;

import grafos.Grafo;

public class Principal {

	public static void main(String[] args) {
		Grafo<String> grafo = new Grafo<String>();
		grafo.adicionarVertice("Jose");
		grafo.adicionarVertice("Henrique");
		grafo.adicionarAresta("Jose", "Henrique");
		System.out.println(grafo.getVertice("Jose").getArestasEntrada().get(0).getFim().getDado());
	}

}
