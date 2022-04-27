package grafos;


public class Vertice<type> implements Comparable<Vertice<type>>{ 
	private type dado;
	private Double frequencia;
	private Integer visitado;
	private Integer grau;
	
	public Vertice(type valor) {
		this.dado = valor;
		this.frequencia = 0.0;
		this.visitado = 0;
		this.grau = 0;
	}
	
	public Vertice(type valor, Double frequencia, Integer visitado, Integer grau) {
		this.dado = valor;
		this.frequencia = frequencia;
		this.visitado = visitado;
		this.grau = grau;
	}
	
	public Vertice() {
		this.dado = null;
		this.frequencia = 0.0;
		this.visitado = 0;
		this.grau = 0;
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

	public Integer getGrau() {
		return grau;
	}

	public void setGrau(Integer grau) {
		this.grau = grau;
	}

	public int compareTo(Vertice<type> outroVertice) {
		if (this.grau > outroVertice.getGrau()) {
			return -1;
		}
		
		if (this.grau < outroVertice.getGrau()) {
			return 1;
		}
		return 0;
	}
}
