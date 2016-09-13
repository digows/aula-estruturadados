package br.edu.udc.ed.tabela.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.tabela.TabelaObject;
import br.edu.udc.ed.tabela.veiculo.Veiculo;
import br.edu.udc.ed.vetor.Vetor;

public class TabelaObjectTestes {
	
	@Test
	public void adicionaDevePassar() {
		final TabelaObject tabela = new TabelaObject();
		tabela.adiciona("Uma String");
		tabela.adiciona( new Veiculo() );
		tabela.adiciona( 1 );
		tabela.adiciona( 1L );
		tabela.adiciona( 1F );
		tabela.adiciona( 1D );
		
		Assert.assertEquals(6, tabela.tamanho());
		Assert.assertTrue( tabela.contem("Uma String") );
		Assert.assertTrue( tabela.contem( new Veiculo() ) );
		Assert.assertTrue( tabela.contem( 1 ) );
		Assert.assertTrue( tabela.contem( 1L ) );
		Assert.assertTrue( tabela.contem( 1F ) );
		Assert.assertTrue( tabela.contem( 1D ) );
	}
	
	@Test
	public void testarGeneralizacao(){
		final TabelaObject tabela = new TabelaObject();
		tabela.adiciona("Uma String");
		tabela.adiciona(1D);
		
		final Vetor<Object> objetos = tabela.todas();
		for (int i = 0; i < tabela.tamanho(); i++) {
			final Object objeto = objetos.obtem(i);

			if ( objeto instanceof String ) {
				System.out.println("É uma string ->"+ objeto);
			}
			if ( objeto instanceof Double ) {
				System.out.println("É um double ->"+ objeto);
			}
		}
	}
	
}
