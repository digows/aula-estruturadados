package br.edu.udc.ed.listas;

import java.util.Arrays;

public class Alunos {

	// Inicializando um array de Aluno com capacidade 100.
	private Aluno[] alunos = new Aluno[100];

	private int quantidade = 0;

	private void verificaCapacidade() {
		//se já estiver no máximo
		if (this.quantidade == this.alunos.length) {
			//dobra a capacidade
			final Aluno[] novaArray = new Aluno[this.alunos.length * 2];
			//copia os alunos
			for (int i = 0; i < this.alunos.length; i++) {
				novaArray[i] = this.alunos[i];
			}
			this.alunos = novaArray;
		}
	}

	public void adiciona(Aluno aluno) {
		this.verificaCapacidade();
		this.alunos[quantidade] = aluno;
		this.quantidade++;
	}

	public void adiciona(int posicao, Aluno aluno) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		this.verificaCapacidade();
		// desloca todos os alunos para a direita a partir da posição
		for (int i = this.quantidade - 1; i >= posicao; i -= 1) {
			this.alunos[i + 1] = this.alunos[i];
		}
		this.alunos[posicao] = aluno;
		this.quantidade++;
	}

	public Aluno obtem(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		return this.alunos[posicao];
	}

	private boolean posicaoOcupada(int posicao) {
		return posicao >= 0 && posicao < this.quantidade;
	}

	public void remove(int posicao) {
		if (!this.posicaoOcupada(posicao)) {
			throw new IndexOutOfBoundsException("Posição inválida");
		}
		// desloca os alunos da direita para a esquerda
		for (int i = posicao; i < this.quantidade - 1; i++) {
			this.alunos[i] = this.alunos[i + 1];
		}
		this.quantidade--;
	}

	public boolean contem(Aluno aluno) {
		for (int i = 0; i < this.quantidade; i++) {
			if (aluno.equals(this.alunos[i])) {
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
		return Arrays.toString(alunos);
	}
}