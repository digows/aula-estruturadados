package br.edu.udc.ed.tabela.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.tabela.Tabela;
import br.edu.udc.ed.tabela.veiculo.Terrestre;
import br.edu.udc.ed.tabela.veiculo.Veiculo;

public class TabelaTestes {

	@Test
	public void adicionarDevePassar() {
		final Tabela<Veiculo> veiculos = new Tabela<>();
		veiculos.adiciona( new Veiculo() );
		veiculos.adiciona( new Veiculo() );
		veiculos.adiciona( new Veiculo() );
		veiculos.adiciona( new Veiculo() );
		veiculos.adiciona( new Terrestre() );
		
		final Veiculo c4 = new Veiculo();
		c4.setConversivel(false);
		c4.setCor("prata");
		c4.setModelo("C4");
		c4.setPeso(1000.2F);
		veiculos.adiciona( c4 );
		
		Assert.assertEquals(5, veiculos.tamanho());
		Assert.assertTrue( veiculos.contem(c4) );
	}
}
