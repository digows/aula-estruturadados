package br.edu.udc.ed.mapa.heroi;

import br.edu.udc.ed.vetor.Vetor;

public class MapaListaHeroi {

	// TODO se a chave já existisse, trocamos o valor associado para este novo

	private Vetor<AssociacaoHeroi> associacoes = new Vetor<>();

	public void adiciona(String qrcode, Heroi heroi) {
		if (!this.contem(qrcode)) {
			final AssociacaoHeroi associacao = new AssociacaoHeroi(qrcode, heroi);
			this.associacoes.adiciona(associacao);
		}
	}

	public void remove(String qrcode) {

		for ( int i = 0; i < this.associacoes.tamanho(); i++ ) {
			final AssociacaoHeroi associacao = this.associacoes.obtem(i);
			if (qrcode.equals(associacao.getQRCode())) {
				this.associacoes.remove(i);
				return;
			}
		}
		
		throw new IllegalArgumentException("Chave não existente");
	}

	public Heroi obtem(String qrcode) {
		for (int i = 0; i<this.associacoes.tamanho(); i++) {
			final AssociacaoHeroi associacao = this.associacoes.obtem(i);
			
			if ( associacao.getQRCode().equals( qrcode ) ) {
				return associacao.getHeroi();
			}
		}
		throw new IllegalArgumentException("QRCode não existente");
	}

	public boolean contem(String qrcode) {
		for (int i = 0; i < this.associacoes.tamanho(); i++) {
			final AssociacaoHeroi associacao = this.associacoes.obtem(i);
			if ( associacao.getQRCode().equals(qrcode) ) {
				return true;
			}
		}
		return false;
	}

	public int tamanho() {
		return this.associacoes.tamanho();
	}
}