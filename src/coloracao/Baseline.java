package coloracao;

import java.util.ArrayList;

import grafos.Grafo;
import grafos.Vertice;

public class Baseline<type> {
	
	public ArrayList<ArrayList<Double>> calculaPossibilidades(Grafo<type> grafo, ArrayList<Double> cores) {
		ArrayList<ArrayList<Double>> possibilidades = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> possibilidadesNovas = new ArrayList<ArrayList<Double>>();
		for(int x = 0; x < grafo.size(); x++) {
			if(x == 0) {
				for(int g = 0;g < grafo.size(); g++) {
					ArrayList<Double> possibilidade = new ArrayList<Double>();
					possibilidade.add(0.0);
					possibilidade.set(0, cores.get(g));
					possibilidades.add(possibilidade);
				}
			}
			else {
				for(int t = 0; t < possibilidades.size(); t++) {
					for(int l = 0; l < grafo.size(); l++) {
						possibilidades.get(t).add(cores.get(l));
						ArrayList<Double> possibilidade = new ArrayList<Double>(possibilidades.get(t));
						possibilidadesNovas.add(possibilidade);
						possibilidades.get(t).remove(possibilidades.get(t).size()-1);
					}
				}
				possibilidades=possibilidadesNovas;
				possibilidadesNovas = new ArrayList<ArrayList<Double>>();
			}
		}
		return possibilidades;
	}
	
	private ArrayList<Double> calculaCores(Grafo<type> grafo, Double corInicio, Double intervalo) {
		ArrayList<Double> cores = new ArrayList<Double>();
		cores.add(corInicio+intervalo);
		
		for (Integer i = 1; i < grafo.size(); i++) {
			cores.add(cores.get(i-1)+intervalo);
		}
		return cores;
	}
	
	public Grafo<type> algoritmoExato(Grafo<type> grafo){
		ArrayList<ArrayList<Double>> possibilidades = calculaPossibilidades(grafo, calculaCores(grafo, 89.0, 1.0));
		Grafo<type> melhorColoracao = null;
		Grafo<type> resultado;
		Integer numCores = grafo.size();
		for (Integer i = 0; i < possibilidades.size(); i++) {
			resultado = coloreGrafo(new Grafo<type>(grafo), possibilidades.get(i), numCores);
			if (resultado != null) {
				melhorColoracao = resultado;
				numCores = validaSolucaoOtima(melhorColoracao, numCores);
			}
		}
		return melhorColoracao;
	}
	
	private Grafo<type> coloreGrafo(Grafo<type> grafo, ArrayList<Double> possibilidade, Integer numCores){
		for (Integer i = 0; i < grafo.size(); i++) {
			grafo.get(i).get(0).setFrequencia(possibilidade.get(i));
		}
		Boolean coloracaoValida = validaColoracao(grafo);
		Integer novoNumCores = validaSolucaoOtima(grafo, numCores);
		if (coloracaoValida && novoNumCores < numCores) {
			return grafo;
		}
		return null;
	}
	
	private Boolean validaColoracao(Grafo<type> grafo) {
		for (Integer i = 0; i < grafo.size(); i++) {
			ArrayList<Vertice<type>> vertice = grafo.get(i);
			for (Integer t = 1; t < grafo.get(i).size(); t++) {
				if (vertice.get(0).getFrequencia() == vertice.get(t).getFrequencia()) {
					return false;
				}
			}
		}
		return true;
	}
	
	private Integer validaSolucaoOtima(Grafo<type> grafo, Integer numCores) {
		ArrayList<Double> coresUsadas = new ArrayList<Double>();
		for (Integer i = 0; i < grafo.size(); i++) {
			if (coresUsadas.size() == 0) {
				coresUsadas.add(grafo.get(i).get(0).getFrequencia());
			} else {
				Boolean flag = true;
				for (Integer t = 0; t < coresUsadas.size(); t++) {
					if (coresUsadas.get(t) == grafo.get(i).get(0).getFrequencia()) {
						flag = false;
					}
				}
				if (flag) {
					coresUsadas.add(grafo.get(i).get(0).getFrequencia());
				}
			}
		}
		return coresUsadas.size();
	}
}
