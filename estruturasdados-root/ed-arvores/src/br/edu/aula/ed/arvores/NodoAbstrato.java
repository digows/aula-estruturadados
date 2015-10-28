package br.edu.aula.ed.arvores;

import java.util.Collection;

public abstract class NodoAbstrato<E> implements Nodo<E> {

	protected Nodo<E> raiz;
	protected Nodo<E> pai;
	protected E elemento;

	// ------------------

	public NodoAbstrato(E elementoRaiz) {
		if (elementoRaiz == null) {
			throw new IllegalArgumentException("Uma árvore precisa de um elemento raíz.");
		}

		this.elemento = elementoRaiz;
		this.pai = null;
		this.raiz = this;
	}
	
	public NodoAbstrato(E elemento, Nodo<E> nodoPai) {
		if (nodoPai == null || elemento == null) {
			throw new IllegalArgumentException("Um nodo válido precisa de um pai e um elemento.");
		}

		this.pai = nodoPai;
		this.raiz = nodoPai.getRaiz();
		this.setElemento(elemento);
	}

	// ------------------

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
	public Nodo<E> getPai() {
		if ( this.pai == null ) {
			throw new IndexOutOfBoundsException("Este nodo não contém um pai. É uma raíz?");
		}
		return this.pai;
	}

	@Override
	public NodoAbstrato<E> getRaiz() {
		if (this.raiz == null) {
			throw new IndexOutOfBoundsException("Não existe nenhuma raiz definida.");
		}
		return (NodoAbstrato<E>) this.raiz;
	}
	
	@Override
	public boolean irmaoDe(Nodo<E> nodo) {
		return this.getPai().getFilhos().contains(nodo);
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
	public int profundidade() {
		return NodoAbstrato.profundidade(this);
	}

	@Override
	public int altura() {
		return NodoAbstrato.altura(this);
	}

	@Override
	public boolean raiz() {
		return this.getRaiz().equals(this);
	}

	@Override
	public boolean formaArestaCom(Nodo<E> nodoDestino) {
		return NodoAbstrato.formamAresta(this, nodoDestino);
	}

	@Override
	public Collection<Nodo<E>> caminho(Nodo<E> nodoDestino) {
		return NodoAbstrato.caminho(this, nodoDestino);
	}

	@Override
	public int comprimento(Nodo<E> nodoDestino) {
		return NodoAbstrato.comprimento(this, nodoDestino);
	}

	@Override
	public boolean ancestralDe(Nodo<E> nodo) {
		return NodoAbstrato.temAscendencia(this, nodo);
	}

	@Override
	public boolean descendenteDe(Nodo<E> nodo) {
		return NodoAbstrato.temDescendencia(this, nodo);
	}


	// ------------------
	@Override
	public String toString() {
		return NodoAbstrato.representarComChavesAninhadas(this).toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elemento == null) ? 0 : elemento.hashCode());
		result = prime * result + ((pai == null) ? 0 : pai.hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof NodoAbstrato)) {
			return false;
		}
		NodoAbstrato other = (NodoAbstrato) obj;
		if (elemento == null) {
			if (other.elemento != null) {
				return false;
			}
		} else if (!elemento.equals(other.elemento)) {
			return false;
		}
		if (pai == null) {
			if (other.pai != null) {
				return false;
			}
		} else if (!pai.equals(other.pai)) {
			return false;
		}
		return true;
	}

	// ------------------
	public static <E> StringBuilder representarComChavesAninhadas( Nodo<E> nodo ) {

		final StringBuilder builder = new StringBuilder( nodo.getElemento().toString() );
		
		if ( nodo.interno() ) {
			
			Boolean firstTime = true;
			
			for ( Nodo<E> nodoFilho :  nodo.getFilhos() )
				if (firstTime) {
					builder.append(" { " + representarComChavesAninhadas(nodoFilho) ); // the first child
					firstTime = false;
				} else
					builder.append( ", " + representarComChavesAninhadas(nodoFilho) ); // subsequent child
			builder.append( " }" ); // close parenthesis
		}
		
		return builder;
	}
	
	public static <E> int profundidade(Nodo<E> nodo) {
		if (nodo.raiz()) {
			return 0;
		}
		return 1 + NodoAbstrato.profundidade(nodo.getPai());
	}

	public static <E> int altura(Nodo<E> nodo) {
		if (nodo.externo()) {
			return 0;
		}

		int altura = 0;
		for (Nodo<E> nodoFilho : nodo.getFilhos()) {
			altura = Math.max(altura, NodoAbstrato.altura(nodoFilho));
		}
		return 1 + altura;
	}
	
	public static <E> boolean temAscendencia( Nodo<E> nodoOrigem, Nodo<E> nodoDestino ) {
		
		//if ( nodoOrigem.raiz() ) return true;
		
		if ( nodoOrigem.interno() ) {
			
			if ( nodoOrigem.getFilhos().contains( nodoDestino ) ) {
				return true;
			}
			 
			for ( Nodo<E> nodoFilho : nodoOrigem.getFilhos() ) {
				if ( nodoFilho.interno() ) {
					return NodoAbstrato.temAscendencia(nodoFilho, nodoDestino);
				}
				else {
					continue;
				}
			}
		}
		
		return false;
	}
	
	public static <E> boolean temDescendencia( Nodo<E> nodoOrigem, Nodo<E> nodoDestino ) {
		if ( nodoOrigem.raiz() )  {
			return false;
		}
		
		final Nodo<E> nodoPai = nodoOrigem.getPai();
		
		if ( nodoPai.equals(nodoDestino) ) {
			return true;
		} 
		
		return NodoAbstrato.temDescendencia( nodoPai , nodoDestino);
	}

	public static <E> boolean formamAresta(Nodo<E> nodoOrigem, Nodo<E> nodoDestino) {
		return nodoOrigem.getPai().equals(nodoDestino) || nodoDestino.getPai().equals(nodoOrigem);
	}

	public static <E> Collection<Nodo<E>> caminho(Nodo<E> nodoOrigem, Nodo<E> nodoDestino) {
		// TODO Auto-generated method stub
		return null;
	}

	public static <E> int comprimento(Nodo<E> nodoOrigem, Nodo<E> nodoDestino) {
		// TODO Auto-generated method stub
		return 0;
	}
}
