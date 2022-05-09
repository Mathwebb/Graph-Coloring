package grafos;

import java.util.ArrayList;
import java.util.Random;

public class Grafo<type>{
	private ArrayList<ArrayList<Vertice<type>>> grafo;
	
	//Construtor básico para criar um grafo vazio
	public Grafo() {
		grafo = new ArrayList<ArrayList<Vertice<type>>>();
	}
	
	//Construtor para copiar um grafo que foi passado como parâmetro para o grafo que chamou a função
	//Faz isso inicializando o grafo chamador da função como vazio e aos poucos passando as informaçoes do grafo original
	//Primeiro cria os vértices e coloca eles em um ArrayList de vértices para depois colocar esse ArrayList no grafo 
	//com o objetivo de inicializar esse novo grafo com os vértices do grafo original
	//Após isso adiciona as arestas em em cada ArrayList de vértices 
	public Grafo(Grafo<type> grafo) {
		ArrayList<ArrayList<Vertice<type>>> elementosGrafo = grafo.getElementosGrafo();
		this.grafo = new ArrayList<ArrayList<Vertice<type>>>();
		for (Integer i = 0; i < elementosGrafo.size(); i++) {
			ArrayList<Vertice<type>> novoArrayVertices = new ArrayList<Vertice<type>>();
			Vertice<type> antigoVertice = elementosGrafo.get(i).get(0);
			Vertice<type> novoVertice = new Vertice<type>(antigoVertice.getDado(), antigoVertice.getFrequencia(), antigoVertice.getVisitado(), antigoVertice.getGrau());
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
	
	//Função que retorna um ArrayList de ArrayList de vertices que seria o grafo
	public ArrayList<ArrayList<Vertice<type>>> getElementosGrafo() {
		return grafo;
	}
	
	public ArrayList<Vertice<type>> get(Integer index) {
		return grafo.get(index);
	}
	
	//Função que retorna o tamanho do grafo
	public Integer size() {
		return grafo.size();
	}
	
	//Função que retorna um ArrayList de Vertices que representa os vértices do grafo 
	//e faz isso adicioando o primeiro elemento de cada lista do grafo em um novo ArrayList de vertices e no final retorna esse ArrayList
	public ArrayList<Vertice<type>> getVertices() {
		ArrayList<Vertice<type>> vertices = new ArrayList<Vertice<type>>();
		for (Integer i = 0; i < grafo.size(); i++) {
			vertices.add(grafo.get(i).get(0));
		}
		return vertices;
	}
	
	//Função que retorna a posição de um determinado vértice caso ele exista no grafo e caso não existe retorna -1
	//e faz isso percorrendo o grafo e verificando se o dado do primeiro elemento de cada ArrayList do grafo corresponde ao dado procurado 
	public Integer getVertice(type dado) {
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(dado)) {
				return i;
			}
		}
		return -1;
	}
	
	//Função que tenta retornar um objeto vértice que corresponde ao mesmo vértice que tem o dado que foi passado como parâmetro 
	//e faz isso percorrendo o grafo e verificando se o dado do primeiro elemento de cada ArrayList do grafo corresponde ao dado procurado 
	public Vertice<type> getVerticeV(type dado) {
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(dado)) {
				return grafo.get(i).get(0);
			}
		}
		return null;
	}
	
	//Função que adiciona um vértice ao grafo com o dado que foi passado como parâmetro
	//e faz isso veritifando se já existe um vértice com esse dado, criando um novo objeto vértice ou pegando um que exite no grafo
	//e cria um novo ArrayList de vértices.
	//Após isso adiciona o novo vértice ao novo ArrayList e adiciona esse ArryaList no grafo
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
	
	//Função que retorna as arestas do grafo e faz isso criando um novo ArrayList de vértices que representa as arestas que vão ser retornadas
	//depois disso percorre o grafo criando um vértice inicial e depois percorrendo o ArrayLisr que contem esse vértice
	//para que seja adicionado esse vértice e seus vértices vizinhos no ArrayList de vértices para no final retornar eles
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
	
	//Função que procura se existe uma aresta entre dois vértices.
	//Recebe um grafo, um vértice em que vai ser procurada a aresta  e o vértice que queremos saber se tem uma aresta com o VertInicio
	//e retorna true caso exista uma aresta entre o vértice e false caso contrário
	//e faz isso procurando no grafo um vértice que seja igual ao vértice que esta sendo procurado para achar a aresta
	//depois disso percorrer o ArrayList que contem esse vértice para procurar o vértice que queremos saber se tem uma aresta
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
	
	//Função para adicionar uma aresta para o grafo.
	//Recebe dois dados que representam os vértices que vão se ligar através de uma aresta
	//Essa função cria dois novos vértices com os dados que foram passados como parâmetro
	//e depois disso percorre o grafo para saber se o grafo contem o primeiro vértice  e caso encontre adiciona o segundo vértice em seu ArrayList
	//após isso faz o mesmo processo com o segundo vertice mas dessa vez adicionando o primeiro vértice em seu ArrayList caso seja encontrado
	public void addAresta(type dadoVertInicio, type dadoVertFim) {
		Vertice<type> verticeInicio = new Vertice<type>(dadoVertInicio);
		Vertice<type> verticeFim = new Vertice<type>(dadoVertFim);
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(verticeInicio.getDado())) {
				grafo.get(i).add(grafo.get(getVertice(dadoVertFim)).get(0));
				grafo.get(i).get(0).setGrau(grafo.get(i).get(0).getGrau()+1);
				break;
			}
		}
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(verticeFim.getDado())) {
				grafo.get(i).add(grafo.get(getVertice(dadoVertInicio)).get(0));
				grafo.get(i).get(0).setGrau(grafo.get(i).get(0).getGrau()+1);
				break;
			}
		}
	}
	
	//Função para adicionar uma aresta para o grafo.
	//Recebe dois vértices que vão ser ligados através de uma aresta
	//Essa função percorre o grafo para saber se o grafo contem o primeiro vértice  e caso encontre adiciona o segundo vértice em seu ArrayList
	//após isso faz o mesmo processo com o segundo vertice mas dessa vez adicionando o primeiro vértice em seu ArrayList caso seja encontrado
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
	
	public void constroiGrafoAleatorio(ArrayList<type> vertices, Integer arestas) {
		grafo = new ArrayList<ArrayList<Vertice<type>>>();
		Random gerador = new Random();
		Boolean flag = true;
		for (Integer i = 0; i < vertices.size(); i++) {
			addVertices(vertices.get(i));
		}
		int maxAresta = 0;
		for (int i = 1; i < vertices.size(); i++) {
			maxAresta += i;
		}
		if (arestas > maxAresta) {
			arestas--;
		}
		for (Integer i = 0; i < arestas; i++) {
			Integer vertInicio = gerador.nextInt(vertices.size());
			Integer vertFim = gerador.nextInt(vertices.size());
			if (vertInicio == vertFim) {
				i--;
				continue;
			}
			for (Integer t = 1; t < grafo.get(vertInicio).size(); t++) {
				if (grafo.get(vertInicio).get(t).getDado() == vertices.get(vertFim)) {
					i--;
					flag = false;
					break;
				}
			}
			if (flag) {
				addAresta(vertices.get(vertInicio), vertices.get(vertFim));
			}
			flag = true;
		}
	}
}
