package br.edu.udc.ed.mapas;

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
