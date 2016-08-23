package br.edu.udc.ed.listas.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.listas.Aluno;
import br.edu.udc.ed.listas.Alunos;

public class AlunosTestes {

	@Test
	public void testeAdicionaDevePassar() {
		final Aluno jose = new Aluno();
		jose.nome = "José";
		
		final Aluno joao = new Aluno();
        joao.nome = "João";
        
        final Alunos alunos = new Alunos();
        alunos.adiciona(jose);
        alunos.adiciona(joao);
        
        Assert.assertTrue( "O tamanho do Vetor está inválido",  alunos.tamanho() == 2 );
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testeRemoveDeveFalhar() {
		final Alunos alunos = new Alunos();
		alunos.remove(-1);
		Assert.fail("O remove nao verifica a posicao");
	}
	
	@Test
	public void testeAdicionaNaPosicaoDevePassar() {
		final Aluno jose = new Aluno();
		jose.nome = "José";
		Aluno joao = new Aluno();
		joao.nome = "João";
		
		final Alunos alunos = new Alunos();
		alunos.adiciona(jose);
		alunos.adiciona(0, joao);
		
		joao = alunos.obtem(0);
		
		Assert.assertTrue( "O tamanho do Vetor está inválido",  alunos.tamanho() == 2 );
		Assert.assertTrue( "O aluno está com a posição inválida", joao.nome.equals("João")  );
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testeAdicionaNaPosicaoDeveFalhar() {
		final Aluno jose = new Aluno();
		
		final Alunos alunos = new Alunos();
		alunos.adiciona(10, jose);
		
		Assert.fail("Não deveria passar por estar inserindo em uma posição inválida.");
	}
	
	@Test
	public void testeLimiteDoVetor() {
		final Alunos alunos = new Alunos();
		for (int i = 0; i<101; i++) {
			alunos.adiciona(new Aluno());
		}
	}
}
