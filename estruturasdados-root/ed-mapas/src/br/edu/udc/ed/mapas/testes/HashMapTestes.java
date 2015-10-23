package br.edu.udc.ed.mapas.testes;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.edu.udc.ed.mapas.Heroi;

public class HashMapTestes {
	
	@Test
	public void putDevePassar(){
		final Map<String, Heroi> herois = new HashMap<>();
		
		final Heroi spiderman = new Heroi("Homem Aranha", 10F, true, false);
		herois.put("XXMMOO", spiderman);
		
		final Heroi goku = new Heroi("Goku", 80000F, false, true);
		herois.put("GODGOKU", goku);
		
		Assert.assertEquals(2, herois.size());
		Assert.assertTrue( herois.containsKey("GODGOKU") );
		Assert.assertTrue( herois.containsKey("XXMMOO") );
		Assert.assertTrue( herois.containsValue(goku) );
		Assert.assertEquals( herois.get("XXMMOO"), spiderman);
		
		for (Heroi heroi : herois.values()) {
			System.out.println( heroi );
		}
		
		for (String qrcode : herois.keySet() ) {
			System.out.println( qrcode );
		}
	}
}

