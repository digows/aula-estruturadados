package br.edu.udc.ed.listas;

import java.util.Arrays;

public class ListaGenerica<T> {

	// Inicializando um array de Object com capacidade 100.
	private Object[] objetos = new Object[100];

	private int quantidade = 0;

	private void verificaCapacidade() {
		//se já estiver no máximo
		if (this.quantidade == this.objetos.length) {
			//dobra a capacidade
			final Object[] novaArray = new Object[this.objetos.length * 2];
			//copia os objetos
			for (int i = 0; i < this.objetos.length; i++) {
				novaArray[i] = this.objetos[i];
			}
			this.objetos = novaArray;
		}
	}

	public void adiciona(T objeto) {
		this.verificaCapacidade();
		this.objetos[quantidade] = objeto;
		this.quantidade++;
	}

	public void adiciona(int posicao, T objeto) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		this.verificaCapacidade();
		// desloca todos os objetos para a direita a partir da posição
		for (int i = this.quantidade - 1; i >= posicao; i -= 1) {
			this.objetos[i + 1] = this.objetos[i];
		}
		this.objetos[posicao] = objeto;
		this.quantidade++;
	}

	@SuppressWarnings("unchecked")
	public T obtem(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		return (T) this.objetos[posicao];
	}

	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < this.quantidade;
	}

	public void remove(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		// desloca os objetos da direita para a esquerda
		for (int i = posicao; i < this.quantidade - 1; i++) {
			this.objetos[i] = this.objetos[i + 1];
		}
		this.quantidade--;
	}

	public boolean contem(T objeto) {
		for (int i = 0; i < this.quantidade; i++) {
			if (objeto.equals(this.objetos[i])) {
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
		return Arrays.toString(objetos);
	}
}