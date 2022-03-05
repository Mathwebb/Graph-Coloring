package grafos;

import java.util.ArrayList;

public class Grafo<type> {
	public ArrayList<Vertice<type>> vertices;
	public ArrayList<Aresta<type>> arestas;
	
	public Grafo() {
		this.vertices = new ArrayList<Vertice<type>>();
		this.arestas = new ArrayList<Aresta<type>>();
	}
	
	public void adicionarVertice(type dado) {
		Vertice<type> novoVertice = new Vertice<type>(dado);
		vertices.add(novoVertice);
	}
	
	public void adicionarAresta(type dadoVert_1, type dadoVert_2) {
		Vertice<type> inicio = this.getVertice(dadoVert_1);
		Vertice<type> fim = this.getVertice(dadoVert_2);
		Aresta<type> aresta1 = new Aresta<type>(inicio, fim);
		Aresta<type> aresta2 = new Aresta<type>(fim, inicio);
		inicio.adicionarArestaSaida(aresta1);
		inicio.adicionarArestaEntrada(aresta1);
		fim.adicionarArestaEntrada(aresta1);
		fim.adicionarArestaSaida(aresta2);
		this.arestas.add(aresta1);
	}
	
	public Vertice<type> getVertice(type dado) {
		Vertice<type> vertice = null;
		for (int i = 0; i < this.vertices.size(); i++) {
			if (dado.equals(vertices.get(i).getDado())) {
				vertice = vertices.get(i);
				break;
			}
		}
		if (vertice != null) {
			return vertice;
		}else {
			return null;
		}
	}
}
