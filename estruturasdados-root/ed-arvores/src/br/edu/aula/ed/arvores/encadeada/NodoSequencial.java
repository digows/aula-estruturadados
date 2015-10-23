package br.edu.aula.ed.arvores.encadeada;

import br.edu.aula.ed.arvores.Nodo;
import br.edu.aula.ed.arvores.NodoAbstrato;

public class NodoSequencial<E> extends NodoAbstrato<E> {

	protected int tamanhoArvore = 0;

	// ------------------

	public NodoSequencial(E elemento, Nodo<E> nodoPai) {
		super(elemento, nodoPai);
	}

	public NodoSequencial(E elementoRaiz) {
		super(elementoRaiz);
		this.tamanhoArvore = 1;
	}

	// ------------------
	@Override
	public NodoSequencial<E> adicionar(E elemento) {

		final Nodo<E> nodoFilho = new NodoSequencial<E>(elemento, this);
		super.filhos.add(nodoFilho);

		final NodoSequencial<E> raiz = (NodoSequencial<E>) super.getRaiz();
		raiz.tamanhoArvore++;

		return (NodoSequencial<E>) nodoFilho;
	}

	@Override
	public int tamanhoArvore() {
		return this.tamanhoArvore;
	}
}
