package br.edu.aula.ed.arvores;

import java.util.Collection;

/**
 * Árvore genérica
 * @param <E> Tipo de dado a ser mantido na árvore
 */
public interface Nodo<E> {
	
	public int tamanhoArvore();
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

	public Nodo<E> adicionar( E elemento );
	
	public Collection<Nodo<E>> getFilhos();
	public Nodo<E> getRaiz();
	public Nodo<E> getPai();
	
	public E getElemento();
	public void setElemento( E elemento );
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String toString();
	@Override
	public int hashCode();
	@Override
	public boolean equals(Object obj);
}
