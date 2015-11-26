package br.edu.aula.ed.arvores.lista;

import java.util.ArrayList;
import java.util.List;

import br.edu.aula.ed.arvores.NodoAbstrato;

public class NodoListaOrdenado<E> extends NodoAbstrato<E> {

	protected List<NodoAbstrato<E>> filhos = new ArrayList<>();
	protected int tamanhoArvore = 0;

	// ------------------
	public NodoListaOrdenado(E elemento, NodoListaOrdenado<E> nodoPai) {
		super(elemento, nodoPai);
	}

	public NodoListaOrdenado( E elementoRaiz ) {
		super(elementoRaiz);
		this.tamanhoArvore = 1;
	}

	// ------------------
	@Override
	public NodoListaOrdenado<E> adicionar(E elemento) {
		return this.adicionar(elemento, 0);
	}
	
	public NodoListaOrdenado<E> adicionar(E elemento, int ordem) {
		
		final NodoListaOrdenado<E> nodoFilho = new NodoListaOrdenado<E>(elemento, this);
		this.filhos.add(ordem, nodoFilho);
		
		final NodoListaOrdenado<E> raiz = (NodoListaOrdenado<E>) super.getRaiz();
		raiz.tamanhoArvore++;
		
		return (NodoListaOrdenado<E>) nodoFilho;
	}
	
	@Override
	public List<NodoAbstrato<E>> getFilhos() {
		return this.filhos;
	}

	@Override
	public int tamanhoArvore() {
		// TODO Auto-generated method stub
		return 0;
	}
}
