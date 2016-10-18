package br.edu.udc.ed.mapa;

import br.edu.udc.ed.vetor.Vetor;

public class Mapa<C, V> {

	private Vetor<Vetor<Associacao<C, V>>> tabela = new Vetor<>();

	private int quantidade = 0;

	public Mapa() {
		for (int i = 0; i < 100; i++) {
			this.tabela.adiciona(new Vetor<Associacao<C, V>>());
		}
	}
	
	private void redimensionaTabela(int novaCapacidade) {
		final Vetor<Associacao<C,V>> associacoes = new Vetor<>();
		
		for ( int i = 0; i < this.tabela.tamanho(); i++ ) {
			final Vetor<Associacao<C,V>> associacoesTabela = this.tabela.obtem(i);
			associacoes.adiciona(associacoesTabela);
		}
		
		// remove todas as listas de associacoes da tabela;
		this.tabela = new Vetor<>();

		for (int i = 0; i < novaCapacidade; i++) {
			this.tabela.adiciona( new Vetor<Associacao<C,V>>() );
		}
		
		for (int i = 0; i < associacoes.tamanho(); i++) {
			final Associacao<C,V> associacao = associacoes.obtem(i);
			final int indice = this.calculaIndiceDaTabela( associacao.getChave() );
			this.tabela.obtem(indice).adiciona(associacao);
			
		}
	}

	private void verificaCarga() {
		final int capacidade = this.tabela.tamanho();
		final double carga = (double) this.quantidade / capacidade;

		if ( carga > 0.75 ) {
			this.redimensionaTabela(capacidade * 2);
		} else if (carga < 0.25) {
			// Math.max() retorna o maior número entre os 2 parâmetros
			this.redimensionaTabela(Math.max(capacidade / 2, 100));
		}
		// Se a carga for >=0.25 && <= 0.75 não preciamos mexer;
	}

	private int calculaIndiceDaTabela(C chave) {
		return Math.abs(chave.hashCode()) % this.tabela.tamanho();
	}

	public void adiciona(C chave, V valor) {
		if (this.contem(chave)) {
			this.remove(chave);
		}
		this.verificaCarga();
		final int indice = this.calculaIndiceDaTabela(chave);
		final Vetor<Associacao<C, V>> lista = this.tabela.obtem(indice);
		lista.adiciona(new Associacao<>(chave, valor));
		this.quantidade++;
	}

	public void remove(C chave) {
		final int indice = this.calculaIndiceDaTabela(chave);

		final Vetor<Associacao<C, V>> lista = this.tabela.obtem(indice);
		for (int i = 0; i < lista.tamanho(); i++) {
			final Associacao<C, V> associacao = lista.obtem(i);
			if (associacao.getChave().equals(chave)) {
				lista.remove(i);
				this.quantidade--;
				this.verificaCarga();
				return;
			}
		}
		throw new IllegalArgumentException("Não existe valor com esta Chave");
	}

	public V obtem(C chave) {
		final int indice = this.calculaIndiceDaTabela(chave);
		final Vetor<Associacao<C, V>> lista = this.tabela.obtem(indice);

		for (int i = 0; i < lista.tamanho(); i++) {
			final Associacao<C, V> associacao = lista.obtem(i);
			if (associacao.getChave().equals(chave)) {
				return associacao.getValor();
			}
		}

		throw new IllegalArgumentException("Não existe valor com esta chave.");
	}

	public boolean contem(C chave) {
		final int indice = this.calculaIndiceDaTabela(chave);

		final Vetor<Associacao<C, V>> lista = this.tabela.obtem(indice);
		for (int i = 0; i < lista.tamanho(); i++) {
			final Associacao<C, V> associacao = lista.obtem(i);
			if (associacao.getChave().equals(chave)) {
				return true;
			}
		}
		return false;
	}

	public int tamanho() {
		return this.quantidade;
	}
	
	public void imprimir() {

		System.out.println("CAPACIDADE: " + this.tabela.tamanho());

		for (int i = 0; i < this.tabela.tamanho(); i++) {
			final Vetor<Associacao<C, V>> objetos = this.tabela.obtem(i);

			if (objetos.tamanho() == 0)
				continue;

			System.out.println("CODIGO: " + i + " --- TOTAL: " + objetos.tamanho());

			for (int j = 0; j < objetos.tamanho(); j++) {
				System.out.println(objetos.obtem(j));
			}
		}
	}
}
