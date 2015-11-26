package br.edu.aula.ed.arvores.testes;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.aula.ed.arvores.NodoAbstrato;
import br.edu.aula.ed.arvores.binaria.NodoBinarioEncadeado;

public class NodoBinarioEncadeadoTestes {
	
	@Test
	public void adicionaDevePassar() {
		final NodoBinarioEncadeado<String> organograma = new NodoBinarioEncadeado<String>("Fábio & Rosycler");
		organograma
				.adicionar("Silvia")
					.adicionar("Alessandra")
						.adicionar("Luciano")
					.getPai()
						.adicionar("Rodrigo");
		
		Assert.assertEquals( new NodoBinarioEncadeado<String>("Fábio & Rosycler"), organograma);
		Assert.assertEquals( 5, organograma.tamanhoArvore() );
		Assert.assertEquals( 3, organograma.altura() );
		
		final List<NodoAbstrato<String>> filhos = organograma.getFilhos();
		Assert.assertEquals( 1, filhos.size() );
	}
	
	/**
	 * 		     Alfabeto
	 * 		a		b		c
	 *    a1
	 *  a11 
	 */
	@Test
	public void descendenteEAscendenteDevePassar() {
		final NodoBinarioEncadeado<String> alfabeto = new NodoBinarioEncadeado<String>("Alfabeto");
		final NodoBinarioEncadeado<String> a = alfabeto.adicionar("a");
		final NodoBinarioEncadeado<String> a1 = a.adicionar("a1");
		final NodoBinarioEncadeado<String> a11 = a1.adicionar("a11");
		
		final NodoBinarioEncadeado<String> b = alfabeto.adicionar("b");
		
		Assert.assertTrue( alfabeto.ancestralDe(a11) );
		Assert.assertTrue( a.ancestralDe(a1) );
		Assert.assertTrue( a.ancestralDe(a11) );
		
		Assert.assertFalse( a11.ancestralDe(a) );
		Assert.assertFalse( b.ancestralDe(a1) );
		
		Assert.assertTrue( a1.descendenteDe(a) );
		Assert.assertTrue( b.descendenteDe(alfabeto) );
	}
	

	@Test
	public void profundidadeDevePassar() {
		final NodoBinarioEncadeado<String> arvore = new NodoBinarioEncadeado<>("0");
		final NodoBinarioEncadeado<String> nodo1 = arvore.adicionar("1");
		final NodoBinarioEncadeado<String> nodo11 = nodo1.adicionar("1.1");
		final NodoBinarioEncadeado<String> nodo111 = nodo11.adicionar("1.1.1");
		
		Assert.assertEquals( 3, nodo111.profundidade() );
	}
	
	@Test
	public void grauComFilhosDevePassar() {
		final NodoBinarioEncadeado<String> arvore = new NodoBinarioEncadeado<>("R");
		arvore.adicionar("Filho 1");
		arvore.adicionar("Filho 2");
		
		Assert.assertEquals(2, arvore.grau());
	}
	
	@Test
	public void grauSemFilhosDevePassar() {
		final NodoBinarioEncadeado<String> arvore = new NodoBinarioEncadeado<>("R");
		Assert.assertEquals(0, arvore.grau());
	}
	
	@Test
	public void raizDevePassar() {
		final NodoBinarioEncadeado<String> arvore = new NodoBinarioEncadeado<>("Raiz");
		
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
		final NodoBinarioEncadeado<String> arvore = new NodoBinarioEncadeado<>("Raiz");
		
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
		final NodoBinarioEncadeado<String> texto = new NodoBinarioEncadeado<>("Alfabeto");
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
		
		System.out.println( texto );
	}
	
	@Test
	public void formaArestaDevePassar() {
		final NodoBinarioEncadeado<String> texto = new NodoBinarioEncadeado<>("Alfabeto");
		final NodoBinarioEncadeado<String> a = texto.adicionar("A");
		final NodoBinarioEncadeado<String> b = a.adicionar("B");
		final NodoBinarioEncadeado<String> c = b.adicionar("C");
		final NodoBinarioEncadeado<String> d = c.adicionar("D");
		
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
		final NodoBinarioEncadeado<String> texto = new NodoBinarioEncadeado<>("Alfabeto");
		final NodoBinarioEncadeado<String> a = texto.adicionar("A");
		final NodoBinarioEncadeado<String> a1 = a.adicionar("A1");
		final NodoBinarioEncadeado<String> a2 = a.adicionar("A2");
		final NodoBinarioEncadeado<String> b = texto.adicionar("B");
		final NodoBinarioEncadeado<String> b1 = b.adicionar("B1");
		
		Assert.assertTrue( a.irmaoDe(b) );
		Assert.assertTrue( a1.irmaoDe(a2) );
		
		Assert.assertFalse( a.irmaoDe(a1) );
		Assert.assertFalse( a1.irmaoDe(b1) );
	}
	
	@Test
	public void descendenteDeDevePassar() {
		final NodoBinarioEncadeado<String> texto = new NodoBinarioEncadeado<>("Alfabeto");
		final NodoBinarioEncadeado<String> a = texto.adicionar("A");
		final NodoBinarioEncadeado<String> a1 = a.adicionar("A1");
		final NodoBinarioEncadeado<String> a3 = a.adicionar("A3");
		final NodoBinarioEncadeado<String> a31 = a3.adicionar("A31");
		final NodoBinarioEncadeado<String> a32 = a3.adicionar("A32");
		
		final NodoBinarioEncadeado<String> b = texto.adicionar("B");
		final NodoBinarioEncadeado<String> b1 = b.adicionar("B1");
		final NodoBinarioEncadeado<String> b2 = b.adicionar("B2");
		final NodoBinarioEncadeado<String> b21 = b2.adicionar("B21");
		final NodoBinarioEncadeado<String> b22 = b2.adicionar("B22");
		
		Assert.assertFalse( a32.descendenteDe(b1) );
		Assert.assertFalse( a32.descendenteDe(b) );
		Assert.assertFalse( texto.descendenteDe(a31) );
	}
	
