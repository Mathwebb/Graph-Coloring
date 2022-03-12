package grafos;

public class Vertice<type> { 
	private type dado;
	private Double frequencia;
	private Boolean visitado;
	
	public Vertice(type valor) {
		this.dado = valor;
		this.frequencia = 0.0;
		this.visitado = false;
	}
	
	public Vertice() {
		this.dado = null;
		this.frequencia = 0.0;
		this.visitado = false;
	}

	public type getDado() {
		return dado;
	}

	public void setDado(type dado) {
		this.dado = dado;
	}
	
	public Double getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Double frequencia) {
		this.frequencia = frequencia;
	}

	public Boolean getVisitado() {
		return visitado;
	}

	public void setVisitado(Boolean visitado) {
		this.visitado = visitado;
	}
}
