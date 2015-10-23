package br.edu.udc.ed.mapas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapaTabelaHerois {

	private List<List<AssociacaoHeroi>> tabela = new ArrayList<>();
	
	private int quantidade = 0;

	public MapaTabelaHerois() {
		for (int i = 0; i < 100; i++) {
			this.tabela.add(new LinkedList<AssociacaoHeroi>());
		}
	}
	
    private void redimensionaTabela( int novaCapacidade ) {
    		final List<AssociacaoHeroi> associacoes = new ArrayList<>();
    		for ( List<AssociacaoHeroi> associacoesTabela : this.tabela ) {
    			associacoes.addAll(associacoesTabela);
    		}
        
        this.tabela.clear();// remove todas as listas da tabela;
        
        for (int i = 0; i < novaCapacidade; i++) {
            this.tabela.add( new LinkedList<AssociacaoHeroi>() );
        }
        for ( AssociacaoHeroi associacao : associacoes ) {
            final int indice = this.calculaIndiceDaTabela(associacao.getQRCode());
	        this.tabela.get(indice).add(associacao);
        } 
    }
    
    private void verificaCarga() {
    		final int capacidade = this.tabela.size();
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
		return Math.abs(qrcode.hashCode()) % this.tabela.size();
	}
	
	public void adiciona(String qrcode, Heroi heroi) {
	    if (this.contem(qrcode)) {
	        this.remove(qrcode);
	    }
	    this.verificaCarga();
	    final int indice = this.calculaIndiceDaTabela(qrcode);
	    final List<AssociacaoHeroi> lista = this.tabela.get(indice);
	    lista.add(new AssociacaoHeroi(qrcode, heroi));
	    this.quantidade++;
	}

	public void remove(String qrcode) {
		final int indice = this.calculaIndiceDaTabela(qrcode);
		
		final List<AssociacaoHeroi> lista = this.tabela.get(indice);
		for ( int i = 0; i<lista.size(); i++ ) {
			final AssociacaoHeroi associacao = lista.get(i);
			if (associacao.getQRCode().equals(qrcode)) {
				lista.remove(i);
				this.quantidade--;
				this.verificaCarga();
				return;
			}
		}
		throw new IllegalArgumentException("Não existe herói com este QRCode");
	}

	public Heroi obtem( String qrcode ) {
	    final int indice = this.calculaIndiceDaTabela(qrcode);
	    final List<AssociacaoHeroi> lista = this.tabela.get(indice);
	    
	    for (int i = 0; i < lista.size(); i++) {
	    		final AssociacaoHeroi associacao = lista.get(i);
	        if (associacao.getQRCode().equals(qrcode)) {
	            return associacao.getHeroi();
	        }
	    }
	    
	    throw new IllegalArgumentException("Não existe herói com este qrcode.");
	}

	public boolean contem(String qrcode) {
		final int indice = this.calculaIndiceDaTabela(qrcode);

		final List<AssociacaoHeroi> lista = this.tabela.get(indice);
		for (int i = 0; i < lista.size(); i++) {
			final AssociacaoHeroi associacao = lista.get(i);
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
