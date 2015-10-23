package br.edu.udc.ed.mapas;

import java.util.ArrayList;
import java.util.List;

public class MapaListaHerois {

	// TODO se a chave já existisse, trocamos o valor associado para este novo
	// TODO Melhorar o desempenho do remove

	// Carro. A API do Java trata isso desta maneira.
	private List<AssociacaoHeroi> associacoes = new ArrayList<AssociacaoHeroi>();

	public void adiciona(String qrcode, Heroi heroi) {
		if (!this.contem(qrcode)) {
			final AssociacaoHeroi associacao = new AssociacaoHeroi(qrcode, heroi);
			this.associacoes.add(associacao);
		}
	}

	public void remove(String qrcode) {

		for ( int i = 0; i < this.associacoes.size(); i++ ) {
			final AssociacaoHeroi associacao = this.associacoes.get(i);
			if (qrcode.equals(associacao.getQRCode())) {
				this.associacoes.remove(i);
				return;
			}
		}
		
		throw new IllegalArgumentException("Chave não existente");
	}

	public Heroi obtem(String qrcode) {
		for (AssociacaoHeroi associacao : this.associacoes) {
			if (qrcode.equals(associacao.getQRCode())) {
				return associacao.getHeroi();
			}
		}
		throw new IllegalArgumentException("Chave não existente");
	}

	public boolean contem(String qrcode) {
		for ( AssociacaoHeroi associacao : this.associacoes ) {
			if ( qrcode.equals(associacao.getQRCode()) ) {
				return true;
			}
		}
		return false;
	}

	public int tamanho() {
		return this.associacoes.size();
	}
}