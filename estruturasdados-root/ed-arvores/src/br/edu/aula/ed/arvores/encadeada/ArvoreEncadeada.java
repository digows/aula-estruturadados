package br.edu.aula.ed.arvores.encadeada;

import br.edu.aula.ed.arvores.ArvoreAbstrata;
import br.edu.aula.ed.arvores.Nodo;
import br.edu.aula.ed.arvores.NodoAbstrato;

public class ArvoreEncadeada<E> extends ArvoreAbstrata<E> {

	//------------------
	
	public ArvoreEncadeada( ) {
	}
	
	public ArvoreEncadeada( E elementoRaiz ) {
		super(elementoRaiz);
	}
	
	//------------------
	
	@Override
	public NodoEncadeado<E> setRaiz( E elementoRaiz ) {
		super.raiz = this.adicionar( elementoRaiz, null );
		super.tamanho = 1;// anulamos todos os outros
		return super.raiz;
	}
	
	public NodoEncadeado<E> adicionar( E elemento, Nodo<E> nodoPai ) {
		super.tamanho++;
		return new NodoEncadeado<E>( this, elemento, nodoPai ); 
	}
	
	@Override
	public NodoEncadeado<E> getRaiz() {
		return super.getRaiz();
	}
	
	@Override
	public NodoEncadeado<E> getNodo( E elemento ){
		return super.getNodo(elemento);
	}
}
