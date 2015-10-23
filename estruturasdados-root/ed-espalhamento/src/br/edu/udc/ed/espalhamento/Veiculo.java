package br.edu.udc.ed.espalhamento;

public class Veiculo {
	private String modelo;
	private String cor;
	private Boolean conversivel;
	private Float peso;
	
	@Override
	public int hashCode(){
		final int primo = 31;
		int codigo = 1;
		
		if ( modelo != null ) {
			codigo += primo * codigo + modelo.hashCode();
		}
		
		if ( cor != null ) {
			codigo += primo * codigo + cor.hashCode();
		}
		
		if ( conversivel != null ) {
			codigo += primo * codigo + conversivel.hashCode();
		}
		
		if ( peso != null ) {
			codigo += primo * codigo + peso.hashCode();
		}
		return codigo;
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
