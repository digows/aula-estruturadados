package br.edu.udc.ed.listas.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.listas.Lista;

public class ListaTestes {

	@Test
	public void testCasting2() {
		final Lista objetos = new Lista();
		objetos.adiciona( new Double(1.0) );
		objetos.adiciona(2);
		objetos.adiciona("oi");
		final Double jose = (Double) objetos.obtem(0);
		System.out.println( jose );
	}
	
	@Test
	public void testCasting() {
		final Lista objetos = new Lista();
		objetos.adiciona("José");
		final String jose = (String) objetos.obtem(0);
		Assert.assertTrue( "O valor da lista está inválido", jose.equals("José") );
	}
	
	@Test
	public void testeAdicionaDevePassar() {
		final String jose = "José";
		final String joao = "João";
        
        final Lista objetos = new Lista();
        objetos.adiciona(jose);
        objetos.adiciona(joao);
        
        Assert.assertTrue( "O tamanho do Vetor está inválido",  objetos.tamanho() == 2 );
	}
	
	@Test
	public void testeAdicionaNaPosicaoDevePassar() {
		final String jose = "José";
		String joao = "João";
		
		final Lista objetos = new Lista();
		objetos.adiciona(jose);
		objetos.adiciona(0, joao);
		
		joao = (String) objetos.obtem(0);
		
		Assert.assertTrue( "O tamanho do Vetor está inválido",  objetos.tamanho() == 2 );
		Assert.assertTrue( "O aluno está com a posição inválida", joao.equals("João")  );
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testeAdicionaNaPosicaoDeveFalhar() {
		final String jose = "José";
		
		final Lista objetos = new Lista();
		objetos.adiciona(10, jose);
		
		Assert.fail("Não deveria passar por estar inserindo em uma posição inválida.");
	}
	
	@Test
	public void testeLimiteDoVetor() {
		final Lista objetos = new Lista();
		for (int i = 0; i<101; i++) {
			objetos.adiciona("Maria");
		}
	}
}
