package br.edu.udc.ed.espalhamento.testes;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.espalhamento.dicionario.Dicionario;
import br.edu.udc.ed.vetor.Vetor;

public class DicionarioTestes {
	
	@Test
	public void adicionaDevePassar() {
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("Abacaxi");
		dicionario.adiciona("Uva");
		dicionario.adiciona("Pessego");
		dicionario.adiciona("Melão");
		
		Assert.assertEquals(4, dicionario.tamanho());
		Assert.assertTrue(dicionario.contem("Abacaxi"));
		Assert.assertTrue(dicionario.contem("Uva"));
		Assert.assertTrue(dicionario.contem("Pessego"));
		Assert.assertTrue(dicionario.contem("Melão"));
	}
	
	@Test
	public void adicionaDoisIguaisDevePassar() {
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("UDC");
		dicionario.adiciona("UDC");
		dicionario.adiciona("UDC");
		dicionario.adiciona("UDC");
		dicionario.adiciona("UDC");
		dicionario.adiciona("UDC");
		dicionario.adiciona("UDC");
		
		Assert.assertEquals( 1, dicionario.tamanho() );
		Assert.assertTrue( dicionario.contem("UDC") );
	}
	
	@Test
	public void adicionaMinusculoMaiusculoDevePassar() {
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("udc");
		dicionario.adiciona("UDC");
		
		Assert.assertEquals( 2, dicionario.tamanho() );
		Assert.assertTrue( dicionario.contem("UDC") );
		Assert.assertTrue( dicionario.contem("udc") );
	}
	
	
	@Test
	public void removeDevePassar() {
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("Uva");
		dicionario.adiciona("Pessego");
		dicionario.adiciona("Banana");
		
		dicionario.remove("Uva");
		
		Assert.assertEquals( 2, dicionario.tamanho() );
		Assert.assertFalse( dicionario.contem("Uva") );
	}
	
	@Test(expected=RuntimeException.class)
	public void removeDeveFalhar() {
		final Dicionario dicionario = new Dicionario();
		dicionario.remove("NAO EXISTE");
		
		Assert.fail("Deveria ter dado um erro de nao remover.");
	}
	
	@Test(expected=RuntimeException.class)
	public void removeDeveFalharRemovendo2Vezes() {
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("Uva");
		dicionario.remove("Uva");
		dicionario.remove("Uva");
		
		Assert.fail("Deveria ter dado um erro de nao remover o segundo.");
	}
	
	@Test
	public void listarTodasDevePassar() {
		final Dicionario dicionario = new Dicionario();
		dicionario.adiciona("Uva");
		dicionario.adiciona("Abacaxi");
		dicionario.adiciona("Pera");
		dicionario.adiciona("Mamao");
		dicionario.adiciona("Melancia");
		dicionario.adiciona("Abacate");
		dicionario.adiciona("Morango");
		
		Assert.assertEquals(7, dicionario.tamanho());
		
		final Vetor<String> palavras = dicionario.todas();
		Assert.assertEquals(7, palavras.tamanho());
		
		System.out.println( palavras );
	}
	
	@Test
	public void todasDevePassarComVazio() {
		final Dicionario dicionario = new Dicionario();

		Assert.assertEquals(0, dicionario.todas().tamanho());
	}
	
	@Test
	public void balanceamento() {
		final Dicionario dicionario = new Dicionario();
		
		for (int i = 0; i < 99999; i++) {
			dicionario.adiciona( new BigInteger(40, new SecureRandom()).toString(32) );
		}
		
		dicionario.imprimir();
	}
	
	
	
//	
//	
//	@Test
//	public void gerarCodigoTeste(){
//		final Dicionario dicionario = new Dicionario();
//		
//		final int codigoAbacaxi = dicionario.gerarCodigo("Abacaxi");
//		System.out.println("Abacaxi: "+ codigoAbacaxi );
//		
//		final int codigoAmeixa = dicionario.gerarCodigo("Ameixa");
//		System.out.println("Ameixa: "+ codigoAmeixa );
//		
//		final int codigoAmorUpper = dicionario.gerarCodigo("Amor");
//		System.out.println("AmorUpper: "+codigoAmorUpper);
//		final int codigoAmorLower = dicionario.gerarCodigo("amor");
//		System.out.println("AmorLower: "+codigoAmorLower);
//		
//		final int codigoCarro = dicionario.gerarCodigo("Carro");
//		System.out.println("Carro: "+codigoCarro);
//		final int codigoCorra = dicionario.gerarCodigo("Corra");
//		System.out.println("Corra: "+codigoCorra);
//	}
//	
//	@Test
//	public void adicionaDevePassar(){
//		final Dicionario dicionario = new Dicionario();
//		dicionario.adiciona("UDC");
//		dicionario.adiciona("Anglo");
//		dicionario.adiciona("Alunos");
//
//		Assert.assertTrue( dicionario.contem("UDC") );
//		Assert.assertTrue( dicionario.contem("Anglo") );
//		Assert.assertTrue( dicionario.contem("Alunos") );
//	}
//	
//	@Test
//	public void adicionaDevePassar2(){
//		final Dicionario dicionario = new Dicionario();
//		dicionario.adiciona("UDC");
//		dicionario.adiciona("Anglo");
//		dicionario.adiciona("Alunos");
//		
//		Assert.assertEquals( 3, dicionario.tamanho() );
//	}
//	
//	@Test
//	public void removeDevePassar(){
//		final Dicionario dicionario = new Dicionario();
//		dicionario.adiciona("UDC");
//		dicionario.adiciona("Anglo");
//		dicionario.adiciona("Alunos");
//		
//		dicionario.remove("Alunos");
//		Assert.assertEquals( 2, dicionario.tamanho() );
//	}
//
//	@Test
//	public void removeDevePassar2(){
//		final Dicionario dicionario = new Dicionario();
//		dicionario.adiciona("UDC");
//		dicionario.adiciona("Anglo");
//		dicionario.adiciona("Alunos");
//		
//		dicionario.remove("Alunos");
//		//garante que NAO contém a palavra "Alunos"
//		Assert.assertFalse( dicionario.contem("Alunos") );
//	}
//	
//	@Test
//	public void todasDevePassar(){
//		final Dicionario dicionario = new Dicionario();
//		dicionario.adiciona("UDC");
//		dicionario.adiciona("Anglo");
//		dicionario.adiciona("Alunos");
//
//		final Vetor<String> palavras = dicionario.todas();
//
//		Assert.assertTrue( palavras.contem("UDC") ); 
//		Assert.assertTrue( palavras.contem("Anglo") ); 
//		Assert.assertTrue( palavras.contem("Alunos") ); 
//		Assert.assertEquals( palavras.tamanho(), 3 );
//	}
}
