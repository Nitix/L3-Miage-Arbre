package arbreBplus;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {		
		Noeud<Integer> noeud = new Noeud<Integer>(2);
		noeud.setFeuille(true);
		noeud.getValeur().add(1);
		noeud.getValeur().add(2);
		noeud.getValeur().add(3);

		
		noeud.split();
		
		noeud.recursiveToString();
	}

}