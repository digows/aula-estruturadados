package br.edu.aula.ed.arvores;

import java.util.Collection;

/**
 * Nó de uma árvore
 */
public interface Nodo<E> {

	public int grau();
	public int profundidade();
	public int altura();

	public boolean externo();
	public boolean interno();
	public boolean ancestralDe( Nodo<E> nodo );
	public boolean descendenteDe( Nodo<E> nodo );
	public boolean irmaoDe( Nodo<E> nodo );
	public boolean raiz();

	public boolean formaArestaCom( Nodo<E> nodoDestino );
	public Collection<Nodo<E>> caminho( Nodo<E> nodoDestino );
	public int comprimento( Nodo<E> nodoDestino );

	public Collection<Nodo<E>> getFilhos();
	public Arvore<E> getArvore();
	public Nodo<E> getPai();

	public E getElemento();
	public void setElemento( E elemento );
}