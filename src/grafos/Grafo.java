package grafos;

import java.util.ArrayList;
import java.util.Random;

public class Grafo<type>{
	private ArrayList<ArrayList<Vertice<type>>> grafo;
	
	//Construtor b�sico para criar um grafo vazio
	public Grafo() {
		grafo = new ArrayList<ArrayList<Vertice<type>>>();
	}
	
	//Construtor para copiar um grafo que foi passado como par�metro para o grafo que chamou a fun��o
	//Faz isso inicializando o grafo chamador da fun��o como vazio e aos poucos passando as informa�oes do grafo original
	//Primeiro cria os v�rtices e coloca eles em um ArrayList de v�rtices para depois colocar esse ArrayList no grafo 
	//com o objetivo de inicializar esse novo grafo com os v�rtices do grafo original
	//Ap�s isso adiciona as arestas em em cada ArrayList de v�rtices 
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
	
	//Fun��o que retorna um ArrayList de ArrayList de vertices que seria o grafo
	public ArrayList<ArrayList<Vertice<type>>> getElementosGrafo() {
		return grafo;
	}
	
	public ArrayList<Vertice<type>> get(Integer index) {
		return grafo.get(index);
	}
	
	//Fun��o que retorna o tamanho do grafo
	public Integer size() {
		return grafo.size();
	}
	
	//Fun��o que retorna um ArrayList de Vertices que representa os v�rtices do grafo 
	//e faz isso adicioando o primeiro elemento de cada lista do grafo em um novo ArrayList de vertices e no final retorna esse ArrayList
	public ArrayList<Vertice<type>> getVertices() {
		ArrayList<Vertice<type>> vertices = new ArrayList<Vertice<type>>();
		for (Integer i = 0; i < grafo.size(); i++) {
			vertices.add(grafo.get(i).get(0));
		}
		return vertices;
	}
	
	//Fun��o que retorna a posi��o de um determinado v�rtice caso ele exista no grafo e caso n�o existe retorna -1
	//e faz isso percorrendo o grafo e verificando se o dado do primeiro elemento de cada ArrayList do grafo corresponde ao dado procurado 
	public Integer getVertice(type dado) {
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(dado)) {
				return i;
			}
		}
		return -1;
	}
	
	//Fun��o que tenta retornar um objeto v�rtice que corresponde ao mesmo v�rtice que tem o dado que foi passado como par�metro 
	//e faz isso percorrendo o grafo e verificando se o dado do primeiro elemento de cada ArrayList do grafo corresponde ao dado procurado 
	public Vertice<type> getVerticeV(type dado) {
		for (Integer i = 0; i < grafo.size(); i++) {
			if (grafo.get(i).get(0).getDado().equals(dado)) {
				return grafo.get(i).get(0);
			}
		}
		return null;
	}
	
	//Fun��o que adiciona um v�rtice ao grafo com o dado que foi passado como par�metro
	//e faz isso veritifando se j� existe um v�rtice com esse dado, criando um novo objeto v�rtice ou pegando um que exite no grafo
	//e cria um novo ArrayList de v�rtices.
	//Ap�s isso adiciona o novo v�rtice ao novo ArrayList e adiciona esse ArryaList no grafo
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
	
	//Fun��o que retorna as arestas do grafo e faz isso criando um novo ArrayList de v�rtices que representa as arestas que v�o ser retornadas
	//depois disso percorre o grafo criando um v�rtice inicial e depois percorrendo o ArrayLisr que contem esse v�rtice
	//para que seja adicionado esse v�rtice e seus v�rtices vizinhos no ArrayList de v�rtices para no final retornar eles
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
	
	//Fun��o que procura se existe uma aresta entre dois v�rtices.
	//Recebe um grafo, um v�rtice em que vai ser procurada a aresta  e o v�rtice que queremos saber se tem uma aresta com o VertInicio
	//e retorna true caso exista uma aresta entre o v�rtice e false caso contr�rio
	//e faz isso procurando no grafo um v�rtice que seja igual ao v�rtice que esta sendo procurado para achar a aresta
	//depois disso percorrer o ArrayList que contem esse v�rtice para procurar o v�rtice que queremos saber se tem uma aresta
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
	
	//Fun��o para adicionar uma aresta para o grafo.
	//Recebe dois dados que representam os v�rtices que v�o se ligar atrav�s de uma aresta
	//Essa fun��o cria dois novos v�rtices com os dados que foram passados como par�metro
	//e depois disso percorre o grafo para saber se o grafo contem o primeiro v�rtice  e caso encontre adiciona o segundo v�rtice em seu ArrayList
	//ap�s isso faz o mesmo processo com o segundo vertice mas dessa vez adicionando o primeiro v�rtice em seu ArrayList caso seja encontrado
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
	
	//Fun��o para adicionar uma aresta para o grafo.
	//Recebe dois v�rtices que v�o ser ligados atrav�s de uma aresta
	//Essa fun��o percorre o grafo para saber se o grafo contem o primeiro v�rtice  e caso encontre adiciona o segundo v�rtice em seu ArrayList
	//ap�s isso faz o mesmo processo com o segundo vertice mas dessa vez adicionando o primeiro v�rtice em seu ArrayList caso seja encontrado
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
