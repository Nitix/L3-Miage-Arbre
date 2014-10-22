package arbreBplus;


public class Arbre<T extends Comparable<T>> {

	private Noeud<T> racine;

	public Arbre(int ordre){
		this.racine = new Noeud<T>(ordre, this);
	}

	public void setRacine(Noeud<T> noeud){
		this.racine = noeud;
	}
	
	public void ajouterData(T data, String file) throws ObjectAlreadyExistsException, NoeudNonFeuilleException {
		Noeud<T> noeud = this.racine.rechercheBonnePlace(data);
		noeud.add(data, file);
	}

	public String supprimerData(T data) throws NoeudNonFeuilleException, NonExistentObjectException {
		Noeud<T> noeud = this.racine.rechercheBonnePlace(data);
		String file = noeud.getNomFichier(data);
		noeud.remove(data);
		return file;
	}
	
	public String getNomFichier(T data) throws NoeudNonFeuilleException, NonExistentObjectException {
		Noeud<T> noeud = this.racine.rechercheBonnePlace(data);
		return noeud.getNomFichier(data);
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
