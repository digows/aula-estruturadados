package br.edu.aula.ed.arvores.lista;

import java.util.HashSet;
import java.util.Set;

import br.edu.aula.ed.arvores.NodoAbstrato;

public class NodoLista<E> extends NodoAbstrato<E> {

	protected Set<NodoAbstrato<E>> filhos = new HashSet<>();
	protected int tamanhoArvore = 0;

	// ------------------

	public NodoLista(E elemento, NodoLista<E> nodoPai) {
		super(elemento, nodoPai);
	}

	public NodoLista( E elementoRaiz ) {
		super(elementoRaiz);
		this.tamanhoArvore = 1;
	}

	// ------------------
	@Override
	public NodoLista<E> adicionar(E elemento) {

		final NodoAbstrato<E> nodoFilho = new NodoLista<E>(elemento, this);
		this.filhos.add(nodoFilho);

		final NodoLista<E> raiz = (NodoLista<E>) super.getRaiz();
		raiz.tamanhoArvore++;
		
		return (NodoLista<E>) nodoFilho;
	}

	@Override
	public int tamanhoArvore() {
		return this.tamanhoArvore;
	}

	@Override
	public Set<NodoAbstrato<E>> getFilhos() {
		return this.filhos;
	}
}
