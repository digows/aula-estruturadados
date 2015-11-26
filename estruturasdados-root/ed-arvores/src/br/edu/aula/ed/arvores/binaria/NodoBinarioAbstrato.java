
package br.edu.aula.ed.arvores.binaria;

import br.edu.aula.ed.arvores.NodoAbstrato;

public abstract class NodoBinarioAbstrato<E> extends NodoAbstrato<E> {
	
	public NodoBinarioAbstrato(E elemento, NodoBinarioAbstrato<E> nodoPai) {
		super(elemento, nodoPai);
	}

	public NodoBinarioAbstrato( E elementoRaiz ) {
		super(elementoRaiz);
	}
	
	public abstract NodoBinarioAbstrato<E> getEsquerdo();
	public abstract NodoBinarioAbstrato<E> getDireito();
}
