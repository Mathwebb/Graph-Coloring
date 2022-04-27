package coloracao;

import java.util.ArrayList;
import java.util.Collections;
import grafos.Grafo;
import grafos.Vertice;

public class Heuristica<type> {
	public Grafo<type> welshPowel(Grafo<type> grafo) {
		ArrayList<Vertice<type>> vertices = grafo.getVertices();
		Collections.sort(vertices);
		ArrayList<Double> cores = calculaCores(grafo, 89.0, 1.0);
		
		for (Integer i = 0; i < cores.size(); i++) {
			for (Integer t = 0; t < vertices.size(); t++) {
				Vertice<type> verticeAtual = grafo.get(grafo.getVertice(vertices.get(t).getDado())).get(0);
				if (verticeAtual.getFrequencia() == 0.0 && coloracaoValida(grafo.get(grafo.getVertice(verticeAtual.getDado())), cores.get(i))) {
					vertices.get(t).setFrequencia(cores.get(i));
					verticeAtual.setFrequencia(cores.get(i));
				}
			}
		}
		
		return grafo;
	}
	
	private ArrayList<Double> calculaCores(Grafo<type> grafo, Double corInicio, Double intervalo) {
		ArrayList<Double> cores = new ArrayList<Double>();
		cores.add(corInicio+intervalo);
		
		for (Integer i = 1; i < grafo.size(); i++) {
			cores.add(cores.get(i-1)+intervalo);
		}
		return cores;
	}
	
	private Boolean coloracaoValida(ArrayList<Vertice<type>> vizinhos, Double frequencia) {
		for (Integer i = 1; i < vizinhos.size(); i++) {
			if (frequencia == vizinhos.get(i).getFrequencia()) {
				return false;
			}
		}
		return true;
	}
}
