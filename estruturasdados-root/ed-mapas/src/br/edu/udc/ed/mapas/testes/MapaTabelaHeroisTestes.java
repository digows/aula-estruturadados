package br.edu.udc.ed.mapas.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.mapas.Heroi;
import br.edu.udc.ed.mapas.MapaTabelaHerois;

public class MapaTabelaHeroisTestes {
	
	@Test
	public void testAdicionarDevePassar(){
		final MapaTabelaHerois herois = new MapaTabelaHerois();
		
		final Heroi spiderman = new Heroi("Homem Aranha", 10F, true, false);
		herois.adiciona("XXMMOO", spiderman);
		
		final Heroi goku = new Heroi("Goku", 80000F, false, true);
		herois.adiciona("GODGOKU", goku);
		
		Assert.assertEquals(2, herois.tamanho());
		Assert.assertTrue( herois.contem("GODGOKU") );
		Assert.assertTrue( herois.contem("XXMMOO") );
	}
	
	@Test
	public void testAdicionaMilharesDevePassar(){
		final MapaTabelaHerois herois = new MapaTabelaHerois();
		
		for (int i = 0; i < 9999; i++) {
			herois.adiciona("QRCODE"+i, new Heroi("Homem Aranha", 10F, true, false));
		}
		
		Assert.assertEquals(9999, herois.tamanho() );
		System.out.println(herois);
	}
	
	@Test
	public void testAdicionarDevePassar2(){
		final MapaTabelaHerois herois = new MapaTabelaHerois();
		
		final Heroi spiderman = new Heroi("Homem Aranha", 10F, true, false);
		herois.adiciona("XXMMOO", spiderman);
		
		final Heroi goku = new Heroi("Goku", 80000F, false, true);
		herois.adiciona("XXMMOO", goku);
		
		Assert.assertEquals(1, herois.tamanho());
		Assert.assertTrue( herois.contem("XXMMOO") );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveDeveFalhar(){
		final MapaTabelaHerois herois = new MapaTabelaHerois();
		
		herois.remove("XXMMOO");
		
		Assert.fail("Deveria estourar um erro dizendo que o qrcode não existe");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void adicionaDevePassar(){
		final MapaTabelaHerois herois = new MapaTabelaHerois();
		
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
		final MapaTabelaHerois herois = new MapaTabelaHerois();
		
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
		final MapaTabelaHerois herois = new MapaTabelaHerois();
		herois.adiciona("FHSVIUSCH", new Heroi());
		herois.adiciona("DFIUSDIUFS", new Heroi());
		herois.adiciona("SDFIUSDUFS", new Heroi());
		herois.adiciona("ER(*@(RSD)(W", new Heroi());
		herois.adiciona("FSOISDOIDOSFI", new Heroi());
		
		Assert.assertTrue( herois.contem("ER(*@(RSD)(W") );
	}
	
	
	
	@Test
	public void teste(){
		final MapaTabelaHerois herois = new MapaTabelaHerois();
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
