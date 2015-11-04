package br.edu.aula.ed.arvores.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.aula.ed.arvores.NodoAbstrato;
import br.edu.aula.ed.arvores.lista.NodoLista;

public class NodoListaTestes {

	@Test
	public void profundidadeDevePassar() {
		final NodoAbstrato<String> arvore = new NodoLista<>("0");
		final NodoAbstrato<String> nodo1 = arvore.adicionar("1");
		final NodoAbstrato<String> nodo11 = nodo1.adicionar("1.1");
		final NodoAbstrato<String> nodo111 = nodo11.adicionar("1.1.1");
		
		Assert.assertEquals( 3, nodo111.profundidade() );
	}
	
	@Test
	public void grauDevePassarComFilhos() {
		final NodoLista<String> arvore = new NodoLista<>("R");
		arvore.adicionar("Filho 1");
		arvore.adicionar("Filho 2");
		arvore.adicionar("Filho 3");
		
		Assert.assertEquals(3, arvore.grau());
	}
	
	@Test
	public void grauDevePassarSemFilhos() {
		final NodoLista<String> arvore = new NodoLista<>("R");
		Assert.assertEquals(0, arvore.grau());
	}
	
	
	@Test
	public void raizDevePassar() {
		final NodoAbstrato<String> arvore = new NodoLista<>("Raiz");
		
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
		final NodoAbstrato<String> arvore = new NodoLista<>("Raiz");
		
		final NodoAbstrato<String> nodoFilho = arvore.getRaiz().adicionar("Filho");

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
		final NodoAbstrato<String> texto = new NodoLista<>("Alfabeto");
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
		final NodoAbstrato<String> texto = new NodoLista<>("Alfabeto");
		final NodoAbstrato<String> a = texto.adicionar("A");
		final NodoAbstrato<String> b = a.adicionar("B");
		final NodoAbstrato<String> c = b.adicionar("C");
		final NodoAbstrato<String> d = c.adicionar("D");
		
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
		final NodoAbstrato<String> texto = new NodoLista<>("Alfabeto");
		final NodoAbstrato<String> a = texto.adicionar("A");
		final NodoAbstrato<String> a1 = a.adicionar("A1");
		final NodoAbstrato<String> a2 = a.adicionar("A2");
		final NodoAbstrato<String> a3 = a.adicionar("A3");
		final NodoAbstrato<String> b = texto.adicionar("B");
		final NodoAbstrato<String> b1 = b.adicionar("B1");
		
		Assert.assertTrue( a.irmaoDe(b) );
		Assert.assertTrue( a1.irmaoDe(a2) );
		Assert.assertTrue( a3.irmaoDe(a2) );
		
		Assert.assertFalse( a.irmaoDe(a1) );
		Assert.assertFalse( a1.irmaoDe(b1) );
	}
	
	@Test
	public void descendenteDeDevePassar() {
		final NodoAbstrato<String> texto = new NodoLista<>("Alfabeto");
		final NodoAbstrato<String> a = texto.adicionar("A");
		final NodoAbstrato<String> a1 = a.adicionar("A1");
		final NodoAbstrato<String> a2 = a.adicionar("A2");
		final NodoAbstrato<String> a3 = a.adicionar("A3");
		final NodoAbstrato<String> a31 = a3.adicionar("A31");
		final NodoAbstrato<String> a32 = a3.adicionar("A32");
		final NodoAbstrato<String> a33 = a3.adicionar("A33");
		
		final NodoAbstrato<String> b = texto.adicionar("B");
		final NodoAbstrato<String> b1 = b.adicionar("B1");
		final NodoAbstrato<String> b2 = b.adicionar("B2");
		final NodoAbstrato<String> b21 = b2.adicionar("B21");
		final NodoAbstrato<String> b22 = b2.adicionar("B22");
		final NodoAbstrato<String> b23 = b2.adicionar("B23");
		
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
		final NodoAbstrato<String> texto = new NodoLista<>("Alfabeto");
		final NodoAbstrato<String> a = texto.adicionar("A");
		final NodoAbstrato<String> a1 = a.adicionar("A1");
		final NodoAbstrato<String> a2 = a.adicionar("A2");
		final NodoAbstrato<String> a3 = a.adicionar("A3");
		final NodoAbstrato<String> a31 = a3.adicionar("A31");
		final NodoAbstrato<String> a32 = a3.adicionar("A32");
		final NodoAbstrato<String> a33 = a3.adicionar("A33");
		
		final NodoAbstrato<String> b = texto.adicionar("B");
		final NodoAbstrato<String> b1 = b.adicionar("B1");
		final NodoAbstrato<String> b2 = b.adicionar("B2");
		final NodoAbstrato<String> b21 = b2.adicionar("B21");
		final NodoAbstrato<String> b22 = b2.adicionar("B22");
		final NodoAbstrato<String> b23 = b2.adicionar("B23");
		
		Assert.assertTrue( a3.ancestralDe(a31) );
		
		Assert.assertTrue( texto.ancestralDe(b23) );
		Assert.assertTrue( texto.ancestralDe(a33) );
		Assert.assertTrue( texto.ancestralDe(a1) );
		Assert.assertTrue( texto.ancestralDe(a2) );
		Assert.assertTrue( texto.ancestralDe(a3) );
		Assert.assertTrue( texto.ancestralDe(b1) );
		Assert.assertTrue( texto.ancestralDe(b2) );
		
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
		Assert.assertFalse( a3.ancestralDe(b2) );
		Assert.assertFalse( b2.ancestralDe(a2) );
	}
}
