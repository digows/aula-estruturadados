package br.edu.aula.ed.arvores;

import java.util.Collection;

/**
 * Árvore genérica
 * @param <E> Tipo de dado a ser mantido na árvore
 */
public interface Arvore<E> {
	
	public boolean contemNodos();
	public boolean formamAresta( Nodo<E> nodoOrigem, Nodo<E> nodoDestino );
	
	public Collection<Nodo<E>> caminho( Nodo<E> nodoOrigem, Nodo<E> nodoDestino );
	public int tamanho();
	public int comprimento( Nodo<E> nodoOrigem, Nodo<E> nodoDestino );
	public int profundidade ( Nodo<E> nodo );
	public int altura ( Nodo<E> nodo );

	public Nodo<E> setRaiz( E elementoRaiz );
	public Nodo<E> getRaiz();
	public Nodo<E> getNodo( E elemento );
}
