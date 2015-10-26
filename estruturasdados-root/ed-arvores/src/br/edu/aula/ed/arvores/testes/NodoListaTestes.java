package br.edu.aula.ed.arvores.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.aula.ed.arvores.Nodo;
import br.edu.aula.ed.arvores.lista.NodoLista;

public class NodoListaTestes {

	@Test
	public void raizDevePassar() {
		final Nodo<String> arvore = new NodoLista<>("Raiz");
		
		Assert.assertNotNull( "A raiz está nula", arvore.getRaiz() );
		Assert.assertEquals( 1, arvore.tamanhoArvore() );
		Assert.assertNotNull( "O elemento da raiz está nulo", arvore.getRaiz().getElemento() );
		Assert.assertEquals( "A raiz deveria ser 'Raiz'", arvore.getRaiz().getElemento(), "Raiz" );
		Assert.assertTrue( "Deveria ser uma raiz.", arvore.getRaiz().raiz() );
		Assert.assertEquals( 0, arvore.getRaiz().grau() );
		Assert.assertEquals( 0, arvore.getRaiz().profundidade() );
		Assert.assertEquals( 0, arvore.getRaiz().altura() );
		Assert.assertTrue( "Uma raiz sem filhos deve ser externa.", arvore.getRaiz().externo() );
		Assert.assertFalse( "Uma raiz sem filhos não é interna.", arvore.getRaiz().interno() );
	}
	
