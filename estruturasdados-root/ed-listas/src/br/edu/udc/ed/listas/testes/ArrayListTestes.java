package br.edu.udc.ed.listas.testes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.listas.Aluno;

public class ArrayListTestes {

	@Test
	public void testAdiciona() {
		final List<Aluno> list = new ArrayList<Aluno>();
		
		final long agora = System.currentTimeMillis();
		list.add( new Aluno() );
		list.add( new Aluno() );
		list.add( new Aluno() );
		list.add( new Aluno() );
		list.add( new Aluno() );
		final long depois = System.currentTimeMillis();
		
		double tempo = (depois - agora) / 1000.0;
		System.out.println("Tempo em segundos = " + tempo);
		
		Assert.assertSame(list.size(), 2);
	}
}
