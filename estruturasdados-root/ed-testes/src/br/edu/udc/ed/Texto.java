package br.edu.udc.ed;

public class Texto {
	
	private String texto;
	
	public Texto( String texto ){
		this.texto = texto;
	}
	
	public String converterEmMinusculo(){
		return this.texto.toLowerCase();
	}
	
	public int indiceDe( String caracteres ) {
		return this.texto.indexOf(caracteres);
	}
	
	public String subTexto( int inicio, int fim ) {
		return this.texto.substring( inicio, fim );
	}
}