	@Test
	public void adicionaUmNodoDevePassar() {
		final Nodo<String> arvore = new NodoLista<>("Raiz");
		
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
	
	/**
	 * 	{Alfabeto,
	 *		{A, 
	 *			{B}, 
	 *			{C, 
	 *				{D}, 
	 *				{F},
	 *				{G},
	 *			}
	 *		}
	 *	}
	 */
	@Test
	public void adicionaVariosNodosDevePassar() {
		final Nodo<String> texto = new NodoLista<>("Alfabeto");
		texto.getRaiz()
			.adicionar("A")
					.adicionar("B")
				.getPai()
					.adicionar("C")
							.adicionar("D")
						.getPai()
							.adicionar("F")
						.getPai()
							.adicionar("G");

		Assert.assertEquals( 7, texto.tamanhoArvore() );
		Assert.assertEquals( 1, texto.getRaiz().grau() );
		Assert.assertEquals( 0, texto.getRaiz().profundidade() );
		Assert.assertEquals( 3, texto.getRaiz().altura() );
		Assert.assertFalse( texto.getRaiz().externo() );
		Assert.assertTrue( texto.getRaiz().interno() );
		
		System.out.println( texto );
	}
	
	@Test
	public void formaArestaDevePassar() {
		final Nodo<String> texto = new NodoLista<>("Alfabeto");
		final Nodo<String> a = texto.adicionar("A");
		final Nodo<String> b = a.adicionar("B");
		final Nodo<String> c = b.adicionar("C");
		final Nodo<String> d = c.adicionar("D");
		
		Assert.assertTrue( a.formaArestaCom(b) );
		Assert.assertTrue( b.formaArestaCom(c) );
		Assert.assertTrue( d.formaArestaCom(c) );
		Assert.assertTrue( c.formaArestaCom(b) );
		Assert.assertTrue( b.formaArestaCom(a) );
		
		Assert.assertFalse( a.formaArestaCom(d) );
		Assert.assertFalse( a.formaArestaCom(c) );
	}
	
	@Test
	public void irmaoDeDevePassar() {
		final Nodo<String> texto = new NodoLista<>("Alfabeto");
		final Nodo<String> a = texto.adicionar("A");
		final Nodo<String> a1 = a.adicionar("A1");
		final Nodo<String> a2 = a.adicionar("A2");
		final Nodo<String> a3 = a.adicionar("A3");
		final Nodo<String> b = texto.adicionar("B");
		final Nodo<String> b1 = b.adicionar("B1");
		
		Assert.assertTrue( a.irmaoDe(b) );
		Assert.assertTrue( a1.irmaoDe(a2) );
		Assert.assertTrue( a3.irmaoDe(a2) );
		
		Assert.assertFalse( a.irmaoDe(a1) );
		Assert.assertFalse( a1.irmaoDe(b1) );
	}
	
	@Test
	public void descendenteDeDevePassar() {
		final Nodo<String> texto = new NodoLista<>("Alfabeto");
		final Nodo<String> a = texto.adicionar("A");
		final Nodo<String> a1 = a.adicionar("A1");
		final Nodo<String> a2 = a.adicionar("A2");
		final Nodo<String> a3 = a.adicionar("A3");
		final Nodo<String> a31 = a3.adicionar("A31");
		final Nodo<String> a32 = a3.adicionar("A32");
		final Nodo<String> a33 = a3.adicionar("A33");
		
		final Nodo<String> b = texto.adicionar("B");
		final Nodo<String> b1 = b.adicionar("B1");
		final Nodo<String> b2 = b.adicionar("B2");
		final Nodo<String> b21 = b2.adicionar("B21");
		final Nodo<String> b22 = b2.adicionar("B22");
		final Nodo<String> b23 = b2.adicionar("B23");
		
		Assert.assertTrue( b23.descendenteDe(b) );
		Assert.assertTrue( b23.descendenteDe(b2) );
		Assert.assertTrue( b23.descendenteDe(texto) );
		
		Assert.assertTrue( a33.descendenteDe(a3) );
		Assert.assertTrue( a33.descendenteDe(a) );
		
		Assert.assertFalse( b23.descendenteDe(a33) );
		Assert.assertFalse( b23.descendenteDe(a3) );
		Assert.assertFalse( b23.descendenteDe(a) );
		
		Assert.assertFalse( a33.descendenteDe(b22) );
		Assert.assertFalse( a32.descendenteDe(b1) );
		Assert.assertFalse( a32.descendenteDe(b) );
		Assert.assertFalse( a2.descendenteDe(b21) );
		Assert.assertFalse( a33.descendenteDe(a1) );
		Assert.assertFalse( texto.descendenteDe(a31) );
	}
	
	@Test
	public void ancestralDeDevePassar() {
		final Nodo<String> texto = new NodoLista<>("Alfabeto");
		final Nodo<String> a = texto.adicionar("A");
		final Nodo<String> a1 = a.adicionar("A1");
		final Nodo<String> a2 = a.adicionar("A2");
		final Nodo<String> a3 = a.adicionar("A3");
		final Nodo<String> a31 = a3.adicionar("A31");
		final Nodo<String> a32 = a3.adicionar("A32");
		final Nodo<String> a33 = a3.adicionar("A33");
		
		final Nodo<String> b = texto.adicionar("B");
		final Nodo<String> b1 = b.adicionar("B1");
		final Nodo<String> b2 = b.adicionar("B2");
		final Nodo<String> b21 = b2.adicionar("B21");
		final Nodo<String> b22 = b2.adicionar("B22");
		final Nodo<String> b23 = b2.adicionar("B23");
		
		Assert.assertTrue( a3.ancestralDe(a31) );
		
		Assert.assertTrue( texto.ancestralDe(a33) );
		Assert.assertTrue( texto.ancestralDe(b23) );
		Assert.assertTrue( texto.ancestralDe(a1) );
		Assert.assertTrue( texto.ancestralDe(b1) );
		
		Assert.assertTrue( a.ancestralDe(a31) );
		Assert.assertTrue( a.ancestralDe(a32) );
		Assert.assertTrue( a.ancestralDe(a33) );
		Assert.assertTrue( a.ancestralDe(a1) );
		Assert.assertTrue( a.ancestralDe(a2) );
		
		Assert.assertTrue( b.ancestralDe(b21) );
		Assert.assertTrue( b.ancestralDe(b23) );
		Assert.assertTrue( b.ancestralDe(b22) );
		Assert.assertTrue( b.ancestralDe(b1) );
		
		Assert.assertFalse( a1.ancestralDe(a2) );
		Assert.assertFalse( b1.ancestralDe(b2) );
		Assert.assertFalse( a33.ancestralDe(b23) );
	}
}
