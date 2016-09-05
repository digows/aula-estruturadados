package br.edu.udc.ed.vetor.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.vetor.Vetor;

public class VetorTestes {
	
	/**
	 * 
	 */
	@Test
	public void adicionaDevePassar() {
		final Vetor<String> vetor = new Vetor<>();
		vetor.adiciona("Uma String"); //string
		vetor.adiciona("Outra String"); //string
		
		Assert.assertEquals( 2, vetor.tamanho() );
		System.out.println(vetor);
	}
	
}
