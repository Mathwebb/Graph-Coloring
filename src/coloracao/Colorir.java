package coloracao;

import java.util.ArrayList;

import grafos.Grafo;
import grafos.Vertice;

public class Colorir <type> {
	private Integer minCores;
	private Grafo<type> melhorColoracao;
	ArrayList<Double> freqs = new ArrayList<Double>();
	
	public Grafo<type> coloreGrafo(Grafo<type> grafo) {
		for (Double i = 0.0, t = 90.0; i < grafo.size(); i++) {
			freqs.add(t);
			t++;
		}
		minCores = grafo.size();
		
		for (Vertice<type> vertice : grafo.getListaVertices()) {
			Grafo<type> grafoCopia = grafo;
			vezes = 0;
			percorreGrafo(grafoCopia, vertice, 0);
		}
		return melhorColoracao;
	}
	
	private void percorreGrafo(Grafo<type> grafo, Vertice<type> vertice, Integer quantCores) {
		Integer quantVizinhos = vertice.getArestas().size();
		Grafo<type> grafoCopia = grafo;
		// Verifica se o vertice ja foi visitado para adicionar uma cor
		if (!vertice.getVisitado()) {
			vertice.setVisitado(true);
			Integer index = 0;
			// Confere as cores dos vizinhos para não adicionar uma igual
			for (Integer i = 0; i < vertice.getArestas().size(); i++) {
				if (vertice.getArestas().get(i).getFim().getFrequencia() == freqs.get(index)) {
					i = -1;
					index++;
				}
			}
			// Adiciona uma cor ao vertice
			vertice.setFrequencia(freqs.get(index));
			quantCores++;
		}
		// Verificando se todos os vértices foram visitados para sair da recursão
		for (Integer i = 0, t = 0; i < grafo.size(); i++) {
			if (grafo.getListaVertices().get(i).getVisitado()) t++;
			if (t == grafo.size()-1) {
				if(quantCores < minCores) {
					minCores = quantCores;
					melhorColoracao = grafo;
				}
				return;
			}
		}
		for (Integer i = 0; i < quantVizinhos; i++) {
			Vertice<type>proxVertice = vertice.getArestas().get(i).getFim();
			if (proxVertice.getVisitado() && i + 1 < quantVizinhos) continue;
			percorreGrafo(grafoCopia, proxVertice, quantCores);
		}
	}
}
