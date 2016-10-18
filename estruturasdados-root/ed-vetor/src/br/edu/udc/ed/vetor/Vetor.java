package br.edu.udc.ed.vetor;

import java.util.Arrays;

public class Vetor<T> {

	// Inicializando um array de Object com capacidade 100.
	private Object[] itens = new Object[100];

	private int quantidade = 0;

	private void verificaCapacidade() {
		//se já estiver no máximo
		if (this.quantidade == this.itens.length) {
			//dobra a capacidade
			final Object[] novaArray = new Object[this.itens.length * 2];
			//copia os itens
			for (int i = 0; i < this.itens.length; i++) {
				novaArray[i] = this.itens[i];
			}
			this.itens = novaArray;
		}
	}

	public void adiciona( Vetor<T> vetor ) {
		
		for (int i = 0; i < vetor.tamanho(); i++) {
			final T itemVetor = vetor.obtem(i);
			this.adiciona(itemVetor);
		}
	}
	
	public void adiciona(T object) {
		this.verificaCapacidade();
		this.itens[quantidade] = object;
		this.quantidade++;
	}

	public void adiciona(int posicao, T object) {
		if (!this.posicaoOcupada(posicao) && posicao != this.quantidade ) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		this.verificaCapacidade();
		// desloca todos os itens para a direita a partir da posição
		for (int i = this.quantidade - 1; i >= posicao; i -= 1) {
			this.itens[i + 1] = this.itens[i];
		}
		this.itens[posicao] = object;
		this.quantidade++;
	}

	@SuppressWarnings("unchecked")
	public T obtem(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		return (T) this.itens[posicao];
	}

	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < this.quantidade;
	}

	public void remove(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		// desloca os itens da direita para a esquerda
		for (int i = posicao; i < this.quantidade - 1; i++) {
			this.itens[i] = this.itens[i + 1];
		}
		this.quantidade--;
	}

	public boolean contem(T object) {
		for (int i = 0; i < this.quantidade; i++) {
			if (object.equals(this.itens[i])) {
				return true;
			}
		}
		return false;
	}

	public int tamanho() {
		return this.quantidade;
	}

	@Override
	public String toString() {
		return Arrays.toString(itens);
	}
}