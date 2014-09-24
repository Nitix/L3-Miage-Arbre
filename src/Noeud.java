import java.util.ArrayList;
import java.util.Iterator;

public class Noeud<T extends Comparable<T>> {

	// Attributs
	private int ordre;
	private double tauxremplissage = 0;
	private ArrayList<T> valeur = new ArrayList<T>();
	private ArrayList<Noeud<T>> pointeur = new ArrayList<Noeud<T>>();
	private boolean feuille;
	private boolean racine;
	private Noeud<T> Pere;

	// Constructeur
	Noeud(int o){
		ordre = o;
		racine = true;
	}

	Noeud(int o, Noeud<T> p){
		ordre = o;
		Pere = p;
		racine = false;
	}

	// Méthodes 
	public void split(){

		if(this.isRacine() == true){ //Cas ou je split la racine
			int nbvaleur = this.getOrdre() +1;
			Noeud<T> fils1 = new Noeud<T>(this.getOrdre()+1, this);
			Noeud<T> fils2 = new Noeud<T>(this.getOrdre()+1, this);

			int pair = nbvaleur % 2;
			Iterator<T> it = this.valeur.iterator();
			int actuel = 0;
			while(it.hasNext()){
				T valeur = it.next();
				if(actuel < nbvaleur/2+pair){
					if(actuel != nbvaleur/2+pair - 1 ){
						it.remove();
					}
					fils1.getValeur().add(valeur);
				}else{
					it.remove();
					fils2.getValeur().add(valeur);
				}
				actuel++;
			}

			fils1.mettreAJourTauxDeRemplissage();
			fils2.mettreAJourTauxDeRemplissage();
			
			if(!this.feuille){
				for(int i = 0; i < nbvaleur/2+pair; i++){
					fils1.addNoeud(i, this.pointeur.get(i));
				}
				for(int i = nbvaleur/2+pair; i <= nbvaleur; i++){
					fils2.addNoeud(i - nbvaleur/2+pair,this.pointeur.get(i));
				}
				this.pointeur.clear();
			}
			this.getPointeur().add(0, fils1);
			this.getPointeur().add(1, fils2);
		}else{
			//TODO
		}
	}


	public void addNoeud(int index, Noeud<T> noeud) {
		this.pointeur.set(index, noeud);
	}

	public void mettreAJourTauxDeRemplissage() {
		this.tauxremplissage = this.valeur.size() / this.ordre;
	}

	// Getter & Setter
	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public double getTauxremplissage() {
		return tauxremplissage;
	}

	public void setTauxremplissage(double tauxremplissage) {
		this.tauxremplissage = tauxremplissage;
	}

	public ArrayList<T> getValeur() {
		return valeur;
	}

	public void setValeur(ArrayList<T> valeur) {
		this.valeur = valeur;
	}

	public ArrayList<Noeud<T>> getPointeur() {
		return pointeur;
	}

	public void setPointeur(ArrayList<Noeud<T>> pointeur) {
		this.pointeur = pointeur;
	}

	public boolean isFeuille() {
		return feuille;
	}

	public void setFeuille(boolean feuille) {
		this.feuille = feuille;
	}

	public boolean isRacine() {
		return racine;
	}

	public void setRacine(boolean racine) {
		this.racine = racine;
	}

	public Noeud<T> getPere() {
		return Pere;
	}

	public void setPere(Noeud<T> pere) {
		Pere = pere;
	}
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder("| ");
		Iterator<T> i = this.valeur.iterator();
		while(i.hasNext()){
			res.append(i.next());
			res.append(" | ");
		}
		
		return res.toString();
	}

}
