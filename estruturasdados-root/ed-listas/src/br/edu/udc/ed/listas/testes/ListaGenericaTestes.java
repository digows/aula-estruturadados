package br.edu.udc.ed.listas.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.listas.Aluno;
import br.edu.udc.ed.listas.ListaGenerica;

public class ListaGenericaTestes {

	@Test
	public void testeAdicionaStringDevePassar() {
		final String jose = "José";
		final String joao = "João";
        
        final ListaGenerica<String> objetos = new ListaGenerica<>();
        objetos.adiciona(jose);
        objetos.adiciona(joao);
        
        Assert.assertTrue( "O tamanho do Vetor está inválido",  objetos.tamanho() == 2 );
	}
	
	@Test
	public void testeAdicionaAlunoDevePassar() {
		final Aluno jose = new Aluno();
		jose.nome = "José";
		final Aluno joao = new Aluno();
		jose.nome = "João";
		
		final ListaGenerica<Aluno> objetos = new ListaGenerica<Aluno>();
		objetos.adiciona(jose);
		objetos.adiciona(joao);
		
		Assert.assertTrue( "O tamanho do Vetor está inválido",  objetos.tamanho() == 2 );
		Assert.assertTrue( objetos.obtem(0) instanceof Aluno );
	}
	
	@Test
	public void testePerformance() {
		final ListaGenerica<Aluno> alunos = new ListaGenerica<Aluno>();
		
		final long agora = System.currentTimeMillis();
		for (int i = 0; i <10000000; i++) {
			alunos.adiciona( new Aluno() );
		}
		final long depois = System.currentTimeMillis();
		
		double tempo = (depois - agora) / 1000.0;
        System.out.println("Tempo em segundos = " + tempo);
	}
}
