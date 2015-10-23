package br.edu.aula.ed.arvores.learning;

import java.util.List;

public class ArvoreImpl<E> implements Arvore<E> {

	protected int tamanho = 0;
	protected Nodo<E> raiz;
	
	public ArvoreImpl() {
	}
	
	public ArvoreImpl( E raiz ) {
		this.setRaiz(raiz);
	}

	@Override
	public int tamanho() {
		return this.tamanho;
	}

	@Override
	public Nodo<E> setRaiz(E raiz) {
		this.raiz = this.adicionar(raiz, null);
		this.tamanho = 1;// anulamos todos os outros
		return this.raiz;
	}

	@Override
	public Nodo<E> getRaiz() {
		if ( this.raiz == null ) {
			throw new IndexOutOfBoundsException("Não existe nenhuma raiz definida.");
		}
	    return this.raiz;
	}

	@Override
	public boolean contemNodos() {
		return this.tamanho != 0;
	}
	
	@Override
	public Nodo<E> adicionar( E elemento, Nodo<E> nodoPai ) {
		this.tamanho++;
		return new NodoImpl<E>( this, elemento, nodoPai ); 
	}
	
	@Override
	public int profundidade ( Nodo<E> nodo ) {
	    if ( nodo.ehRaiz() ) {
	    		return 0;
	    }
	    return 1 + this.profundidade( nodo.getPai() );
	}
	
	@Override
	public int altura ( Nodo<E> nodo ) {
	    if ( nodo.externo() ) {
	    		return 0;
	    }
	    
	    int altura = 0;
	    for ( Nodo<E> nodoFilho : nodo.getFilhos() ) {
	    		altura = Math.max( altura, this.altura(nodoFilho) );
	    }
	    return 1 + altura;
	}
	
	@Override
	public boolean formamAresta(E elemento1, E elemento2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Nodo<E>> caminho(E elemento1, E elemento2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int comprimento(E elemento1, E elemento2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
