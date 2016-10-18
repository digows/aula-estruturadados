package br.edu.udc.ed.mapa.heroi.testes;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.mapa.heroi.Heroi;
import br.edu.udc.ed.mapa.heroi.MapaListaHeroi;

public class MapaListaHeroiTestes {
	
	@Test
	public void adicionaDevePasar() {
		final MapaListaHeroi mapa = new MapaListaHeroi();
		
		final Heroi ironman = new Heroi();
		ironman.setNome("Iron Man");
		ironman.setEspecialidade("Criar armas");
		ironman.setForca(60F);
		ironman.setFraqueza("Egoista");
		ironman.setVoa(true);
		mapa.adiciona("QTDOA-AODASC-ASLDOAS", ironman);
		
		final Heroi hulk = new Heroi();
		hulk.setNome("Hulk");
		hulk.setEspecialidade("Demolir");
		hulk.setForca(95F);
		hulk.setFraqueza("Temperamental");
		hulk.setHumano(true);
		hulk.setVoa(false);
		mapa.adiciona("ASDUUAD-AHSDHUASD", hulk);
		
		Assert.assertEquals(2, mapa.tamanho());
		Assert.assertTrue( mapa.contem("QTDOA-AODASC-ASLDOAS") );
		Assert.assertTrue( mapa.contem("ASDUUAD-AHSDHUASD") );
		Assert.assertEquals( ironman , mapa.obtem("QTDOA-AODASC-ASLDOAS") );
		Assert.assertEquals( hulk , mapa.obtem("ASDUUAD-AHSDHUASD") );
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removeDeveFalhar() {
		final MapaListaHeroi mapa = new MapaListaHeroi();
		mapa.remove("NAO_EXISTE");
	}
	
	@Test
	public void desempenho() {
		final long agora = System.currentTimeMillis();
		
		final long depois = System.currentTimeMillis();
		
		System.out.println( depois - agora );
	}
	
	@Test
	public void removeDevePassar() {
		final MapaListaHeroi mapa = new MapaListaHeroi();
		
		final Heroi thor = new Heroi();
		thor.setNome("Thor");
		mapa.adiciona("XXXX", thor);
		
		mapa.remove("XXXX");
		
		Assert.assertEquals(0, mapa.tamanho());
		Assert.assertFalse( mapa.contem("XXXX") );
	}
}
