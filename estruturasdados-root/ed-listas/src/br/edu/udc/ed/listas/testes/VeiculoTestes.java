package br.edu.udc.ed.listas.testes;

import org.junit.Test;

import br.edu.udc.ed.listas.ListaGenerica;
import br.edu.udc.ed.listas.Veiculo;

public class VeiculoTestes {

	@Test
	public void adicionarVeiculosDevePassar(){
		final ListaGenerica<Veiculo> veiculos = new ListaGenerica<>();
		
		final long agora = System.currentTimeMillis();
		for (int i = 0; i <100000000; i++) {
			veiculos.adiciona( new Veiculo() );
		}
		final long depois = System.currentTimeMillis();
		
		System.out.println("Tempo gasto: "+ (depois-agora) );
	}

}