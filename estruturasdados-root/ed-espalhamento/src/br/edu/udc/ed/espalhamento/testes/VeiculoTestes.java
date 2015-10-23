package br.edu.udc.ed.espalhamento.testes;

import org.junit.Test;

import br.edu.udc.ed.espalhamento.Veiculo;

public class VeiculoTestes {

	@Test
	public void testarHashCodeNativoDevePassar() {
		final Veiculo ford = new Veiculo();
		ford.setModelo("Ford");
		
		final Veiculo gm = new Veiculo();
		gm.setModelo("GM");
		gm.setPeso(10F);
		
		final Veiculo gm1 = new Veiculo();
		gm1.setModelo("GM");
		gm1.setPeso(10F);
		
		System.out.println( gm.hashCode() );
		System.out.println( gm1.hashCode() );
		System.out.println( ford.hashCode() );
	}
}
