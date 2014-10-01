package arbreBplus;


public class Arbre<T extends Comparable<T>> {
	
	private Noeud<T> racine;
	private int ordre;
	private double taux = 0;
	
	public Arbre(int ordre){
		this.racine = new Noeud<T>(ordre);
		this.ordre = ordre;
	}
	
	public void ajouterData(T data) throws ObjectAlreadyExistsException {
		this.racine.rechercheBonnePlace(data);
	}
	
	public void supprimerData(T data){
		this.racine.rechercheBonneValeur(data);//FIXME Supp
	}
	
	public void recursiveToString(){
		this.racine.recursiveToString();
	}

	public double calculTaux() {
		Taux taux = new Taux();
		racine.getTauxRecursive(taux);
		return taux.getTaux();
	}

}
