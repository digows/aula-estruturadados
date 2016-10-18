package br.edu.udc.ed.mapa.testes;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.mapa.Mapa;
import br.edu.udc.ed.mapa.heroi.Heroi;

public class MapaTestes {
	
	@Test
	public void testAdiciona() {
		final Mapa<String, Long> mapa = new Mapa<>();
		mapa.adiciona("chave1", 1L);
		mapa.adiciona("chave2", 2L);
	}
	
	@Test
	public void testString() {
		final String a = "João";
		System.out.println( a );
		try {
			final Field value = String.class.getDeclaredField("value");
		    value.setAccessible(true); //2
		    char [] charsDaString = (char []) value.get(a); // 3
		    charsDaString[0] = 'X';
		    charsDaString[1] = 'o';
		    charsDaString[2] = 'V';
		    System.out.println( a );
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAdicionaMilharesDevePassar(){
		final Mapa<String, Heroi> herois = new Mapa<>();
		for (int i = 0; i < 99999; i++) {
			herois.adiciona("QRCODE"+i, new Heroi("Homem Aranha", 10F, true, false));
		}
		Assert.assertEquals(99999, herois.tamanho() );
		System.out.println( herois );
	}
	
	
	
	@Test
	public void testChaveIntegerDevePassar(){
		final Mapa<Integer, String> mapaInt = new Mapa<>();
		mapaInt.adiciona(1, "Um");
		mapaInt.adiciona(2, "Dois");
		mapaInt.adiciona(3, "Três");
		
		Assert.assertEquals( mapaInt.obtem(1), "Um" );
		Assert.assertEquals( mapaInt.obtem(2), "Dois" );
		Assert.assertEquals( mapaInt.obtem(3), "Três" );
	}
	
	
	@Test
	public void testAdicionarDevePassar(){
		final Mapa<String, Heroi> herois = new Mapa<>();
		
		final Heroi spiderman = new Heroi("Homem Aranha", 10F, true, false);
		herois.adiciona("XXMMOO", spiderman);
		
		final Heroi goku = new Heroi("Goku", 80000F, false, true);
		herois.adiciona("GODGOKU", goku);
		
		Assert.assertEquals( 2, herois.tamanho() );
		Assert.assertTrue( herois.contem("GODGOKU") );
		Assert.assertTrue( herois.contem("XXMMOO") );
		Assert.assertEquals( herois.obtem("XXMMOO"), spiderman);
	}
	
	@Test
	public void testAdicionarDevePassar2(){
		final Mapa<String, Heroi> herois = new Mapa<>();
		
		final Heroi spiderman = new Heroi("Homem Aranha", 10F, true, false);
		herois.adiciona("XXMMOO", spiderman);
		
		final Heroi goku = new Heroi("Goku", 80000F, false, true);
		herois.adiciona("XXMMOO", goku);
		
		Assert.assertEquals(1, herois.tamanho());
		Assert.assertTrue( herois.contem("XXMMOO") );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveDeveFalhar(){
		final Mapa<String, Heroi> herois = new Mapa<>();
		
		herois.remove("XXMMOO");
		
		Assert.fail("Deveria estourar um erro dizendo que o qrcode não existe");
	}
	
	
	@Test
	public void adicionaDevePassar(){
		final Mapa<String, Heroi> herois = new Mapa<>();
		
		final Heroi hulk = new Heroi();
		hulk.setEspecialidade("Quebrar");
		hulk.setForca(100.0F);
		hulk.setFraqueza("Sentimental");
		hulk.setHumano(true);
		hulk.setVoa(false);
		herois.adiciona("AXOAI-01209381", hulk);
		
		final Heroi ironMan = new Heroi();
		hulk.setEspecialidade("Inteligencia");
		hulk.setForca(30.0F);
		hulk.setFraqueza("Arrogante");
		hulk.setHumano(true);
		hulk.setVoa(true);
		herois.adiciona("DSOFI-82849234", ironMan);
		
		final Heroi spiderMan = new Heroi();
		hulk.setEspecialidade("Agilidade");
		hulk.setForca(30.0F);
		hulk.setFraqueza("Infantil");
		hulk.setHumano(true);
		hulk.setVoa(false);
		herois.adiciona("ASODDSAOU-191123", spiderMan);
		
		Assert.assertEquals(3, herois.tamanho());
		Assert.assertTrue( herois.contem("ASODDSAOU-191123") );
		Assert.assertEquals( spiderMan, herois.obtem("ASODDSAOU-191123") );
	}
	
	@Test
	public void removeDevePassar(){
		final Mapa<String, Heroi> herois = new Mapa<>();
		
		herois.adiciona("AASHKDAJSHD", new Heroi());
		herois.adiciona("DOSAIVOSFS", new Heroi());
		herois.adiciona("SDIUFISDUFS", new Heroi());
		herois.adiciona("SDKFDSUIGDI", new Heroi());
		
		herois.remove("SDKFDSUIGDI");
		Assert.assertFalse( herois.contem("SDKFDSUIGDI") );
		Assert.assertEquals( 3, herois.tamanho() );
	}
	
	@Test
	public void obtemDevePassar() {
		final Mapa<String, Heroi> herois = new Mapa<>();
		herois.adiciona("FHSVIUSCH", new Heroi());
		herois.adiciona("DFIUSDIUFS", new Heroi());
		herois.adiciona("SDFIUSDUFS", new Heroi());
		herois.adiciona("ER(*@(RSD)(W", new Heroi());
		herois.adiciona("FSOISDOIDOSFI", new Heroi());
		
		Assert.assertTrue( herois.contem("ER(*@(RSD)(W") );
	}
	
	@Test
	public void teste(){
		final Mapa<String, Heroi> herois = new Mapa<>();
		herois.adiciona("ABC", new Heroi());
		herois.adiciona("CXZ", new Heroi());
		herois.adiciona("OIT", new Heroi());
		
		Assert.assertEquals(3, herois.tamanho());
		
		herois.remove("OIT");
		Assert.assertEquals(2, herois.tamanho());
		Assert.assertTrue( herois.contem("ABC") );
		Assert.assertTrue( herois.contem("CXZ") );
		
		final Heroi h1 = herois.obtem("ABC");
		Assert.assertNotNull(h1);
	}
}
