package br.edu.udc.ed.espalhamento;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TabelaGenerica<T> {
	
	//Tabela bidimensional de chaves X elementos genericos
	private ArrayList<LinkedList<T>> tabela = 
						new ArrayList<LinkedList<T>>();
	
	//Constante com a quantidade de letras
	private final int CAPACIDADE_INICIAL = 26;
	
	private int quantidade = 0;
	
	//Construtor da classe
    public TabelaGenerica() {
        for ( int i = 0; i<CAPACIDADE_INICIAL; i++ ) {
            final LinkedList<T> lista = new LinkedList<T>();
            tabela.add(lista);
        }
    }
    
    private void redimensionaTabela( int novaCapacidade ) {
        final List<T> palavras = this.todas();
        this.tabela.clear();// remove todas as listas da tabela;
        for (int i = 0; i < novaCapacidade; i++) {
            this.tabela.add( new LinkedList<T>() );
        }
        for (T palavra : palavras) {
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
          	//Math.max() retorna o maior número entre os 2 parâmetros
        		this.redimensionaTabela( Math.max(capacidade / 2, 10) );
        } 
        //Se a carga for >=0.25 && <= 0.75 não preciamos mexer;
    }
    
    public int calculaIndice( Object elemento ){
    		int codigoDeEspalhamento = elemento.hashCode();
    		codigoDeEspalhamento = Math.abs(codigoDeEspalhamento);
    		//Devolve um número no intervalo fechado [0, this.tabela.size() - 1]
        return codigoDeEspalhamento % this.tabela.size();
    }
    
    public void adiciona(T palavra) {
    		if ( !this.contem(palavra) ) {
    			this.verificaCarga();
    			
    			final int indice = this.calculaIndice(palavra);
    	        this.tabela.get(indice).add(palavra);
    	        this.quantidade++;
    		}
    }

    public void remove(T palavra) {
        if ( this.contem(palavra) ) {
        		final int indice = this.calculaIndice(palavra);
            this.tabela.get(indice).remove(palavra);
            this.quantidade--;
            
            this.verificaCarga();
        } else {
        		throw new RuntimeException("O elemento "+palavra+" não existente.");
        }
    }

    public boolean contem( T palavra ) {
    		final int indice = this.calculaIndice(palavra);
        return this.tabela.get(indice).contains(palavra);
    }

	public List<T> todas() {
		final List<T> palavras = new ArrayList<T>();
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