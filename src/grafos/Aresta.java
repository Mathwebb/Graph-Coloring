package grafos;

public class Aresta<type> {
	private Vertice<type> inicio;
	private Vertice<type> fim;
	
	public Aresta(Vertice<type> inicio, Vertice<type> fim) {
		this.inicio = inicio;
		this.fim = fim;
	}

	public Vertice<type> getInicio() {
		return inicio;
	}

	public void setInicio(Vertice<type> inicio) {
		this.inicio = inicio;
	}

	public Vertice<type> getFim() {
		return fim;
	}

	public void setFim(Vertice<type> fim) {
		this.fim = fim;
	}
}
