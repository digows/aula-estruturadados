package br.edu.aula.ed.arvores.learning;

import java.util.LinkedList;
import java.util.List;

public class NodoImpl<E> implements Nodo<E> {
	
	private Arvore<E> arvore;
	protected Nodo<E> pai;
	protected E elemento;
	private List<Nodo<E>> filhos = new LinkedList<>();
	
	public NodoImpl( Arvore<E> arvore, E elemento, Nodo<E> nodoPai ) {
		if ( arvore == null || elemento == null ) {
			throw new IllegalArgumentException("A árvore nem elemento podem ser null");
		}
		
		this.pai = nodoPai;
		this.arvore = arvore;
		this.setElemento(elemento);
	}

	@Override
	public Nodo<E> getPai() {
		return this.pai;
	}

	@Override
	public List<Nodo<E>> getFilhos() {
		return this.filhos;
	}

	@Override
	public int grau() {
		return this.filhos.size();
	}

	@Override
	public int profundidade() {
		return this.arvore.profundidade(this);
	}

	@Override
	public int altura() {
	    return this.arvore.altura(this);
	  }

	@Override
	public boolean externo() {
	    return this.getFilhos().isEmpty();
	}

	@Override
	public boolean interno() {
		return !this.getFilhos().isEmpty();
	}

	@Override
	public boolean ehRaiz() {
		return this.arvore.getRaiz().equals(this);
	}

	@Override
	public E getElemento() {
		return this.elemento;
	}

	@Override
	public void setElemento(E elemento) {
		this.elemento = elemento;
	}

	@Override
	public Nodo<E> adicionaPorUltimo( E filho ) {
		final Nodo<E> nodoFilho = this.arvore.adicionar(filho, this);
		this.filhos.add( nodoFilho );
		return nodoFilho;
	}

	@Override
	public Arvore<E> getArvore() {
		return this.arvore;
	}
	
	@Override
	public Nodo<E> remove(E filho) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ehAncestral( Nodo<E> nodo ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean descendenteDe( Nodo<E> nodo ) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean irmaoDe( Nodo<E> nodo ) {
		// TODO Auto-generated method stub
		return false;
	}
}
