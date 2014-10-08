package arbreBplus;


public class Arbre<T extends Comparable<T>> {

	private Noeud<T> racine;
	private int ordre;
	private double taux = 0;

	public Arbre(int ordre){
		this.racine = new Noeud<T>(ordre);
		this.ordre = ordre;
	}

	public void ajouterData(T data) throws ObjectAlreadyExistsException, NoeudNonFeuilleException {
		Noeud<T> noeud = this.racine.rechercheBonnePlace(data);
		noeud.add(data);
	}

	public void supprimerData(T data) throws NoeudNonFeuilleException{
		Noeud<T> noeud = this.racine.rechercheBonnePlace(data);
		noeud.remove(data);
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
