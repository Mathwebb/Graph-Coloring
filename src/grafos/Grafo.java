package grafos;

import java.util.ArrayList;

public class Grafo<type>{
	private ArrayList<ArrayList<Vertice<type>>> grafo;
	
	public Grafo() {
		grafo = new ArrayList<ArrayList<Vertice<type>>>();
	}
	
	public Grafo(Grafo<type> grafo) {
		ArrayList<ArrayList<Vertice<type>>> elementosGrafo = grafo.getElementosGrafo();
		this.grafo = new ArrayList<ArrayList<Vertice<type>>>();
		for (Integer i = 0; i < elementosGrafo.size(); i++) {
			ArrayList<Vertice<type>> novoArrayVertices = new ArrayList<Vertice<type>>();
			for (Integer j = 0; j < elementosGrafo.get(i).size(); j++) {
				Vertice<type> antigoVertice = elementosGrafo.get(i).get(j);
				Vertice<type> novoVertice = new Vertice<type>(antigoVertice.getDado());
				novoArrayVertices.add(novoVertice);
			}
		this.grafo.add(novoArrayVertices);
		}
	}
	
	public ArrayList<ArrayList<Vertice<type>>> getElementosGrafo() {
		return grafo;
	}
	
	public ArrayList<Vertice<type>> getVertices() {
		ArrayList<Vertice<type>> vertices = new ArrayList<Vertice<type>>();
		for (Integer i = 0; i < grafo.size(); i++) {
			vertices.add(grafo.get(i).get(0));
		}
		return vertices;
	}
	
	public void addVertices(type dadoVert) {
		Vertice<type> vertice = new Vertice<type>(dadoVert);
		ArrayList<Vertice<type>> listaVertice = new ArrayList<Vertice<type>>();
		listaVertice.add(vertice);
		grafo.add(listaVertice);
	}
	
	public ArrayList<Vertice<type>> getArestas(){
		ArrayList<Vertice<type>> arestas = new ArrayList<Vertice<type>>();
		Vertice<type> verticeInicial;
		for (Integer i = 0; i < grafo.size(); i++) {
			verticeInicial = grafo.get(i).get(0);
			for (Integer j = 1; j < grafo.get(i).size(); j++) {
				arestas.add(verticeInicial);
				arestas.add(grafo.get(i).get(j));
			}
		}
		return arestas;
	}
	
	public void addArestas(type dadoVertInicio, type dadoVertFim) {
		Vertice<type> verticeInicio = new Vertice<type>(dadoVertInicio);
		Vertice<type> verticeFim = new Vertice<type>(dadoVertFim);
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(verticeInicio.getDado())) {
				grafo.get(i).add(verticeFim);
				break;
			}
		}
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(verticeFim.getDado())) {
				grafo.get(i).add(verticeInicio);
				break;
			}
		}
	}
}
