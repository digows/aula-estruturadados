package br.edu.aula.ed.arvores;

/**
 * Nó de uma árvore ordenada
 */
public interface NodoOrdenado<E> extends Nodo<E> {
	public int ordem();
}