	@Test
	public void ancestralDeDevePassar() {
		final NodoBinarioEncadeado<String> texto = new NodoBinarioEncadeado<>("Alfabeto");
		final NodoBinarioEncadeado<String> a = texto.adicionar("A");
		final NodoBinarioEncadeado<String> a1 = a.adicionar("A1");
		final NodoBinarioEncadeado<String> a3 = a.adicionar("A3");
		final NodoBinarioEncadeado<String> a31 = a3.adicionar("A31");
		final NodoBinarioEncadeado<String> a32 = a3.adicionar("A32");
		
		final NodoBinarioEncadeado<String> b = texto.adicionar("B");
		final NodoBinarioEncadeado<String> b1 = b.adicionar("B1");
		final NodoBinarioEncadeado<String> b2 = b.adicionar("B2");
		final NodoBinarioEncadeado<String> b21 = b2.adicionar("B21");
		final NodoBinarioEncadeado<String> b22 = b2.adicionar("B22");
		
		Assert.assertTrue( a3.ancestralDe(a31) );
		
		Assert.assertTrue( texto.ancestralDe(a1) );
		Assert.assertTrue( texto.ancestralDe(a3) );
		Assert.assertTrue( texto.ancestralDe(b1) );
		Assert.assertTrue( texto.ancestralDe(b2) );
		
		Assert.assertTrue( a.ancestralDe(a31) );
		Assert.assertTrue( a.ancestralDe(a32) );
		Assert.assertTrue( a.ancestralDe(a1) );
		
		Assert.assertTrue( b.ancestralDe(b21) );
		Assert.assertTrue( b.ancestralDe(b22) );
		Assert.assertTrue( b.ancestralDe(b1) );
		
		Assert.assertFalse( b1.ancestralDe(b2) );
		Assert.assertFalse( a3.ancestralDe(b2) );
	}
	
	@Test
	public void caminhoAscendenteDevePassar() {
		final NodoBinarioEncadeado<String> texto = new NodoBinarioEncadeado<>("Alfabeto");
		final NodoBinarioEncadeado<String> a = texto.adicionar("A");
		final NodoBinarioEncadeado<String> a1 = a.adicionar("A1");
		final NodoBinarioEncadeado<String> a11 = a1.adicionar("A11");
		final NodoBinarioEncadeado<String> a111 = a11.adicionar("A111");
		
		final List<NodoAbstrato<String>> nodos = texto.caminho(a111);
		
		Assert.assertEquals( 5, texto.comprimento(a111) );
		Assert.assertEquals( 5, nodos.size() );
		Assert.assertEquals( texto, nodos.get(0) );
		Assert.assertEquals( a, nodos.get(1) );
		Assert.assertEquals( a1, nodos.get(2) );
		Assert.assertEquals( a11, nodos.get(3) );
		Assert.assertEquals( a111, nodos.get(4) );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void caminhoAscendenteDeveFalhar() {
		final NodoBinarioEncadeado<String> texto = new NodoBinarioEncadeado<>("Alfabeto");
		final NodoBinarioEncadeado<String> a = texto.adicionar("A");
		final NodoBinarioEncadeado<String> a1 = a.adicionar("A1");
		final NodoBinarioEncadeado<String> a2 = a.adicionar("A2");
		final NodoBinarioEncadeado<String> a11 = a1.adicionar("A11");
		final NodoBinarioEncadeado<String> a111 = a11.adicionar("A111");
		
		a2.caminho(a111);
		Assert.fail("Deveria estourar um erro de não formar um caminho.");
	}
	
	@Test
	public void caminhoDescendenteDevePassar() {
		final NodoBinarioEncadeado<String> texto = new NodoBinarioEncadeado<>("Alfabeto");
		final NodoBinarioEncadeado<String> a = texto.adicionar("A");
		final NodoBinarioEncadeado<String> a1 = a.adicionar("A1");
		final NodoBinarioEncadeado<String> a11 = a1.adicionar("A11");
		final NodoBinarioEncadeado<String> a111 = a11.adicionar("A111");
		
		final List<NodoAbstrato<String>> nodos = a111.caminho(texto);
		
		Assert.assertEquals( 5, a111.comprimento(texto) );
		Assert.assertEquals( 5, nodos.size() );
		Assert.assertEquals( a111, nodos.get(0) );
		Assert.assertEquals( a11, nodos.get(1) );
		Assert.assertEquals( a1, nodos.get(2) );
		Assert.assertEquals( a, nodos.get(3) );
		Assert.assertEquals( texto, nodos.get(4) );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void caminhoDescendenteDeveFalhar() {
		final NodoBinarioEncadeado<String> texto = new NodoBinarioEncadeado<>("Alfabeto");
		final NodoBinarioEncadeado<String> a = texto.adicionar("A");
		final NodoBinarioEncadeado<String> a1 = a.adicionar("A1");
		final NodoBinarioEncadeado<String> a2 = a.adicionar("A2");
		final NodoBinarioEncadeado<String> a11 = a1.adicionar("A11");
		final NodoBinarioEncadeado<String> a111 = a11.adicionar("A111");
		
		a111.caminho(a2);
		Assert.fail("Deveria estourar um erro de não formar um caminho.");
	}
}
