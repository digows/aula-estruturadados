
package br.edu.aula.ed.arvores.binaria;

import java.util.ArrayList;
import java.util.List;

import br.edu.aula.ed.arvores.NodoAbstrato;

public class NodoBinarioEncadeado<E> extends NodoBinarioAbstrato<E> {
	
	protected NodoBinarioEncadeado<E> esquerdo;
	protected NodoBinarioEncadeado<E> direito;
	protected int tamanhoArvore = 0;
	
	public NodoBinarioEncadeado(E elemento, NodoBinarioEncadeado<E> nodoPai) {
		super(elemento, nodoPai);
	}

	public NodoBinarioEncadeado( E elementoRaiz ) {
		super(elementoRaiz);
		this.tamanhoArvore = 1;
	}

	@Override
	public NodoBinarioEncadeado<E> getEsquerdo() {
		return this.esquerdo;
	}

	@Override
	public NodoBinarioEncadeado<E> getDireito() {
		return this.direito;
	}

	@Override
	public int tamanhoArvore() {
		return this.tamanhoArvore;
	}

	@Override
	public NodoBinarioEncadeado<E> adicionar(E elemento) {
		
		final NodoBinarioEncadeado<E> nodoFilho = new NodoBinarioEncadeado<E>(elemento, this);
		
		if ( this.getEsquerdo() == null ) {
			this.esquerdo = nodoFilho;
		} else if ( this.getDireito() == null ) {
			this.direito = nodoFilho;
		} else {
			throw new IllegalArgumentException("Esse nodo já contém um filho esquerdo e direito");
		}
		
		final NodoBinarioEncadeado<E> raiz = (NodoBinarioEncadeado<E>) super.getRaiz();
		raiz.tamanhoArvore++;
		
		return (NodoBinarioEncadeado<E>) nodoFilho;
	}

	@Override
	public List<NodoAbstrato<E>> getFilhos() {
		final List<NodoAbstrato<E>> filhos = new ArrayList<>();
		
		if ( this.getEsquerdo() != null ) {
			filhos.add(this.getEsquerdo());
		}
		
		if ( this.getDireito() != null ) {
			filhos.add(this.getDireito());
		}
		return filhos;
	}
}
