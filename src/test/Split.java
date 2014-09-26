package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import arbreBplus.Noeud;

public class Split {

	/*
	@Test
	public void splitRacineFeuille() {
		
		
	}
	*/
	
	@Test
	public void splitRacineNonFeuille(){
		Noeud<Integer> noeud = new Noeud<Integer>(2);
		noeud.setFeuille(true);
		noeud.getValeur().add(2);
		noeud.getValeur().add(4);
		
		Noeud<Integer> noeudf1 = new Noeud<Integer>(2, noeud);
		noeudf1.getValeur().add(1);
		noeudf1.getValeur().add(2);
		
		Noeud<Integer> noeudf2 = new Noeud<Integer>(2, noeud);
		noeudf2.getValeur().add(3);
		noeudf2.getValeur().add(4);
		
		Noeud<Integer> noeudf3 = new Noeud<Integer>(2, noeud);
		noeudf3.getValeur().add(5);
		noeudf3.getValeur().add(6);

		noeud.setFeuille(false);
		noeud.getPointeur().add(0, noeudf1);
		noeud.getPointeur().add(1, noeudf2);
		noeud.getPointeur().add(2, noeudf3);
		
		Noeud<Integer> noeudf4 = new Noeud<Integer>(2, noeud);
		noeudf4.getValeur().add(7);
		noeudf4.getValeur().add(8);

		noeud.getValeur().add(6);
		noeud.getPointeur().add(3, noeudf4);
		
		noeud.split();

		ArrayList<Integer> racine = new ArrayList<Integer>();
		racine.add(4);
		
		Assert.assertEquals(racine, noeud.getValeur());
	}

}
