package br.edu.udc.ed.vetor.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.vetor.VetorObjects;
import br.edu.udc.ed.vetor.alunos.Aluno;

public class VetorObjectsTestes {
	
	/**
	 * 
	 */
	@Test
	public void adicionaDevePassar() {
		final VetorObjects vetor = new VetorObjects();
		vetor.adiciona(new Aluno()); //aluno
		vetor.adiciona("Uma String"); //string
		vetor.adiciona(1); //int
		vetor.adiciona(1L); //long
		vetor.adiciona( System.out ); //java.io.PrintStream
		
		Assert.assertEquals( 5, vetor.tamanho() );
		System.out.println(vetor);
	}
	
	@Test
	public void adicionaDevePassarGenerico() {
		final VetorObjects vetor = new VetorObjects();
		vetor.adiciona( "String" ); //string
		vetor.adiciona( 1L ); //long
		vetor.adiciona( 'c' ); //char
		
		final String c = (String) vetor.obtem( 2 );
	}
}
