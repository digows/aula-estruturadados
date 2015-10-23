package br.edu.aula.ed.arvores.encadeada;

import br.edu.aula.ed.arvores.Arvore;
import br.edu.aula.ed.arvores.Nodo;
import br.edu.aula.ed.arvores.NodoAbstrato;

public class NodoEncadeado<E> extends NodoAbstrato<E> {

	public NodoEncadeado( Arvore<E> arvore, E elemento, Nodo<E> nodoPai ) {
		super(arvore, elemento, nodoPai);
	}
	
	public NodoEncadeado<E> adicionar( E filho ) {
		final NodoEncadeado<E> nodoFilho = ((ArvoreEncadeada<E>) this.arvore).adicionar(filho, this);
		this.filhos.add( nodoFilho );
		return nodoFilho;
	}
}
