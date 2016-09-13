package br.edu.udc.ed.tabela.veiculo;

public class Veiculo {
	private String modelo;
	private String cor;
	private Boolean conversivel;
	private Float peso;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conversivel == null) ? 0 : conversivel.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Veiculo)) {
			return false;
		}
		Veiculo other = (Veiculo) obj;
		if (conversivel == null) {
			if (other.conversivel != null) {
				return false;
			}
		} else if (!conversivel.equals(other.conversivel)) {
			return false;
		}
		if (cor == null) {
			if (other.cor != null) {
				return false;
			}
		} else if (!cor.equals(other.cor)) {
			return false;
		}
		if (modelo == null) {
			if (other.modelo != null) {
				return false;
			}
		} else if (!modelo.equals(other.modelo)) {
			return false;
		}
		if (peso == null) {
			if (other.peso != null) {
				return false;
			}
		} else if (!peso.equals(other.peso)) {
			return false;
		}
		return true;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Boolean getConversivel() {
		return conversivel;
	}
	public void setConversivel(Boolean conversivel) {
		this.conversivel = conversivel;
	}
	public Float getPeso() {
		return peso;
	}
	public void setPeso(Float peso) {
		this.peso = peso;
	}
}
