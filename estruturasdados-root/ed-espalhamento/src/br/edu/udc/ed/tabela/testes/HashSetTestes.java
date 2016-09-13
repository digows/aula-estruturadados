package br.edu.udc.ed.tabela.testes;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.tabela.veiculo.Veiculo;

public class HashSetTestes {

	@Test
	public void adicionaDevePassar() {
		final Set<String> palavras = new HashSet<>();
		palavras.add("UDC");
		palavras.add("MONJOLO");
		palavras.add("MINHA CHAVE");
		palavras.add("CARRO");
		
		Assert.assertTrue( palavras.contains("UDC") );
		Assert.assertEquals( 4, palavras.size() );
		
		final Object[] palavrasArray = palavras.toArray();
		for (int i = 0; i < palavrasArray.length; i++) {
			System.out.println( palavrasArray[i] );
		}
		
		for ( String palavra : palavras ) {
			System.out.println(palavra);
		}
		
		palavras.forEach( palavra -> {
			System.out.println(palavra);
		});
		
		palavras.stream().forEach( palavra -> {
			System.out.println(palavra);
		});
	}
	
	@Test
	public void adicionaOMesmo() {
		final Set<Veiculo> veiculos = new HashSet<>();
		veiculos.add( new Veiculo() );
		veiculos.add( new Veiculo() );
		veiculos.add( new Veiculo() );
		veiculos.add( new Veiculo() );
		
		final Veiculo c4 = new Veiculo();
		veiculos.add( c4 );
		
		Assert.assertEquals( 1 , veiculos.size() );
	}
}
