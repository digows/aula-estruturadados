package br.edu.aula.ed.arvores.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.aula.ed.arvores.Nodo;
import br.edu.aula.ed.arvores.encadeada.NodoSequencial;

public class NodoEncadeadoTestes {

	@Test
	public void raizDevePassar() {
		final Nodo<String> arvore = new NodoSequencial<>("Raiz");
		
		Assert.assertNotNull( "A raiz está nula", arvore.getRaiz() );
		Assert.assertEquals( 1, arvore.tamanhoArvore() );
		Assert.assertNotNull( "O elemento da raiz está nulo", arvore.getRaiz().getElemento() );
		Assert.assertEquals( "A raiz deveria ser 'Raiz'", arvore.getRaiz().getElemento(), "Raiz" );
		Assert.assertTrue( "Deveria ser uma raiz.", arvore.getRaiz().raiz() );
		Assert.assertNull( "Uma raiz não pode ter pai", arvore.getRaiz().getPai() );
		Assert.assertEquals( 0, arvore.getRaiz().grau() );
		Assert.assertEquals( 0, arvore.getRaiz().profundidade() );
		Assert.assertEquals( 0, arvore.getRaiz().altura() );
		Assert.assertTrue( "Uma raiz sem filhos deve ser externa.", arvore.getRaiz().externo() );
		Assert.assertFalse( "Uma raiz sem filhos não é interna.", arvore.getRaiz().interno() );
	}
	
	@Test
	public void adicionaUmNodoDevePassar() {
		final Nodo<String> arvore = new NodoSequencial<>("Raiz");
		
		final Nodo<String> nodoFilho = arvore.getRaiz().adicionar("Filho");

		Assert.assertNotNull( nodoFilho );
		
		Assert.assertEquals( 2, arvore.tamanhoArvore() );
		Assert.assertEquals( 1, arvore.getRaiz().getFilhos().size() );
		Assert.assertTrue( arvore.getRaiz().getFilhos().contains(nodoFilho) );
		
		Assert.assertEquals( "Filho", nodoFilho.getElemento() );
		Assert.assertNotNull( nodoFilho.getPai() );
		Assert.assertEquals( arvore.getRaiz(), nodoFilho.getPai() );
		
		Assert.assertEquals( 1, arvore.getRaiz().grau() );
		Assert.assertEquals( 0, arvore.getRaiz().profundidade() );
		Assert.assertEquals( 1, arvore.getRaiz().altura() );
		Assert.assertFalse( arvore.getRaiz().externo() );
		Assert.assertTrue( arvore.getRaiz().interno() );
		
		Assert.assertEquals( 0, nodoFilho.grau() );
		Assert.assertEquals( 1, nodoFilho.profundidade() );
		Assert.assertEquals( 0, nodoFilho.altura() );
		Assert.assertTrue( nodoFilho.externo() );
		Assert.assertFalse( nodoFilho.interno() );
	}
		
	@Test
	public void adicionaVariosNodosDevePassar() {
		final Nodo<String> texto = new NodoSequencial<>("Alfabeto");
//		{Alfabeto,
//			{A, 
//				{B}, 
//				{C, 
//					{D}, 
//					{F}
//				}
//			}
//		}
		texto.getRaiz()
			.adicionar("A")
					.adicionar("B")
				.getPai()
					.adicionar("C")
							.adicionar("D")
						.getPai()
							.adicionar("F");

		Assert.assertEquals( 6, texto.tamanhoArvore() );
		Assert.assertEquals( 1, texto.getRaiz().grau() );
		Assert.assertEquals( 0, texto.getRaiz().profundidade() );
		Assert.assertEquals( 3, texto.getRaiz().altura() );
		Assert.assertFalse( texto.getRaiz().externo() );
		Assert.assertTrue( texto.getRaiz().interno() );
	}
}
