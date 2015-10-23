package br.edu.aula.ed.arvores;

import java.util.ArrayList;
import java.util.Collection;

public abstract class NodoAbstrato<E> implements Nodo<E> {

	protected Nodo<E> raiz;
	protected Nodo<E> pai;
	protected E elemento;
	protected Collection<Nodo<E>> filhos;

	// ------------------

	public NodoAbstrato(E elemento, Nodo<E> nodoPai) {
		if ( nodoPai == null || elemento == null ) {
			throw new IllegalArgumentException("Um nodo válido precisa de um pai e um elemento.");
		}
		
		this.pai = nodoPai;
		this.raiz = nodoPai.getRaiz();
		this.setElemento(elemento);
		this.filhos = new ArrayList<>();
	}

	public NodoAbstrato(E elementoRaiz) {
		if ( elementoRaiz == null ) {
			throw new IllegalArgumentException("Uma árvore precisa de um elemento raíz.");
		}
		
		this.elemento = elementoRaiz;
		this.pai = null;
		this.filhos = new ArrayList<>();
		this.raiz = this;
	}

	// ------------------

	@Override
	public int grau() {
		return this.getFilhos() != null ? this.getFilhos().size() : 0;
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
	public boolean externo() {
		return this.getFilhos() == null || this.getFilhos().isEmpty();
	}

	@Override
	public boolean interno() {
		return !this.externo();
	}

	@Override
	public boolean raiz() {
		return this.getRaiz().equals(this);
	}

	@Override
	public boolean formaArestaCom( Nodo<E> nodoDestino ) {
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
	public Collection<Nodo<E>> getFilhos() {
		return this.filhos;
	}

	@Override
	public Nodo<E> getPai() {
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
	public E getElemento() {
		return this.elemento;
	}

	@Override
	public void setElemento(E elemento) {
		this.elemento = elemento;
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

	// ------------------
	
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

	public static <E> boolean formamAresta( Nodo<E> nodoOrigem, Nodo<E> nodoDestino ) {
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
