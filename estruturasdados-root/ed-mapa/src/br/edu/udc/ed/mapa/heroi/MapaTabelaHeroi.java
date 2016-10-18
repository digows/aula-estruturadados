package br.edu.udc.ed.mapa.heroi;

import br.edu.udc.ed.vetor.Vetor;

public class MapaTabelaHeroi {

	private Vetor<Vetor<AssociacaoHeroi>> tabela = new Vetor<>();
	
	private int quantidade = 0;

	public MapaTabelaHeroi() {
		for (int i = 0; i < 100; i++) {
			this.tabela.adiciona(new Vetor<AssociacaoHeroi>());
		}
	}
	
    private void redimensionaTabela( int novaCapacidade ) {
    		final Vetor<AssociacaoHeroi> associacoes = new Vetor<>();
    		
    		for (int i = 0; i < this.tabela.tamanho(); i++) {
			final Vetor<AssociacaoHeroi> associacoesTabela = this.tabela.obtem(i);
			associacoes.adiciona(associacoesTabela);
		}
    		
        this.tabela = new Vetor<>();// remove todas os vetores da tabela;

        //criamos a tabela com a nova capacidade
        for (int i = 0; i < novaCapacidade; i++) {
            this.tabela.adiciona( new Vetor<AssociacaoHeroi>() );
        }
        
        for (int i = 0; i < associacoes.tamanho(); i++) {
        		final AssociacaoHeroi associacao = associacoes.obtem(i);
        		final int indice = this.calculaIndiceDaTabela(associacao.getQRCode());
    	        this.tabela.obtem(indice).adiciona(associacao);
			
		}
    }
    
    private void verificaCarga() {
    		final int capacidade = this.tabela.tamanho();
    		final double carga = (double) this.quantidade / capacidade;
        
        if (carga > 0.75) {
            this.redimensionaTabela(capacidade * 2);
        } else if (carga < 0.25) {
          	//Math.max() retorna o maior número entre os 2 parâmetros
        		this.redimensionaTabela( Math.max(capacidade / 2, 10) );
        } 
        //Se a carga for >=0.25 && <= 0.75 não preciamos mexer;
    }

	private int calculaIndiceDaTabela(String qrcode) {
		return Math.abs(qrcode.hashCode()) % this.tabela.tamanho();
	}
	
	public void adiciona(String qrcode, Heroi heroi) {
	    if (this.contem(qrcode)) {
	        this.remove(qrcode);
	    }
	    this.verificaCarga();
	    final int indice = this.calculaIndiceDaTabela(qrcode);
	    final Vetor<AssociacaoHeroi> vetor = this.tabela.obtem(indice);
	    vetor.adiciona(new AssociacaoHeroi(qrcode, heroi));
	    this.quantidade++;
	}

	public void remove(String qrcode) {
		final int indice = this.calculaIndiceDaTabela(qrcode);
		
		final Vetor<AssociacaoHeroi> vetor = this.tabela.obtem(indice);
		for ( int i = 0; i<vetor.tamanho(); i++ ) {
			final AssociacaoHeroi associacao = vetor.obtem(i);
			if (associacao.getQRCode().equals(qrcode)) {
				vetor.remove(i);
				this.quantidade--;
				this.verificaCarga();
				return;
			}
		}
		throw new IllegalArgumentException("Não existe herói com este QRCode");
	}

	public Heroi obtem( String qrcode ) {
	    final int indice = this.calculaIndiceDaTabela(qrcode);
	    final Vetor<AssociacaoHeroi> vetor = this.tabela.obtem(indice);
	    
	    for (int i = 0; i < vetor.tamanho(); i++) {
	    		final AssociacaoHeroi associacao = vetor.obtem(i);
	        if (associacao.getQRCode().equals(qrcode)) {
	            return associacao.getHeroi();
	        }
	    }
	    
	    throw new IllegalArgumentException("Não existe herói com este qrcode.");
	}

	public boolean contem(String qrcode) {
		final int indice = this.calculaIndiceDaTabela(qrcode);

		final Vetor<AssociacaoHeroi> vetor = this.tabela.obtem(indice);
		for (int i = 0; i < vetor.tamanho(); i++) {
			final AssociacaoHeroi associacao = vetor.obtem(i);
			if (associacao.getQRCode().equals(qrcode)) {
				return true;
			}
		}
		return false;
	}

	public int tamanho() {
		return this.quantidade;
	}
}
