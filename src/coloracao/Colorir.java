package coloracao;

import java.util.ArrayList;

import grafos.Grafo;
import grafos.Vertice;

public class Colorir <type> {
	private ArrayList<Double> freqs;
	private Integer minCores;
	private Integer iteracao = 0;
	
	public ArrayList<Double> calculaFreqs(Grafo<type> grafo, Double freqInicio, Double intervalo) {
		ArrayList<Double> freqs = new ArrayList<Double>();
		freqs.add(freqInicio+intervalo);
		
		for (Integer i = 1; i < grafo.size(); i++) {
			freqs.add(freqs.get(i-1)+intervalo);
		}
		return freqs;
	}
	
	public Grafo<type> coloreGrafo(Grafo<type> grafo) {
		freqs = calculaFreqs(grafo, 90.0, 1.0);
		minCores = freqs.size();
		Grafo<type> melhorColoracao = null;
		Grafo<type> grafoCopia = new Grafo<type>(grafo);
		
//		for (Integer i = 0; i < grafo.size(); i++) {
//			System.out.println(grafoCopia.getElementosGrafo().get(i).get(0));
//			System.out.println(grafoCopia.getElementosGrafo().get(i));
//		}
		
		for (Integer i = 0; i < grafo.size(); i++) {
			grafoCopia = new Grafo<type>(grafo);
			melhorColoracao = percorreGrafo(grafoCopia, i, 0);
		}
		return melhorColoracao;
	}
	
	public Grafo<type> percorreGrafo(Grafo<type> grafo, Integer indexVert, Integer quantCores) {
		Vertice<type> vertice = grafo.getElementosGrafo().get(indexVert).get(0);
		ArrayList<Vertice<type>> vizinhos = grafo.getElementosGrafo().get(indexVert);
		Integer indexCorAtual = 0;
		System.out.println(iteracao++);
		
		//Verifica se o vertice ja foi visitado e se não foi adiciona uma cor
		if (vertice.getVisitado() == 0) {
			vertice.setVisitado(1);
			for (Integer i = 0; i < vizinhos.size(); i++) {
				if (vizinhos.get(i).getFrequencia() == freqs.get(indexCorAtual)) {
					i = 0;
					indexCorAtual++;
				}
			}
			vertice.setFrequencia(freqs.get(indexCorAtual));
			quantCores++;
		} else {
			vertice.setVisitado(vertice.getVisitado()+1);
		}
		
		//Se o grafo ja tiver sido todo percorrido retorna 1
		Grafo<type> grafoCopia = new Grafo<type>(grafo);
		if (elementosNaoVisitados(grafo) == 0) {
			if (quantCores <= minCores) {
				minCores = quantCores;
			}
			return grafoCopia;
		}
		

		Integer vizMenosVis = 0;
		Grafo<type> grafoRes = null;
		Integer menosVisitas = Integer.MAX_VALUE;
		for (Integer i = 1; i < vizinhos.size(); i++) {
			if (vizinhos.get(i).getVisitado() == 0) {
				grafoRes = percorreGrafo(grafoCopia, grafoCopia.getVertice(vizinhos.get(i).getDado()), quantCores);
			}else if (vizinhos.get(i).getVisitado() <= menosVisitas) {
				vizMenosVis = grafoCopia.getVertice(vizinhos.get(i).getDado());
				menosVisitas = grafoCopia.getVertices().get(vizMenosVis).getVisitado();
			}
		}
		if (elementosNaoVisitados(grafo) != 1) {
			grafoRes = percorreGrafo(grafoCopia, vizMenosVis, quantCores);
		} else {
			return grafoRes;
		}
		
		return grafoRes;
	}
	
	public Integer elementosNaoVisitados(Grafo<type> grafo) {
		Integer elemNaoVis = 0;
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.getElementosGrafo().get(i).get(0).getVisitado() == 0) {
				elemNaoVis++;
			}
		}
		return elemNaoVis;
	}
}
