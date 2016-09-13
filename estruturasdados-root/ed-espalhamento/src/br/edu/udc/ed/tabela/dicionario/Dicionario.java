package br.edu.udc.ed.espalhamento.dicionario;

import br.edu.udc.ed.vetor.Vetor;

public class Dicionario {
	
	//Tabela bidimensional de chaves X palavras
	private Vetor<Vetor<String>> tabela = new Vetor<Vetor<String>>();
	
	//Constante com a quantidade de letras
	private final int QUANTIDADE_LETRAS = 10;
	
	private int quantidade = 0;
	
	//Construtor da classe Dicionario
    public Dicionario() {
        for ( int i = 0; i<QUANTIDADE_LETRAS; i++ ) {
            final Vetor<String> lista = new Vetor<String>();
            this.tabela.adiciona(lista);
        }
    }
    
    private void redimensionaTabela( int novaCapacidade ) {
        final Vetor<String> palavras = this.todas();
        this.tabela = new Vetor<>();
        
        for (int i = 0; i < novaCapacidade; i++) {
            this.tabela.adiciona( new Vetor<String>() );
        }
        
        for (int i = 0; i < palavras.tamanho(); i++) {
        		final String palavra = palavras.obtem(i);
        		final int indice = this.calculaIndice(palavra);
	        this.tabela.obtem(indice).adiciona(palavra);
		}
    }
    
    private void verificaCarga() {
        int capacidade = this.tabela.tamanho();
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
    
    private int gerarCodigo( String palavra ){
        int codigo = 1;
        for (int i = 0; i < palavra.length(); i++) {
            codigo = 31 * codigo + palavra.charAt(i);
        }
        return codigo;
    }
    
    private int calculaIndice( String palavra ){
    		int codigoDeEspalhamento = this.gerarCodigo(palavra);
    		//garante que o codigo seja sempre absoluto
    		if ( codigoDeEspalhamento < 0 ) {
    			codigoDeEspalhamento *= -1; 
    		}
    		//Devolve um número no intervalo fechado [0, this.tabela.size() - 1]
        return codigoDeEspalhamento % this.tabela.tamanho();
    }
    
    public void adiciona(String palavra) {
    		if ( !this.contem(palavra) ) {
    			this.verificaCarga();
    			
    			final int indice = this.calculaIndice(palavra);
    	        this.tabela.obtem(indice).adiciona(palavra);
    	        this.quantidade++;
    		}
    }

    public void remove(String palavra) {
        if ( this.contem(palavra) ) {
        	
        		final int indice = this.calculaIndice(palavra);
        		final Vetor<String> lista = this.tabela.obtem(indice);
        		
        		for (int i = 0; i < lista.tamanho(); i++) {
				if ( lista.obtem(i).equals(palavra) ) {
					lista.remove(i);
					break;
				}
			}
        		
            this.quantidade--;
            
            this.verificaCarga();
        } else {
        		throw new RuntimeException("Palavra "+palavra+" não existente.");
        }
    }
    
    public boolean contem( String palavra ) {
    		final int indice = this.calculaIndice(palavra);
        return this.tabela.obtem(indice).contem(palavra);
    }

	public Vetor<String> todas() {
		final Vetor<String> todasPalavras = new Vetor<String>();
		
	    for (int i = 0; i < this.tabela.tamanho(); i++) {
	    		final Vetor<String> palavras = this.tabela.obtem(i);
	    		
	    		for (int j = 0; j < palavras.tamanho(); j++) {
	    			final String palavra = palavras.obtem(j);
				todasPalavras.adiciona( palavra );
			}
	    }
	    return todasPalavras;
	}

	public int tamanho() {
		return this.quantidade;
	}

	public void imprimir() {
		
		for (int i = 0; i < this.tabela.tamanho(); i++) {
			final Vetor<String> palavras = this.tabela.obtem(i);
			System.out.println("CODIGO: "+i + " --- TOTAL: "+palavras.tamanho());
			
			for (int j = 0; j < palavras.tamanho(); j++) {
				System.out.println( palavras.obtem(j) );
			}
		}
	}
}