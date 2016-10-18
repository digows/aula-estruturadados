package br.edu.udc.ed.mapa.heroi;

public class Heroi {
	
	private String nome;
	private Float forca;
	private String especialidade;
	private String fraqueza;
	private Boolean humano;
	private Boolean voa;
	
	public Heroi(){
	}
	
	public Heroi( String nome, Float forca, Boolean humano, Boolean voa ){
		this.nome = nome;
		this.forca = forca;
		this.humano = humano;
		this.voa = voa;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((especialidade == null) ? 0 : especialidade.hashCode());
		result = prime * result + ((forca == null) ? 0 : forca.hashCode());
		result = prime * result + ((fraqueza == null) ? 0 : fraqueza.hashCode());
		result = prime * result + ((humano == null) ? 0 : humano.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((voa == null) ? 0 : voa.hashCode());
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
		if (!(obj instanceof Heroi)) {
			return false;
		}
		Heroi other = (Heroi) obj;
		if (especialidade == null) {
			if (other.especialidade != null) {
				return false;
			}
		} else if (!especialidade.equals(other.especialidade)) {
			return false;
		}
		if (forca == null) {
			if (other.forca != null) {
				return false;
			}
		} else if (!forca.equals(other.forca)) {
			return false;
		}
		if (fraqueza == null) {
			if (other.fraqueza != null) {
				return false;
			}
		} else if (!fraqueza.equals(other.fraqueza)) {
			return false;
		}
		if (humano == null) {
			if (other.humano != null) {
				return false;
			}
		} else if (!humano.equals(other.humano)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (voa == null) {
			if (other.voa != null) {
				return false;
			}
		} else if (!voa.equals(other.voa)) {
			return false;
		}
		return true;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Float getForca() {
		return forca;
	}
	public void setForca(Float forca) {
		this.forca = forca;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public String getFraqueza() {
		return fraqueza;
	}
	public void setFraqueza(String fraqueza) {
		this.fraqueza = fraqueza;
	}
	public Boolean getHumano() {
		return humano;
	}
	public void setHumano(Boolean humano) {
		this.humano = humano;
	}
	public Boolean getVoa() {
		return voa;
	}
	public void setVoa(Boolean voa) {
		this.voa = voa;
	}
}
