package br.edu.udc.ed.vetor.alunos.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.vetor.alunos.Aluno;
import br.edu.udc.ed.vetor.alunos.VetorAlunos;

public class VetorAlunosTest {
	
	@Test
	public void adicionaCom2Alunos() {
		final VetorAlunos vetorAlunos = new VetorAlunos();
		
		final Aluno maria = new Aluno();
		maria.nome = "Maria Joquina";
		maria.creditos = 100;
		vetorAlunos.adiciona(maria);
		
		final Aluno jose = new Aluno();
		jose.nome = "Jose Mariana";
		jose.creditos = 50;
		vetorAlunos.adiciona(jose);
		
		Assert.assertEquals(2, vetorAlunos.tamanho());
		Assert.assertTrue( vetorAlunos.contem(maria) );
		Assert.assertTrue( vetorAlunos.contem(jose) );
	}
	
	@Test
	public void equalsDoAluno() {
		final Aluno m1 = new Aluno();
		m1.nome = "Maria";
		
		System.out.println(m1);
		
		final Aluno m2 = new Aluno();
		m2.nome = "Maria";
		
		System.out.println(m2);
		
		Assert.assertEquals(m1, m2);
	}
	
	@Test
	public void equalsReferenciaEmMemoria() {
		final Aluno m1 = new Aluno();
		m1.nome = "Maria";
		
		final Aluno m2 = m1;
		
		Assert.assertTrue(m1 == m2);
		//byte
		//short
		//int
		//float
		//double
		//chat
		//boolean
	}
	
	@Test
	public void contemDevePassar() {
		final Aluno m1 = new Aluno();
		m1.nome = "Maria";
		
		final VetorAlunos vetor = new VetorAlunos();
		vetor.adiciona(m1);
		
		Assert.assertTrue( vetor.contem(m1) );
		
		final Aluno m2 = new Aluno();
		m2.nome = "Mariana";
		
		Assert.assertFalse( vetor.contem(m2) );
	}
	
	@Test
	public void obtemDevePassar() {
		final VetorAlunos vetor = new VetorAlunos();
		
		final Aluno aluno0 = new Aluno();
		aluno0.nome = "Aluno 0";
		vetor.adiciona(aluno0);
		
		final Aluno aluno1 = new Aluno();
		aluno1.nome = "Aluno 1";
		vetor.adiciona(aluno1);
		
		final Aluno aluno2 = new Aluno();
		aluno2.nome = "Aluno 2";
		vetor.adiciona(aluno2);
		
		Assert.assertEquals(aluno0, vetor.obtem(0) );
		Assert.assertEquals(aluno1, vetor.obtem(1) );
		Assert.assertEquals(aluno2, vetor.obtem(2) );
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void obtemDeveFalharComPosicaoInvalida() {
		final VetorAlunos vetor = new VetorAlunos();
		vetor.obtem(1000);
		Assert.fail("NAo deveria retornar um aluno com posicao invalida");
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void obtemDeveFalharComPosicaoNegativa() {
		final VetorAlunos vetor = new VetorAlunos();
		vetor.obtem(-100);
		Assert.fail("NAo deveria retornar um aluno com posicao negativa");
	}
	
	@Test
	public void adicionaNaPosicaoDevePassar() {
		final VetorAlunos vetor = new VetorAlunos();
		vetor.adiciona( new Aluno() );//0
		vetor.adiciona( new Aluno() );//1
		vetor.adiciona( new Aluno() );//2
		vetor.adiciona( new Aluno() );//3
		
		final Aluno aluno = new Aluno();
		aluno.nome = "aluno 4";
		vetor.adiciona(4, aluno);	//4
		
		Assert.assertEquals( aluno, vetor.obtem(4) );
		Assert.assertEquals( 5, vetor.tamanho());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void adicionaNaPosicaoInvalidaDeveFalhar() {
		final VetorAlunos vetor = new VetorAlunos();
		vetor.adiciona( new Aluno() ); //0
		vetor.adiciona( new Aluno() ); //1
		vetor.adiciona( new Aluno() ); //2
		vetor.adiciona( new Aluno() ); //3
		
		vetor.adiciona(10, new Aluno());
		
		Assert.fail("Não deveria adicionar um aluno na posicao 10.");
	}
	
	@Test
	public void adicionaNoMeioDevePassar() {
		final VetorAlunos vetor = new VetorAlunos();
		vetor.adiciona( new Aluno() ); //0
		vetor.adiciona( new Aluno() ); //1
		vetor.adiciona( new Aluno() ); //2
		vetor.adiciona( new Aluno() ); //3
		
		final Aluno aluno = new Aluno();
		aluno.nome = "Aluno na posicao 2";
		vetor.adiciona(2, aluno);
		
		Assert.assertEquals( aluno, vetor.obtem(2) );
		Assert.assertEquals( 5, vetor.tamanho() );
	}
	
	@Test
	public void removeDevePassar() {
		final VetorAlunos vetor = new VetorAlunos();
		
		vetor.adiciona( new Aluno() ); //0
		vetor.adiciona( new Aluno() ); //1
		vetor.adiciona( new Aluno() ); //2
		vetor.adiciona( new Aluno() ); //3
		vetor.adiciona( new Aluno() ); //4
		
		final Aluno aluno = new Aluno();
		aluno.nome = "Aluno a ser removido";
		vetor.adiciona( aluno ); 	//5
		
		vetor.remove(5);
		
		Assert.assertFalse( vetor.contem(aluno) );
		Assert.assertEquals( 5, vetor.tamanho() );
	}
	
	@Test
	public void removeNoMeioDevePassar() {
		final VetorAlunos vetor = new VetorAlunos();
		
		final Aluno aluno = new Aluno();
		aluno.nome = "Aluno na posicao 2";
		
		vetor.adiciona( new Aluno() ); //0
		vetor.adiciona( new Aluno() ); //1
		vetor.adiciona( aluno ); 	  //2
		vetor.adiciona( new Aluno() ); //3
		vetor.adiciona( new Aluno() ); //4
		
		vetor.remove(2);
		
		Assert.assertFalse( vetor.contem(aluno) );
		Assert.assertEquals( 4, vetor.tamanho() );
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void removeDeveFalharPosicaoInvalida(){
		final VetorAlunos vetor = new VetorAlunos();
		
		vetor.remove(100);
		
		Assert.fail("Nao deveria remover uma posicao invalida.");
	}
	
	@Test
	public void adicionaSemLimites(){
		final VetorAlunos vetor = new VetorAlunos();
		
		for (int i = 0; i < 999999; i++) {
			vetor.adiciona( new Aluno() );
		}
		
		Assert.assertEquals( 999999 , vetor.tamanho());
	}
}
