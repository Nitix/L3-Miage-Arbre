
public class Arbre<T extends Comparable<T>> {
	
	private Noeud<T> racine;
	private int ordre;
	private double taux = 0;
	
	public Arbre(int ordre){
		this.racine = new Noeud<T>(ordre);
		this.ordre = ordre;
	}

	public Noeud<T> getRacine() {
		return racine;
	}

	public void setRacine(Noeud<T> racine) {
		if(racine == null)
			throw new NullPointerException("Racine null");
		this.racine = racine;
	}

}
