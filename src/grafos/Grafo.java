package grafos;

import java.util.ArrayList;

public class Grafo<type> implements Cloneable {
	public ArrayList<Vertice<type>> vertices;
	public ArrayList<Aresta<type>> arestas;
	
	public Grafo() {
		this.vertices = new ArrayList<Vertice<type>>();
		this.arestas = new ArrayList<Aresta<type>>();
	}
	
	public Grafo(ArrayList<Vertice<type>> vertices, ArrayList<Aresta<type>> arestas) {
		this.vertices = vertices;
		this.arestas = arestas;
	}
	
//	// Contrutor para permitir a clonagem de um grafo
//	public Grafo(Grafo<type> grafo) {
//		this.vertices = new ArrayList<Vertice<type>>();
//		for (Integer i = 0; i < grafo.vertices.size(); i++) {
//			Vertice<type> vertice = new Vertice<type>(grafo.getListaVertices().get(i));
//			this.vertices.add(i, vertice);
//		}
//		this.arestas = new ArrayList<Aresta<type>>();
//		for (Integer i = 0; i < grafo.arestas.size(); i++) {
//			Aresta<type> aresta = new Aresta<type>(grafo.arestas.get(i).getInicio(),grafo.arestas.get(i).getFim());
//			this.arestas.add(i, aresta);
//		}
//	}
	
	public Object clone() {
        // deep copy
        ArrayList<Vertice<type>> verticesCopia = new ArrayList<Vertice<type>>();
        ArrayList<Aresta<type>> arestasCopia = new ArrayList<Aresta<type>>();
        for(int i = 0 ; i < vertices.size() ; i++) {
            Vertice<type> novoVertice = (Vertice<type>) vertices.get(i).clone();
            verticesCopia.add(novoVertice);
        }
        for(int i = 0 ; i < arestas.size() ; i++) {
            Aresta<type> aresta = new Aresta<type>(arestas.get(i).getInicio(),arestas.get(i).getFim());
            arestasCopia.add(aresta);
        }
        Grafo<type> copia = new Grafo<type>(verticesCopia, arestasCopia);
        return copia;
    }
	
	public void adicionarVertice(type dado) {
		Vertice<type> novoVertice = new Vertice<type>(dado);
		vertices.add(novoVertice);
	}
	
	public void adicionarAresta(type dadoVert_1, type dadoVert_2) {
		Vertice<type> inicio = this.getVertice(dadoVert_1);
		Vertice<type> fim = this.getVertice(dadoVert_2);
		Aresta<type> arestaInicio = new Aresta<type>(inicio, fim);
		Aresta<type> arestaFim = new Aresta<type>(fim, inicio);
		inicio.adicionarAresta(arestaInicio);
		fim.adicionarAresta(arestaFim);
		this.arestas.add(arestaInicio);
		this.arestas.add(arestaFim);
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
	
	public Integer size() {
		return vertices.size();
	}
	
	public ArrayList<Vertice<type>> getListaVertices(){
		return vertices;
	}
}
