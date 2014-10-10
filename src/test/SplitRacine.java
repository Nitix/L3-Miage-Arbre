package test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import arbreBplus.Noeud;

public class SplitRacine {
	
	@Test
	public void splitFeuille() {
		Noeud<Integer> noeud = new Noeud<Integer>(2, null);
		noeud.setFeuille(true);
		noeud.getValeur().add(1);
		noeud.getValeur().add(2);
		noeud.getValeur().add(3);

		
		noeud.split();
		
		//debut resultat attendu

		ArrayList<Integer> racine = new ArrayList<Integer>();
		racine.add(2);
		
		 //doit contenir que 2
		Assert.assertEquals(racine, noeud.getValeur());  
		
		ArrayList<Integer> noeudfils1 = new ArrayList<Integer>();
		noeudfils1.add(1);
		noeudfils1.add(2);
		
		Assert.assertEquals(noeudfils1, noeud.getPointeur().get(0).getValeur());  

		
	}
	
	@Test
	public void splitNonFeuille(){
		Noeud<Integer> noeud = new Noeud<Integer>(2, null);
		noeud.getValeur().add(2);
		noeud.getValeur().add(4);
		
		Noeud<Integer> noeudf1 = new Noeud<Integer>(2, noeud, null);
		noeudf1.getValeur().add(1);
		noeudf1.getValeur().add(2);
		
		Noeud<Integer> noeudf2 = new Noeud<Integer>(2, noeud, null);
		noeudf2.getValeur().add(3);
		noeudf2.getValeur().add(4);
		
		Noeud<Integer> noeudf3 = new Noeud<Integer>(2, noeud, null);
		noeudf3.getValeur().add(5);
		noeudf3.getValeur().add(6);

		noeud.setFeuille(false);
		noeud.getPointeur().add(0, noeudf1);
		noeud.getPointeur().add(1, noeudf2);
		noeud.getPointeur().add(2, noeudf3);
		
		Noeud<Integer> noeudf4 = new Noeud<Integer>(2, noeud, null);
		noeudf4.getValeur().add(7);
		noeudf4.getValeur().add(8);

		noeud.getValeur().add(6);
		noeud.getPointeur().add(3, noeudf4);
		
		noeud.split();
		
		//debut resultat attendu

		ArrayList<Integer> racine = new ArrayList<Integer>();
		racine.add(4);
		
		 //doit contenir que 4
		Assert.assertEquals(racine, noeud.getValeur());  
		
		//Contenu dans ses fils, le noeud a crée deux fils
		Assert.assertFalse(noeud.getPointeur().contains(noeudf1)); 
		Assert.assertFalse(noeud.getPointeur().contains(noeudf2));
		Assert.assertFalse(noeud.getPointeur().contains(noeudf3)); 
		Assert.assertFalse(noeud.getPointeur().contains(noeudf4));
		
		//Les parents doivent être idenique pour les deux premiers et les deux derniers et differents entre eux
		Assert.assertSame(noeudf1.getPere(), noeudf2.getPere());
		Assert.assertSame(noeudf3.getPere(), noeudf4.getPere());
		Assert.assertNotSame(noeudf1, noeudf3);	
		
		ArrayList<Integer> noeudFR1 = new ArrayList<Integer>();
		noeudFR1.add(2);
		
		Assert.assertEquals(noeudFR1, noeud.getPointeur().get(0).getValeur());  

		ArrayList<Integer> noeudFR2 = new ArrayList<Integer>();
		noeudFR2.add(6);
		
		Assert.assertEquals(noeudFR2, noeud.getPointeur().get(1).getValeur());  

	}
}
