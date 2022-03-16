package coloracao;

import java.util.ArrayList;

import grafos.Grafo;
import grafos.Vertice;

public class Colorir <type> {
	private ArrayList<Double> freqs;
	private Integer minCores;
	private Integer iteracao = 0;
	private Grafo<type> melhorColoracao = null;
	
	//Fun��o que recebe um grafo, uma frequencia de r�dio inicial e o intervalo que essa frequ�ncia vai aumentar
	//e retorna um ArrayList onde cada posi��o � uma frequencia que aumenta em um conforme o �ndice aumenta
	//Essa fun��o faz isso atravez de um for que vai adicionando novas frequencias com valor da frequencia anterior adicionado com o intervalo
	//at� o ArrayList de frequ�ncias ter o mesmo tamanho do grafo passado no par�metro da fun��o
	//Complexidade: O(n)
	public ArrayList<Double> calculaFreqs(Grafo<type> grafo, Double freqInicio, Double intervalo) {
		ArrayList<Double> freqs = new ArrayList<Double>();
		freqs.add(freqInicio+intervalo);
		
		for (Integer i = 1; i < grafo.size(); i++) {
			freqs.add(freqs.get(i-1)+intervalo);
		}
		return freqs;
	}
	
	//Fun��o que recebe um grafo e, usando ele como base, retorna um novo grafo com os v�rtices coloridos da melhor forma poss�vel
	//Essa fun��o faz isso atrav�s de um for que testa todas as possibilidades poss�veis atrav�s da fun��o percorreGrafo()
	//para saber qual � a melhor colora��o poss�vel para o grafo e no final retorna esse grafo
	//Complexidade: n*T(n)
	public Grafo<type> coloreGrafo(Grafo<type> grafo) {
		freqs = calculaFreqs(grafo, 90.0, 1.0);
		minCores = freqs.size();
		Grafo<type> grafoCopia = new Grafo<type>(grafo);
		
		for (Integer i = 0; i < grafo.size(); i++) {
			grafoCopia = new Grafo<type>(grafo);
			percorreGrafo(grafoCopia, i, 0);
		}
		return melhorColoracao;
	}
	
	//Fun��o recursiva que recebe um grafo, o �ndice do v�rtice atual e a quantidade de ores usadas at� o momento � repons�vel
	//por percorrer e colorir todo o grafo recursivamente
	public Grafo<type> percorreGrafo(Grafo<type> grafo, Integer indexVert, Integer quantCores) {
		Vertice<type> vertice = grafo.getElementosGrafo().get(indexVert).get(0);
		ArrayList<Vertice<type>> vizinhos = grafo.getElementosGrafo().get(indexVert);
		Integer indexCorAtual = 0;
		System.out.println(iteracao++);
		
		//Verifica se o vertice ja foi visitado e se n�o foi adiciona uma cor a esse v�rtice, bem como marca que ele j� foi visitado uma vez
		//se o v�rtice j� foi visitado ent�o s� marca que o v�rtice foi visitado n+1 vezes
		if (vertice.getVisitado() == 0) {
			vertice.setVisitado(1);
			//Garante que o v�rtice atual n�o vai receber a mesmo cor que os seus vizinhos
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
		
		//Se o grafo j� tiver sido todo percorrido e todos os v�rtices possuem alguma cor retorna o grafo da colora��o atual
		//se o n�mero de cores usadas for menor que o menor n�mero de cores usadas na melhor colora��o at� agora ent�o atualiza
		//a variavel para o menor n�mero de cores usadas at� agora e o grafo que possui a melhor coloracao at� agora
		Grafo<type> grafoCopia = new Grafo<type>(grafo);
		if (elementosNaoVisitados(grafo) == 0) {
			if (quantCores <= minCores) {
				minCores = quantCores;
				melhorColoracao = grafoCopia;
			}
			return grafoCopia;
		}
		
		
		Integer vizMenosVis = 0;
		Grafo<type> grafoRes = null;
		Integer menosVisitas = Integer.MAX_VALUE;
		//Verifica os vizinhos do vertice atual, se houver algum vertice que ainda nao foi visitado, chama o metodo recursivo passando
		//os dados para a fun��o, se n�o houver nenhum vizinho n�o visitado ent�o o algoritmo vai para o vizinho que foi visitado menos vezes
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
		}
		
		return grafoRes;
	}
	
	//Fun��o que recebe um grafo e retorna o n�mero de v�rtices que n�o foram visitados 
	//e faz isso percorrendo o grafo e contando quantos v�rtices ainda n�o foram visitados
	//Complexidade O(n)
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
