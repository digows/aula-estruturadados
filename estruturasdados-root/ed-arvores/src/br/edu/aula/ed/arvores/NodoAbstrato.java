package br.edu.aula.ed.arvores;

import java.util.Collection;

public abstract class NodoAbstrato<E> implements Nodo<E> {

	//------------------
	
	protected Arvore<E> arvore;
	protected Nodo<E> pai;
	protected E elemento;
	protected Collection<Nodo<E>> filhos;

	//------------------
	
	public NodoAbstrato( Arvore<E> arvore, E elemento, Nodo<E> nodoPai ) {
		if (arvore == null || elemento == null) {
			throw new IllegalArgumentException("A árvore nem elemento podem ser null");
		}
		
		this.pai = nodoPai;
		this.arvore = arvore;
		this.setElemento(elemento);
	}
	
	//------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arvore == null) ? 0 : arvore.hashCode());
		result = prime * result + ((elemento == null) ? 0 : elemento.hashCode());
		result = prime * result + ((pai == null) ? 0 : pai.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodoAbstrato<E> other = (NodoAbstrato<E>) obj;
		if (arvore == null) {
			if (other.arvore != null)
				return false;
		} else if (!arvore.equals(other.arvore))
			return false;
		if (elemento == null) {
			if (other.elemento != null)
				return false;
		} else if (!elemento.equals(other.elemento))
			return false;
		if (pai == null) {
			if (other.pai != null)
				return false;
		} else if (!pai.equals(other.pai))
			return false;
		return true;
	}
	
	
	@Override
	public Nodo<E> getPai() {
		return this.pai;
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
	public boolean raiz() {
		return this.arvore.getRaiz().equals(this);
	}

	@Override
	public E getElemento() {
		return this.elemento;
	}

	@Override
	public void setElemento( E elemento ) {
		this.elemento = elemento;
	}

	@Override
	public Arvore<E> getArvore() {
		return this.arvore;
	}
	
	@Override
	public Collection<Nodo<E>> getFilhos() {
		return this.filhos;
	}
	
	@Override
	public int grau() {
		return this.getFilhos() != null ? this.getFilhos().size() : 0;
	}

	@Override
	public boolean externo() {
		return this.getFilhos() == null || this.getFilhos().isEmpty();
	}

	@Override
	public boolean interno() {
		return !this.externo();
	}

	@Override
	public boolean formaArestaCom(Nodo<E> nodoDestino) {
		return this.arvore.formamAresta(this, nodoDestino);
	}

	@Override
	public Collection<Nodo<E>> caminho(Nodo<E> nodoDestino) {
		return this.arvore.caminho(this, nodoDestino);
	}

	@Override
	public int comprimento(Nodo<E> nodoDestino) {
		return this.arvore.comprimento(this, nodoDestino);
	}

	@Override
	public boolean ancestralDe(Nodo<E> nodo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean descendenteDe(Nodo<E> nodo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean irmaoDe(Nodo<E> nodo) {
		// TODO Auto-generated method stub
		return false;
	}
}
