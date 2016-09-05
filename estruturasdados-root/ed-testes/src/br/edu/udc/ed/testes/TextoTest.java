package br.edu.udc.ed.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.Texto;

public class TextoTest {
	
	//converterEmMinusculo
	@Test
	public void converterEmMinusculoDevePassar() {
		final String nome = "RODRIGO P. FRAGA";
		final Texto texto = new Texto( nome );
		
		final String nomeMinusculo = texto.converterEmMinusculo();
		Assert.assertEquals("Não converteu em minusculo", nome.toLowerCase(), nomeMinusculo);
	}
	
	@Test
	public void converterEmMinusculoDevePassarComNumeros() {
		final String numeros = "100101101010101101";
		final Texto texto = new Texto( numeros );
		
		final String numerosConvertidos = texto.converterEmMinusculo();
		
		Assert.assertEquals("Não deveria converter", numeros, numerosConvertidos);
	}
	
	@Test(expected=NullPointerException.class)
	public void converterEmMinusculoDeveFalhar() {
		final Texto texto = new Texto(null);
		texto.converterEmMinusculo();
		
		Assert.fail("Deveria ter retornado um nullpointer exception");
	}
	
	//indiceDe
	@Test
	public void indiceDeDevePassar() {
		final Texto texto = new Texto("Uma Palavra");
		int indice = texto.indiceDe("P");
		
		Assert.assertEquals("O indice está invalido", 4, indice);
	}
	
	@Test
	public void indiceDeDeveRetornarMenos1() {
		final Texto texto = new Texto("Uma Palavra");
		int indice = texto.indiceDe("X");
		
		Assert.assertEquals("O indice deve ser -1", -1, indice);
	}
	
	@Test
	public void indiceDeDeveRetornarIndice5() {
		final Texto texto = new Texto("10010ç011001001");
		int indice = texto.indiceDe("ç");
		
		Assert.assertEquals("O indice está invalido", 5, indice);
	}
	
	@Test
	public void indiceDeNaoDeveEncontrarMinusculo() {
		final Texto texto = new Texto("MAIUSCULO");
		int indice = texto.indiceDe("i");
		
		Assert.assertEquals("Não deveria ter encontrado um indice", -1, indice);
	}
	
	//subTexto	
	@Test
	public void subTextoDevePassar() {
		final Texto texto = new Texto("Uma frase grande");
		final String subTexto = texto.subTexto(4, 9);
		
		Assert.assertEquals("Sub texto invalido", "frase", subTexto);
	}
	
	@Test(expected=StringIndexOutOfBoundsException.class)
	public void subTextoForaAlcance() {
		final Texto texto = new Texto("Uma frase grande");
		texto.subTexto(10, 20);
		
		Assert.fail("Deveria ter retornado um index out of bound");
	}
	
	@Test(expected=StringIndexOutOfBoundsException.class)
	public void subTextoComIndicesInvertidos() {
		final Texto texto = new Texto("Uma frase grande");
		texto.subTexto(20, 10);
		
		Assert.fail("Deveria ter retornado um index out of bound");
	}
	
	@Test(expected=StringIndexOutOfBoundsException.class)
	public void subTextoComIndicesNegativos() {
		final Texto texto = new Texto("Uma frase grande");
		texto.subTexto(-1, -2);
		
		Assert.fail("Deveria ter retornado um index out of bound");
	}
}
