package br.edu.udc.ed.espalhamento.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.espalhamento.TabelaGenerica;
import br.edu.udc.ed.espalhamento.Veiculo;

public class TabelaGenericaTestes {
	
	@Test
	public void adicionaVeiculoDevePassar(){
		final TabelaGenerica<Veiculo> tabela = new TabelaGenerica<>();
		
		final Veiculo ford = new Veiculo();
		ford.setModelo("ford");
		tabela.adiciona( ford );
		
		final Veiculo fiat = new Veiculo();
		ford.setModelo("fiat");
		tabela.adiciona( fiat );

		Assert.assertTrue( tabela.contem( ford ) );
		Assert.assertTrue( tabela.contem( fiat ) );
		
		Assert.assertEquals( 2, tabela.tamanho() );
	}
	
	@Test
	public void renderizaTabelaGenerica(){
		final TabelaGenerica<String> tabela = new TabelaGenerica<>();
		
		for (int i = 0; i <1000; i++) {
			tabela.adiciona("Abacaxi"+i);
		}
		for (int i = 0; i <100; i++) {
			tabela.adiciona("Zero"+i);
		}
		
		tabela.renderiza();
		
		Assert.assertEquals( 1100, tabela.tamanho() );
	}
	
	@Test
	public void adicionaDevePassar(){
		final TabelaGenerica<String> tabela = new TabelaGenerica<>();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");

		Assert.assertTrue( tabela.contem("UDC") );
		Assert.assertTrue( tabela.contem("Anglo") );
		Assert.assertTrue( tabela.contem("Alunos") );
	}
	
	@Test
	public void adicionaDevePassar2(){
		final TabelaGenerica<String> tabela = new TabelaGenerica<>();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");
		
		Assert.assertEquals( tabela.tamanho(), 3 );
	}
	
	@Test
	public void removeDevePassar(){
		final TabelaGenerica<String> tabela = new TabelaGenerica<>();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");
		
		tabela.remove("Alunos");
		Assert.assertEquals( tabela.tamanho(), 2 );
	}

	@Test
	public void removeDevePassar2(){
		final TabelaGenerica<String> tabela = new TabelaGenerica<>();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");
		
		tabela.remove("Alunos");
		//garante que NAO contém a palavra "Alunos"
		Assert.assertFalse( tabela.contem("Alunos") );
	}
	
	@Test
	public void todasDevePassar(){
		final TabelaGenerica<String> tabela = new TabelaGenerica<>();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");

		final List<String> palavras = tabela.todas();

		Assert.assertTrue( palavras.contains("UDC") ); 
		Assert.assertTrue( palavras.contains("Anglo") ); 
		Assert.assertTrue( palavras.contains("Alunos") ); 
		Assert.assertEquals( palavras.size(), 3 );
	}
}
