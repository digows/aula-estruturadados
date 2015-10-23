package br.edu.udc.ed.mapas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapaGenerico<C, V> {

	private List<List<Associacao<C, V>>> tabela = new ArrayList<>();

	private int quantidade = 0;

	public MapaGenerico() {
		for (int i = 0; i < 100; i++) {
			this.tabela.add(new LinkedList<Associacao<C, V>>());
		}
	}
	
	private void redimensionaTabela(int novaCapacidade) {
		final List<Associacao<C,V>> associacoes = new ArrayList<>();
		for ( List<Associacao<C,V>> associacoesTabela : this.tabela ) {
			associacoes.addAll(associacoesTabela);
		}

		// remove todas as listas de associacoes da tabela;
		this.tabela.clear();

		for (int i = 0; i < novaCapacidade; i++) {
			this.tabela.add( new LinkedList<Associacao<C,V>>() );
		}
		
		for ( Associacao<C,V> associacao : associacoes ) {
			final int indice = this.calculaIndiceDaTabela( associacao.getChave() );
			this.tabela.get(indice).add(associacao);
		}
	}

	private void verificaCarga() {
		final int capacidade = this.tabela.size();
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
		return Math.abs(chave.hashCode()) % this.tabela.size();
	}

	public void adiciona(C chave, V valor) {
		if (this.contem(chave)) {
			this.remove(chave);
		}
		this.verificaCarga();
		final int indice = this.calculaIndiceDaTabela(chave);
		final List<Associacao<C, V>> lista = this.tabela.get(indice);
		lista.add(new Associacao<>(chave, valor));
		this.quantidade++;
	}

	public void remove(C chave) {
		final int indice = this.calculaIndiceDaTabela(chave);

		final List<Associacao<C, V>> lista = this.tabela.get(indice);
		for (int i = 0; i < lista.size(); i++) {
			final Associacao<C, V> associacao = lista.get(i);
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
		final List<Associacao<C, V>> lista = this.tabela.get(indice);

		for (int i = 0; i < lista.size(); i++) {
			final Associacao<C, V> associacao = lista.get(i);
			if (associacao.getChave().equals(chave)) {
				return associacao.getValor();
			}
		}

		throw new IllegalArgumentException("Não existe valor com esta chave.");
	}

	public boolean contem(C chave) {
		final int indice = this.calculaIndiceDaTabela(chave);

		final List<Associacao<C, V>> lista = this.tabela.get(indice);
		for (int i = 0; i < lista.size(); i++) {
			final Associacao<C, V> associacao = lista.get(i);
			if (associacao.getChave().equals(chave)) {
				return true;
			}
		}
		return false;
	}

	public int tamanho() {
		return this.quantidade;
	}
	
	@Override
	public String toString(){
		final StringBuffer string = new StringBuffer();
		for ( List<Associacao<C, V>> associacoes : tabela ) {
			string.append( associacoes );
			string.append( "\n" );
		}
		
		final int capacidade = this.tabela.size();
		final double carga = (double) this.quantidade / capacidade;
		string.append("\nCAPACIDADE: "+capacidade);
		string.append("\nCARGA: "+carga);
		
		return string.toString();
	}
}
