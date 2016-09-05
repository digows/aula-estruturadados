package br.edu.udc.ed.espalhamento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dicionario {
	
	//Tabela bidimensional de chaves X palavras
	private ArrayList<LinkedList<String>> tabela = 
						new ArrayList<LinkedList<String>>();
	
	//Constante com a quantidade de letras
	private final int QUANTIDADE_LETRAS = 10;
	
	private int quantidade = 0;
	
	//Construtor da classe Dicionario
    public Dicionario() {
        for ( int i = 0; i<QUANTIDADE_LETRAS; i++ ) {
            final LinkedList<String> lista = new LinkedList<String>();
            tabela.add(lista);
        }
    }
    
    private void redimensionaTabela( int novaCapacidade ) {
        final List<String> palavras = this.todas();
        this.tabela.clear();// remove todas as listas da tabela;
        
        for (int i = 0; i < novaCapacidade; i++) {
            this.tabela.add( new LinkedList<String>() );
        }
        for (String palavra : palavras) {
			final int indice = this.calculaIndice(palavra);
	        this.tabela.get(indice).add(palavra);
        } 
    }
    
    private void verificaCarga() {
        int capacidade = this.tabela.size();
        double carga = (double) this.quantidade / capacidade;
        
        if (carga > 0.75) {
            this.redimensionaTabela(capacidade * 2);
        } else if (carga < 0.25) {
        		final int novaCapacidade = capacidade / 2;
        		if ( novaCapacidade < QUANTIDADE_LETRAS ) {
        			this.redimensionaTabela( novaCapacidade );
        		}
        } 
        //Se a carga for >=0.25 && <= 0.75 não preciamos mexer;
    }
    
    public int gerarCodigo( String palavra ){
        int codigo = 1;
        for (int i = 0; i < palavra.length(); i++) {
            codigo = 31 * codigo + palavra.charAt(i);
        }
        return codigo;
    }
    
    public int calculaIndice( String palavra ){
    		int codigoDeEspalhamento = this.gerarCodigo(palavra);
    		codigoDeEspalhamento = Math.abs(codigoDeEspalhamento);
    		//Devolve um número no intervalo fechado [0, this.tabela.size() - 1]
        return codigoDeEspalhamento % this.tabela.size();
    }
    
    public void adiciona(String palavra) {
    		if ( !this.contem(palavra) ) {
    			this.verificaCarga();
    			
    			final int indice = this.calculaIndice(palavra);
    	        this.tabela.get(indice).add(palavra);
    	        this.quantidade++;
    		}
    }

    public void remove(String palavra) {
        if ( this.contem(palavra) ) {
        		final int indice = this.calculaIndice(palavra);
            this.tabela.get(indice).remove(palavra);
            this.quantidade--;
            
            this.verificaCarga();
        } else {
        		throw new RuntimeException("Palavra "+palavra+" não existente.");
        }
    }

    public boolean contem( String palavra ) {
    		final int indice = this.calculaIndice(palavra);
        return this.tabela.get(indice).contains(palavra);
    }

	public List<String> todas() {
		final List<String> palavras = new ArrayList<String>();
	    for (int i = 0; i < this.tabela.size(); i++) {
	        palavras.addAll( this.tabela.get(i) );
	    }
	    return palavras;
	}

	public int tamanho() {
		return this.quantidade;
	}

	public void renderiza() {
		for (int i = 0; i < this.tabela.size(); i++) {
			System.out.println( this.tabela.get(i) );
		}
	}
}