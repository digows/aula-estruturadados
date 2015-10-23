package br.edu.udc.ed.espalhamento.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.espalhamento.Dicionario;

public class DicionarioTestes {
	
	@Test
	public void renderizaTabela(){
		final Dicionario dicionario = new Dicionario();
		
		for (int i = 0; i <1000; i++) {
			dicionario.adiciona("Abacaxi"+i);
		}
		for (int i = 0; i <100; i++) {
			dicionario.adiciona("Zero"+i);
		}
		
		dicionario.renderiza();
		
		Assert.assertEquals( 1100, dicionario.tamanho() );
	}
	
	@Test
	public void gerarCodigoTeste(){
		final Dicionario dicionario = new Dicionario();
		
		final int codigoAbacaxi = dicionario.gerarCodigo("Abacaxi");
		System.out.println("Abacaxi: "+ codigoAbacaxi );
		
		final int codigoAmeixa = dicionario.gerarCodigo("Ameixa");
		System.out.println("Ameixa: "+ codigoAmeixa );
		
		final int codigoAmorUpper = dicionario.gerarCodigo("Amor");
		System.out.println("AmorUpper: "+codigoAmorUpper);
		final int codigoAmorLower = dicionario.gerarCodigo("amor");
		System.out.println("AmorLower: "+codigoAmorLower);
		
		final int codigoCarro = dicionario.gerarCodigo("Carro");
		System.out.println("Carro: "+codigoCarro);
		final int codigoCorra = dicionario.gerarCodigo("Corra");
		System.out.println("Corra: "+codigoCorra);
	}
	
	@Test
	public void adicionaDevePassar(){
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("UDC");
		dicionario.adiciona("Anglo");
		dicionario.adiciona("Alunos");

		Assert.assertTrue( dicionario.contem("UDC") );
		Assert.assertTrue( dicionario.contem("Anglo") );
		Assert.assertTrue( dicionario.contem("Alunos") );
	}
	
	@Test
	public void adicionaDevePassar2(){
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("UDC");
		dicionario.adiciona("Anglo");
		dicionario.adiciona("Alunos");
		
		Assert.assertEquals( 3, dicionario.tamanho() );
	}
	
	@Test
	public void removeDevePassar(){
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("UDC");
		dicionario.adiciona("Anglo");
		dicionario.adiciona("Alunos");
		
		dicionario.remove("Alunos");
		Assert.assertEquals( 2, dicionario.tamanho() );
	}

	@Test
	public void removeDevePassar2(){
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("UDC");
		dicionario.adiciona("Anglo");
		dicionario.adiciona("Alunos");
		
		dicionario.remove("Alunos");
		//garante que NAO contém a palavra "Alunos"
		Assert.assertFalse( dicionario.contem("Alunos") );
	}
	
	@Test
	public void todasDevePassar(){
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("UDC");
		dicionario.adiciona("Anglo");
		dicionario.adiciona("Alunos");

		final List<String> palavras = dicionario.todas();

		Assert.assertTrue( palavras.contains("UDC") ); 
		Assert.assertTrue( palavras.contains("Anglo") ); 
		Assert.assertTrue( palavras.contains("Alunos") ); 
		Assert.assertEquals( palavras.size(), 3 );
	}
}
