package br.edu.aula.ed.arvores.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.aula.ed.arvores.Arvore;
import br.edu.aula.ed.arvores.Nodo;
import br.edu.aula.ed.arvores.encadeada.ArvoreEncadeada;

public class ArvoreEncadeadaTestes {

	@Test
	public void raizDevePassar() {
		final Arvore<String> arvore = new ArvoreEncadeada<>("Raiz");
		
		Assert.assertNotNull( "A raiz está nula", arvore.getRaiz() );
		Assert.assertEquals( 1, arvore.tamanho() );
		Assert.assertTrue( arvore.contemNodos() );
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
	public void setRaizDevePassar() {
		final Arvore<String> arvore = new ArvoreEncadeada<>("RaizInicial");
		arvore.getRaiz().adicionar("Filho1");
		arvore.getRaiz().adicionar("Filho2");
		
		Assert.assertNotNull( "A raiz está nula", arvore.getRaiz() );
		Assert.assertEquals( 3, arvore.tamanho() );
		Assert.assertTrue( arvore.contemNodos() );
		
		arvore.setRaiz("RaizAlterada");
		
		Assert.assertNotNull( "A raiz está nula", arvore.getRaiz() );
		Assert.assertEquals( 1, arvore.tamanho() );
		Assert.assertTrue( arvore.contemNodos() );
		Assert.assertNotNull( "O elemento da raiz está nulo", arvore.getRaiz().getElemento() );
		Assert.assertEquals( "A raiz deveria ser 'RaizAlterada'", arvore.getRaiz().getElemento(), "RaizAlterada" );
		Assert.assertTrue( "Deveria ser uma raiz.", arvore.getRaiz().raiz() );
		Assert.assertNull( "Uma raiz não pode ter pai", arvore.getRaiz().getPai() );
		Assert.assertNotNull( arvore.getRaiz().getFilhos() );
		Assert.assertEquals( 0, arvore.getRaiz().getFilhos().size() );
		Assert.assertEquals( 0, arvore.getRaiz().grau() );
		Assert.assertEquals( 0, arvore.getRaiz().profundidade() );
		Assert.assertEquals( 0, arvore.getRaiz().altura() );
		Assert.assertTrue( "Uma raiz sem filhos deve ser externa.", arvore.getRaiz().externo() );
		Assert.assertFalse( "Uma raiz sem filhos não é interna.", arvore.getRaiz().interno() );
		
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void raizDeveFalhar() {
		final Arvore<String> arvore = new ArvoreEncadeada<>();
		Assert.assertNotNull( "Deveria estourar erro por nao ter raiz", arvore.getRaiz() );
	}

	
	@Test
	public void adicionaUmNodoDevePassar() {
		final Arvore<String> arvore = new ArvoreEncadeada<>("Raiz");
		
		final Nodo<String> nodoFilho = arvore.getRaiz().adicionar("Filho");

		Assert.assertNotNull( nodoFilho );
		
		Assert.assertEquals( 2, arvore.tamanho() );
		Assert.assertEquals( 1, arvore.getRaiz().getFilhos().size() );
		Assert.assertEquals( nodoFilho, arvore.getRaiz().getFilhos().get(0) );
		
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
		final Arvore<String> texto = new ArvoreEncadeada<>("Alfabeto");
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

		Assert.assertEquals( 6, texto.tamanho() );
		Assert.assertEquals( 1, texto.getRaiz().grau() );
		Assert.assertEquals( 0, texto.getRaiz().profundidade() );
		Assert.assertEquals( 3, texto.getRaiz().altura() );
		Assert.assertFalse( texto.getRaiz().externo() );
		Assert.assertTrue( texto.getRaiz().interno() );
	}
}
