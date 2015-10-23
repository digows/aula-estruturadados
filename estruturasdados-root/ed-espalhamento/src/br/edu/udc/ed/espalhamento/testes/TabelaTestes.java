package br.edu.udc.ed.espalhamento.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.espalhamento.Tabela;
import br.edu.udc.ed.espalhamento.Veiculo;

public class TabelaTestes {
	
	@Test
	public void adicionaVeiculoDevePassar() {
		final Tabela veiculos = new Tabela();
		veiculos.adiciona( new Veiculo() );
		veiculos.adiciona( new Veiculo() );
		veiculos.adiciona( new Veiculo() );
		
		System.out.println( veiculos.todas() );
		
		Assert.assertEquals(3, veiculos.tamanho());
	}
	
	@Test
	public void renderizaTabela(){
		final Tabela tabela = new Tabela();
		
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
		final Tabela tabela = new Tabela();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");

		Assert.assertTrue( tabela.contem("UDC") );
		Assert.assertTrue( tabela.contem("Anglo") );
		Assert.assertTrue( tabela.contem("Alunos") );
	}
	
	@Test
	public void adicionaDevePassar2(){
		final Tabela tabela = new Tabela();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");
		
		Assert.assertEquals( tabela.tamanho(), 3 );
	}
	
	@Test
	public void removeDevePassar(){
		final Tabela tabela = new Tabela();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");
		
		tabela.remove("Alunos");
		Assert.assertEquals( tabela.tamanho(), 2 );
	}

	@Test
	public void removeDevePassar2(){
		final Tabela tabela = new Tabela();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");
		
		tabela.remove("Alunos");
		//garante que NAO contém a palavra "Alunos"
		Assert.assertFalse( tabela.contem("Alunos") );
	}
	
	@Test
	public void todasDevePassar(){
		final Tabela tabela = new Tabela();
		tabela.adiciona("UDC");
		tabela.adiciona("Anglo");
		tabela.adiciona("Alunos");

		final List<Object> palavras = tabela.todas();

		Assert.assertTrue( palavras.contains("UDC") ); 
		Assert.assertTrue( palavras.contains("Anglo") ); 
		Assert.assertTrue( palavras.contains("Alunos") ); 
		Assert.assertEquals( palavras.size(), 3 );
	}
}
