package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import arbreBplus.Noeud;

public class SplitNonRacine {
	
	@Test
	public void splitOrdre2Feuille()
	{
		Noeud<Integer> noeud = new Noeud<Integer>(2);
		noeud.setFeuille(true);
		noeud.getValeur().add(1);

		Noeud<Integer> fils1 = new Noeud<>(2, noeud);
		fils1.getValeur().add(1);
		fils1.setFeuille(true);
		
		Noeud<Integer> fils2 = new Noeud<>(2, noeud);
		fils2.getValeur().add(2);	
		fils2.getValeur().add(3);	
		fils2.getValeur().add(4);	
		fils2.setFeuille(true);


		noeud.addNoeud(0, fils1);
		noeud.addNoeud(1, fils2);
		
		fils2.split();
		
		ArrayList<Integer> resRacine = new ArrayList<>();
		resRacine.add(1);
		resRacine.add(3);
		
		ArrayList<Integer> resFils2Val = new ArrayList<>();
		resFils2Val.add(2);
		resFils2Val.add(3);
		
		ArrayList<Integer> resFils3Val = new ArrayList<>();
		resFils3Val.add(4);
		
		Assert.assertEquals(resRacine, noeud.getValeur());
		Assert.assertEquals(resFils2Val, fils2.getValeur());
		Assert.assertEquals(3, noeud.getPointeur().size());
		Assert.assertEquals(resFils3Val, noeud.getPointeur().get(2).getValeur());
	}

}
