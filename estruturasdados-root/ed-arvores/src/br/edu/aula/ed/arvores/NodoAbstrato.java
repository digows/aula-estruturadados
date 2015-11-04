package br.edu.aula.ed.arvores;

import java.util.Collection;

public abstract class NodoAbstrato<E> {

	protected NodoAbstrato<E> raiz;
	protected NodoAbstrato<E> pai;
	protected E elemento;

	// ------------------

	public NodoAbstrato(E elementoRaiz) {
		if (elementoRaiz == null) {
			throw new IllegalArgumentException("Uma árvore precisa de um elemento raiz.");
		}

		this.setElemento(elementoRaiz);
		this.pai = null;
		this.raiz = this;
	}
	
	public NodoAbstrato(E elemento, NodoAbstrato<E> nodoPai) {
		if (nodoPai == null || elemento == null) {
			throw new IllegalArgumentException("Um nodo válido precisa de um pai e um elemento.");
		}

		this.pai = nodoPai;
		this.raiz = nodoPai.getRaiz();
		this.setElemento(elemento);
	}

	// ------------------

	public int grau() {
		return this.getFilhos() != null ? this.getFilhos().size() : 0;
	}
	
	public boolean externo() {
		return this.getFilhos() == null || this.getFilhos().isEmpty();
	}

	public boolean interno() {
		return !this.externo();
	}
	
	public NodoAbstrato<E> getPai() {
		if ( this.pai == null ) {
			throw new IndexOutOfBoundsException("Este nodo não contém um pai. É uma raíz?");
		}
		return this.pai;
	}

	public NodoAbstrato<E> getRaiz() {
		if (this.raiz == null) {
			throw new IndexOutOfBoundsException("Não existe nenhuma raiz definida.");
		}
		return (NodoAbstrato<E>) this.raiz;
	}
	
	public boolean irmaoDe(NodoAbstrato<E> nodo) {
		return this.getPai().getFilhos().contains(nodo);
	}
	
	public E getElemento() {
		return this.elemento;
	}

	public void setElemento(E elemento) {
		this.elemento = elemento;
	}
	
	public boolean raiz() {
		return this.getRaiz().equals(this);
	}

	public int profundidade() {
		return NodoAbstrato.profundidade(this);
	}

	public int altura() {
		return NodoAbstrato.altura(this);
	}

	public boolean formaArestaCom(NodoAbstrato<E> nodoDestino) {
		return NodoAbstrato.formamAresta(this, nodoDestino);
	}
	
	//-----------

	public Collection<NodoAbstrato<E>> caminho(NodoAbstrato<E> nodoDestino) {
		return NodoAbstrato.caminho(this, nodoDestino);
	}

	public int comprimento(NodoAbstrato<E> nodoDestino) {
		return NodoAbstrato.comprimento(this, nodoDestino);
	}

	public boolean ancestralDe(NodoAbstrato<E> nodo) {
		return NodoAbstrato.temAscendencia(this, nodo);
	}

	public boolean descendenteDe(NodoAbstrato<E> nodo) {
		return NodoAbstrato.temDescendencia(this, nodo);
	}

	// ------------------
	@Override
	public String toString() {
		return NodoAbstrato.representarComChavesAninhadas(this).toString();
	}

	@Override
	public int hashCode() {
		final int primo = 31;
		int hash = 1;
		hash = primo * hash + ((elemento == null) ? 0 : elemento.hashCode());
		hash = primo * hash + ((pai == null) ? 0 : pai.hashCode());
		return hash;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof NodoAbstrato)) return false;
		
		final NodoAbstrato other = (NodoAbstrato) obj;
		if (elemento == null) {
			if (other.elemento != null) return false;
		} else if (!elemento.equals(other.elemento)) return false;
		
		if (pai == null) {
			if (other.pai != null) return false;
		} else if (!pai.equals(other.pai)) return false;
		
		return true;
	}

	// ------------------
	public abstract int tamanhoArvore();
	public abstract NodoAbstrato<E> adicionar(E elemento);
	public abstract Collection<NodoAbstrato<E>> getFilhos();
	
	// ------------------
	
	public static <E> int profundidade(NodoAbstrato<E> nodo) {
		if (nodo.raiz()) {
			return 0;
		}
		return 1 + NodoAbstrato.profundidade(nodo.getPai());
	}

	public static <E> int altura(NodoAbstrato<E> nodo) {
		if (nodo.externo()) {
			return 0;
		}

		int altura = 0;
		for (NodoAbstrato<E> nodoFilho : nodo.getFilhos()) {
			altura = Math.max(altura, NodoAbstrato.altura(nodoFilho));
		}
		return 1 + altura;
	}
	
	public static <E> boolean formamAresta(NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino) {
		return nodoOrigem.getPai().equals(nodoDestino) || nodoDestino.getPai().equals(nodoOrigem);
	}
	
	public static <E> boolean temAscendencia( NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino ) {
		
		if ( nodoOrigem.raiz() ) return true;
		
		if ( nodoOrigem.interno() ) {
			
			for ( NodoAbstrato<E> nodoFilho : nodoOrigem.getFilhos() ) {
				
				if ( nodoFilho.equals(nodoDestino) || 
						NodoAbstrato.temAscendencia(nodoFilho, nodoDestino) ) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static <E> boolean temDescendencia( NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino ) {
		if ( nodoOrigem.raiz() )  {
			return false;
		}
		
		final NodoAbstrato<E> nodoPai = nodoOrigem.getPai();
		
		if ( nodoPai.equals(nodoDestino) ) {
			return true;
		} 
		
		return NodoAbstrato.temDescendencia( nodoPai , nodoDestino);
	}

	public static <E> Collection<NodoAbstrato<E>> caminho(NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <E> int comprimento(NodoAbstrato<E> nodoOrigem, NodoAbstrato<E> nodoDestino) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static <E> StringBuilder representarComChavesAninhadas( NodoAbstrato<E> nodo ) {

		final StringBuilder builder = new StringBuilder( nodo.getElemento().toString() );
		
		if ( nodo.interno() ) {
			
			Boolean firstTime = true;
			
			for ( NodoAbstrato<E> nodoFilho :  nodo.getFilhos() )
				if (firstTime) {
					builder.append(" { " + representarComChavesAninhadas(nodoFilho) );
					firstTime = false;
				} else
					builder.append( ", " + representarComChavesAninhadas(nodoFilho) );
			builder.append( " }" ); // close parenthesis
		}

		return builder;
	}
}
