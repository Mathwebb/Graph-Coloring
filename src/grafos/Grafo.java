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
			Vertice<type> antigoVertice = elementosGrafo.get(i).get(0);
			Vertice<type> novoVertice = new Vertice<type>(antigoVertice.getDado(), antigoVertice.getFrequencia(), antigoVertice.getVisitado());
			novoArrayVertices.add(novoVertice);
			this.grafo.add(novoArrayVertices);
		}
		for (Integer i = 0; i < grafo.size(); i++) {
			for (Integer j = 1; j < grafo.getElementosGrafo().get(i).size(); j++) {
				Vertice<type> antigoVizinho = grafo.getElementosGrafo().get(i).get(j);
				Vertice<type> novoVizinho = this.getVerticeV(antigoVizinho.getDado());
				this.grafo.get(i).add(novoVizinho);
			}
		}
	}
	
	public ArrayList<ArrayList<Vertice<type>>> getElementosGrafo() {
		return grafo;
	}
	
	public Integer size() {
		return grafo.size();
	}
	
	public ArrayList<Vertice<type>> getVertices() {
		ArrayList<Vertice<type>> vertices = new ArrayList<Vertice<type>>();
		for (Integer i = 0; i < grafo.size(); i++) {
			vertices.add(grafo.get(i).get(0));
		}
		return vertices;
	}
	
	public Integer getVertice(type dado) {
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(dado)) {
				return i;
			}
		}
		return -1;
	}
	
	public Vertice<type> getVerticeV(type dado) {
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(dado)) {
				return grafo.get(i).get(0);
			}
		}
		return null;
	}
	
	public void addVertices(type dadoVert) {
		Vertice<type> vertice;
		Integer indexVert = getVertice(dadoVert);
		if (indexVert == -1) {
			vertice = new Vertice<type>(dadoVert);
		} else {
			vertice = grafo.get(indexVert).get(0);
		}
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
	
	public Boolean buscaAresta(Grafo<type> grafo, Vertice<type> VertInicio, Vertice<type> dadoVertFim) {
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.getElementosGrafo().get(i).get(0).getDado().equals(dadoVertFim)) {
				for (Integer j = 1; j < grafo.getElementosGrafo().get(i).size(); j++) {
					if (grafo.getElementosGrafo().get(i).get(j).getDado().equals(dadoVertFim)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void addAresta(type dadoVertInicio, type dadoVertFim) {
		Vertice<type> verticeInicio = new Vertice<type>(dadoVertInicio);
		Vertice<type> verticeFim = new Vertice<type>(dadoVertFim);
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(verticeInicio.getDado())) {
				grafo.get(i).add(grafo.get(getVertice(dadoVertFim)).get(0));
				break;
			}
		}
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(verticeFim.getDado())) {
				grafo.get(i).add(grafo.get(getVertice(dadoVertInicio)).get(0));
				break;
			}
		}
	}
	
	public void addAresta(Vertice<type> VertInicio, Vertice<type> VertFim) {
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(VertInicio.getDado())) {
				grafo.get(i).add(grafo.get(getVertice(VertFim.getDado())).get(0));
				break;
			}
		}
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(VertFim.getDado())) {
				grafo.get(i).add(grafo.get(getVertice(VertInicio.getDado())).get(0));
				break;
			}
		}
	}
}
