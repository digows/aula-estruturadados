package br.edu.udc.ed.tabela.veiculo.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.tabela.veiculo.Veiculo;

public class VeiculoTestes {

	@Test
	public void testHashCode() {
		System.out.println( new Veiculo().hashCode() );
		System.out.println( new Veiculo().hashCode() );
		System.out.println( new Veiculo().hashCode() );
		System.out.println( new Veiculo().hashCode() );
		System.out.println( new Veiculo().hashCode() );
		System.out.println( new Veiculo().hashCode() );
	}
	
	@Test
	public void testHashCodeDeveSerIgual() {
		final Veiculo veiculo1 = new Veiculo();
		veiculo1.setCor("azul");
		veiculo1.setConversivel(false);
		
		final Veiculo veiculo2 = new Veiculo();
		veiculo2.setCor("azul");
		veiculo2.setConversivel(false);
		
		Assert.assertEquals(veiculo1.hashCode(), veiculo2.hashCode());
	}
	
	@Test
	public void testImpressaoHashCode() {
		final Veiculo veiculo1 = new Veiculo();
		veiculo1.setCor("azul");
		veiculo1.setConversivel(false);
		veiculo1.setModelo("Brasilia");
		System.out.println(veiculo1);
		
		final Veiculo veiculo2 = new Veiculo();
		veiculo2.setCor("azul");
		veiculo2.setConversivel(false);
		veiculo2.setModelo("Fusca");
		System.out.println(veiculo2);
		
		Assert.assertNotEquals(veiculo1.hashCode(), veiculo2.hashCode());
	}
}
