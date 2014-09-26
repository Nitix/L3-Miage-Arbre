
public class Main {

	public static void main(String[] args) {
		Noeud<Integer> noeud = new Noeud<Integer>(4);
		noeud.setFeuille(true);
		noeud.getValeur().add(1);
		noeud.getValeur().add(2);
		noeud.getValeur().add(3);
		noeud.getValeur().add(4);
		noeud.getValeur().add(5);
		
		noeud.split();
		
		System.out.println(noeud.toString());
		System.out.println(noeud.getPointeur().get(0));
		System.out.println(noeud.getPointeur().get(1));

	}

}