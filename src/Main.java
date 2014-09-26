
public class Main {

	public static void main(String[] args) {
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

		noeud.getPointeur().add(0, noeudf1);
		noeud.getPointeur().add(1, noeudf2);
		noeud.getPointeur().add(2, noeudf3);

		
		noeud.recursiveToString();
		
		Noeud<Integer> noeudf4 = new Noeud<Integer>(2, noeud);
		noeudf4.getValeur().add(7);
		noeudf4.getValeur().add(8);

		noeud.getValeur().add(6);
		noeud.getPointeur().add(3, noeudf4);

		System.out.println("--------------------------------------------");
		noeud.recursiveToString();
		System.out.println("--------------------------------------------");

		
		noeud.split();
		noeud.recursiveToString();

		/*
		System.out.println(noeud.toString());
		System.out.println(noeud.getPointeur().get(0));
		System.out.println(noeud.getPointeur().get(1));
		*/
	}

}
