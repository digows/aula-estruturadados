package br.edu.aula.ed.arvores;

import java.util.Collection;

public abstract class ArvoreAbstrata<E> implements Arvore<E> {

	protected int tamanho = 0;
	protected NodoAbstrato<E> raiz;

	//------------------
	
	public ArvoreAbstrata() {
	}
	
	public ArvoreAbstrata( E raiz ) {
		this.setRaiz(raiz);
	}
	
	//------------------

	@Override
	public int tamanho() {
		return this.tamanho;
	}

	@Override
	public NodoAbstrato<E> getRaiz() {
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
	public int profundidade ( Nodo<E> nodo ) {
	    if ( nodo.raiz() ) {
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
	public boolean formamAresta( Nodo<E> nodoOrigem, Nodo<E> nodoDestino ) {
		return nodoOrigem.getPai().equals(nodoDestino) || nodoDestino.getPai().equals(nodoOrigem);
	}

	@Override
	public Collection<Nodo<E>> caminho( Nodo<E> nodoOrigem, Nodo<E> nodoDestino ) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int comprimento( Nodo<E> nodoOrigem, Nodo<E> nodoDestino ) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NodoAbstrato<E> getNodo(E elemento) {
		// TODO Auto-generated method stub
		return null;
	}
}
