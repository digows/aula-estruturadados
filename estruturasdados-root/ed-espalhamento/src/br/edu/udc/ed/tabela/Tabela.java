package br.edu.udc.ed.tabela;

import br.edu.udc.ed.vetor.Vetor;

public class Tabela<T> {
	
	//Tabela bidimensional de chaves X objetos
	private Vetor<Vetor<T>> tabela = new Vetor<Vetor<T>>();
	
	//Constante com a quantidade de objects
	private final short TAMANHO_MINIMO = 10;
	
	private int quantidade = 0;
	
	//Construtor da classe TabelaObject
    public Tabela() {
        for ( int i = 0; i<TAMANHO_MINIMO; i++ ) {
            final Vetor<T> lista = new Vetor<T>();
            this.tabela.adiciona(lista);
        }
    }
    
    private void redimensionaTabela( int novaCapacidade ) {
        final Vetor<T> objetos = this.todas();
        this.tabela = new Vetor<>();
        
        for (int i = 0; i < novaCapacidade; i++) {
            this.tabela.adiciona( new Vetor<T>() );
        }
        
        for (int i = 0; i < objetos.tamanho(); i++) {
        		final T objeto = objetos.obtem(i);
        		final int indice = this.calculaIndice(objeto);
	        this.tabela.obtem(indice).adiciona(objeto);
		}
    }
    
    private void verificaCarga() {
        final int capacidade = this.tabela.tamanho();
        final double carga = (double) this.quantidade / capacidade;
        
        if (carga > 0.75) {
            this.redimensionaTabela(capacidade * 2);
        } else if (carga < 0.25) {
        		final int novaCapacidade = capacidade / 2;
        		if ( novaCapacidade > TAMANHO_MINIMO ) {
        			this.redimensionaTabela( novaCapacidade );
        		}
        } 
        //Se a carga for >=0.25 && <= 0.75 não preciamos mexer;
    }
    
    private int calculaIndice( T objeto ){
    		int codigoDeEspalhamento = objeto.hashCode();

    		//garante que o codigo seja sempre absoluto
    		if ( codigoDeEspalhamento < 0 ) {
    			codigoDeEspalhamento *= -1;
    		}
    		//Devolve um número no intervalo fechado [0, this.tabela.size() - 1]
        return codigoDeEspalhamento % this.tabela.tamanho();
    }
    
    public void adiciona(T objeto) {
    		if ( !this.contem(objeto) ) {
    			this.verificaCarga();
    			
    			final int indice = this.calculaIndice(objeto);
    	        this.tabela.obtem(indice).adiciona(objeto);
    	        this.quantidade++;
    		}
    }

    public void remove(T objeto) {
        if ( this.contem(objeto) ) {
        	
        		final int indice = this.calculaIndice(objeto);
        		final Vetor<T> lista = this.tabela.obtem(indice);
        		
        		for (int i = 0; i < lista.tamanho(); i++) {
				if ( lista.obtem(i).equals(objeto) ) {
					lista.remove(i);
					break;
				}
			}
        		
            this.quantidade--;
            
            this.verificaCarga();
        } else {
        		throw new RuntimeException("Objeto "+objeto+" não existente.");
        }
    }
    
    public boolean contem( T objeto ) {
    		final int indice = this.calculaIndice(objeto);
        return this.tabela.obtem(indice).contem(objeto);
    }

	public Vetor<T> todas() {
		final Vetor<T> todosObjetos = new Vetor<T>();
		
	    for (int i = 0; i < this.tabela.tamanho(); i++) {
	    		final Vetor<T> objetos = this.tabela.obtem(i);
	    		
	    		for (int j = 0; j < objetos.tamanho(); j++) {
	    			final T objeto = objetos.obtem(j);
				todosObjetos.adiciona( objeto );
			}
	    }
	    return todosObjetos;
	}

	public int tamanho() {
		return this.quantidade;
	}

	public void imprimir() {
		
		System.out.println("CAPACIDADE: "+ this.tabela.tamanho() );
		
		for (int i = 0; i < this.tabela.tamanho(); i++) {
			final Vetor<T> objetos = this.tabela.obtem(i);
			
			if ( objetos.tamanho() == 0 ) continue;
			
			System.out.println("CODIGO: "+i + " --- TOTAL: "+objetos.tamanho());

			for (int j = 0; j < objetos.tamanho(); j++) {
				System.out.println( objetos.obtem(j) );
			}
		}
	}
}