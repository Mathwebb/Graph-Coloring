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
		
		for (Integer i = 0; i < grafo.size(); i++) {
			Grafo<type> grafoCopia = new Grafo<type>(grafo);
			percorreGrafo(grafoCopia, grafo.getElementosGrafo().get(i), i, 0);
		}
		return melhorColoracao;
	}
	
	private void percorreGrafo(Grafo<type> grafo, ArrayList<Vertice<type>> vertice, Integer indexVert, Integer quantCores) {
		Integer quantVizinhos = vertice.size()-1;
		Grafo<type> grafoCopia = grafo;
		// Verifica se o vertice ja foi visitado para adicionar uma cor
		if (vertice.get(0).getVisitado() == 0) {
			vertice.get(0).setVisitado(1);
			Integer index = 0;
			// Confere as cores dos vizinhos para não adicionar uma igual
			for (Integer i = 1; i < quantVizinhos; i++) {
				if (vertice.get(i).getFrequencia() == freqs.get(index)) {
					i = -1;
					index++;
				}
			}
			// Adiciona uma cor ao vertice
			vertice.get(0).setFrequencia(freqs.get(index));
			quantCores++;
		}
		// Verificando se todos os vértices foram visitados para sair da recursão
		for (Integer i = 0, t = 0; i < grafo.size(); i++) {
			if (grafo.getVertices().get(i).getVisitado() != 0) t++;
			if (t == grafo.size()-1) {
				if(quantCores < minCores) {
					minCores = quantCores;
					melhorColoracao = grafo;
				}
				return;
			}
		}
		
		Integer indexProx;
		ArrayList<Vertice<type>> proxVert;
		for (Integer i = 1; i < quantVizinhos; i++) {
			if (grafo.getElementosGrafo().get(indexVert).get(i).getVisitado() == 0) {
				proxVert = grafo.getElementosGrafo().get(indexVert);
				indexProx = grafo.getVertice(proxVert.get(0).getDado());
				percorreGrafo(grafo, proxVert, indexProx, quantCores);
			}
		}
	}
}
