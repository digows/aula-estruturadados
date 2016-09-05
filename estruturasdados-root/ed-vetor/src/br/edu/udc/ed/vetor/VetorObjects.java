package br.edu.udc.ed.vetor;

import java.util.Arrays;

public class VetorObjects {

	// Inicializando um array de Object com capacidade 100.
	private Object[] objects = new Object[100];

	private int quantidade = 0;

	private void verificaCapacidade() {
		//se já estiver no máximo
		if (this.quantidade == this.objects.length) {
			//dobra a capacidade
			final Object[] novaArray = new Object[this.objects.length * 2];
			//copia os objects
			for (int i = 0; i < this.objects.length; i++) {
				novaArray[i] = this.objects[i];
			}
			this.objects = novaArray;
		}
	}

	public void adiciona(Object object) {
		this.verificaCapacidade();
		this.objects[quantidade] = object;
		this.quantidade++;
	}

	public void adiciona(int posicao, Object object) {
		if (!this.posicaoOcupada(posicao) && posicao != this.quantidade ) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		this.verificaCapacidade();
		// desloca todos os objects para a direita a partir da posição
		for (int i = this.quantidade - 1; i >= posicao; i -= 1) {
			this.objects[i + 1] = this.objects[i];
		}
		this.objects[posicao] = object;
		this.quantidade++;
	}

	public Object obtem(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		return this.objects[posicao];
	}

	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < this.quantidade;
	}

	public void remove(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		// desloca os objects da direita para a esquerda
		for (int i = posicao; i < this.quantidade - 1; i++) {
			this.objects[i] = this.objects[i + 1];
		}
		this.quantidade--;
	}

	public boolean contem(Object object) {
		for (int i = 0; i < this.quantidade; i++) {
			if (object.equals(this.objects[i])) {
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
		return Arrays.toString(objects);
	}
}