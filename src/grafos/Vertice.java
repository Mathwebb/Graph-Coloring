package grafos;

public class Vertice<type> { 
	private type dado;
	private Double frequencia;
	private Integer visitado;
	
	public Vertice(type valor) {
		this.dado = valor;
		this.frequencia = 0.0;
		this.visitado = 0;
	}
	
	public Vertice(type valor, Double frequencia, Integer visitado) {
		this.dado = valor;
		this.frequencia = frequencia;
		this.visitado = visitado;
	}
	
	public Vertice() {
		this.dado = null;
		this.frequencia = 0.0;
		this.visitado = 0;
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

	public Integer getVisitado() {
		return visitado;
	}

	public void setVisitado(Integer visitado) {
		this.visitado = visitado;
	}
}
