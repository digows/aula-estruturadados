package br.edu.udc.ed.espalhamento.testes;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class HashSetTestes {

	@Test
	public void adicionaDevePassar() {
		final Set<String> palavras = new HashSet<>();
		palavras.add( "UDC" );
		
		palavras.forEach( (palavra) -> {
			System.out.println( palavra );
		});
		
		Assert.assertEquals( 1, palavras.size() );
	}
}
