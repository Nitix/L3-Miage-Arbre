package arbreBplus;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class Noeud permet de structurer l'arbre, un noued est chaque cellule d'un arbre. Il comporte une liste de valeurs, 
 * et une listes de pointeurs pour les noeuds fils
 * 
 * Il esy défini par un ordre qui donne le nombre de valeur que peut contenir un Noeud
 * 
 * @author Pierson Guillaume, Hugo Yohann
 *
 * @param <T> type d'index, il faut qu'il implemente l'interface Comparable pour pourvoir les trier
 */
public class Noeud<T extends Comparable<T>> {

	// Attributs
	private static int NEXT_ID = 0;

	private int id;  //utile pour le toString
	private int ordre;
	private double tauxremplissage = 0;
	private ArrayList<T> valeur = new ArrayList<T>();
	private ArrayList<Noeud<T>> pointeur = new ArrayList<Noeud<T>>();
	private boolean feuille;
	private boolean racine;
	private Noeud<T> pere;

	// Constructeur
	public Noeud(int ordre){
		this.ordre = ordre;
		this.racine = true;
		this.feuille = true;
		this.id = NEXT_ID;
		NEXT_ID++;
	}

	public Noeud(int ordre, Noeud<T> pere){
		this.ordre = ordre;
		this.pere = pere;
		this.feuille = false;
		this.racine = false;
		this.id = NEXT_ID;
		NEXT_ID++;
	}

	// Méthodes 
	public void split(){
		int nbvaleur = this.getOrdre() +1;
		int pair = nbvaleur % 2;

		if(this.isRacine() == true){ //Cas ou je split la racine
			Noeud<T> fils1 = new Noeud<T>(this.getOrdre(), this);
			Noeud<T> fils2 = new Noeud<T>(this.getOrdre(), this);

			if(this.isFeuille()){
				this.copierValeurDansNoeud(fils1, 0, nbvaleur/2+pair);
				fils1.setFeuille(true);
				fils2.setFeuille(true);
			}else{
				this.copierValeurDansNoeud(fils1, 0, nbvaleur/2+pair-1);
			}
			this.copierValeurDansNoeud(fils2, nbvaleur/2+pair, nbvaleur);

			T valeur = this.valeur.get(nbvaleur/2-1+pair);
			this.valeur.clear();
			this.valeur.add(valeur);

			fils1.mettreAJourTauxDeRemplissage();
			fils2.mettreAJourTauxDeRemplissage();

			if(!this.feuille){
				this.copierPointeurDansNoeud(fils1, 0, nbvaleur/2+pair);
				this.copierPointeurDansNoeud(fils2, nbvaleur/2+pair, pointeur.size());
				this.pointeur.clear();
			}else{
				this.setFeuille(false);
			}
			this.getPointeur().add(0, fils1);
			this.getPointeur().add(1, fils2);
		}else{
			Noeud<T> noeudFrere = new Noeud<T>(this.getOrdre(), this.pere);
			this.copierValeurDansNoeud(noeudFrere,nbvaleur/2+pair , nbvaleur);
			this.valeur.subList(nbvaleur/2+pair, nbvaleur).clear();
			noeudFrere.mettreAJourTauxDeRemplissage();
			if(!this.isFeuille()){
				this.copierPointeurDansNoeud(noeudFrere, nbvaleur/2+pair, pointeur.size());
				this.supprimerPointeur(nbvaleur/2+pair, pointeur.size());
			}else{
				noeudFrere.setFeuille(true);
			}
			pere.ajouter(noeudFrere, this, this.getValeur().get(nbvaleur/2-1+pair));  //FIXME Erreur avec ordre 3
		}
		this.mettreAJourTauxDeRemplissage();
	}

	private void ajouter(Noeud<T> noeudfils, Noeud<T> noeudfilsorigine, T valeur) {
		int index = this.pointeur.indexOf(noeudfilsorigine);
		if(index == -1)
			System.out.println("CRASH");
		this.valeur.add(index, valeur);   //FIXME provoque des erreurs -- Problème de réference Père
		this.pointeur.add(index+1, noeudfils);
		this.mettreAJourTauxDeRemplissage();
	}

	private void supprimerPointeur(int debut, int fin){
		this.pointeur.subList(debut, fin).clear();
	}

	private void copierValeurDansNoeud(Noeud<T> noeud, int debut, int fin){
		for(int i = debut; i < fin; i++){
			noeud.getValeur().add(this.valeur.get(i));
		}
	}

	private void copierPointeurDansNoeud(Noeud<T> noeud, int debut, int fin){
		for(int i = debut; i < fin; i++){
			Noeud<T> filsOrigine = this.pointeur.get(i);
			noeud.getPointeur().add(filsOrigine);
			filsOrigine.setPere(noeud);
		}
	}

	public void addNoeud(int index, Noeud<T> noeud) {
		this.pointeur.add(index, noeud);
	}

	public void mettreAJourTauxDeRemplissage() {
		this.tauxremplissage = (double) this.valeur.size() / this.ordre;
		if(tauxremplissage> 1){
			this.split();
		}
	}

	public int getID(){
		return this.id;
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
		return pere;
	}

	public void setPere(Noeud<T> pere) {
		this.pere = pere;
	}

	public void recursiveToString(){
		System.out.println(this.toString());
		Iterator<Noeud<T>> it = this.pointeur.iterator();
		while(it.hasNext()){
			it.next().recursiveToString();
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder("@" + this.id + " :: | ");
		Iterator<T> i = this.valeur.iterator();
		while(i.hasNext()){
			res.append(i.next());
			res.append(" | ");
		}
		res.append(" -- ");
		Iterator<Noeud<T>> j = this.pointeur.iterator();
		if(j.hasNext()){
			res.append("@"+j.next().getID());
		}
		while(j.hasNext()){
			res.append(" | ");
			res.append("@"+j.next().getID());
		}
		res.append("  --- T = ");
		res.append(this.tauxremplissage);
		return res.toString();
	}

	public void rechercheBonnePlace(T data) throws ObjectAlreadyExistsException{
		if(this.valeur.contains(data))
			throw new ObjectAlreadyExistsException();

		boolean trouve = false;
		for(int i = 0; i < this.valeur.size(); i++){
			if(this.valeur.get(i).compareTo(data) >= 0){
				trouve = true;
				if(this.isFeuille()){
					this.valeur.add(i, data);
					this.mettreAJourTauxDeRemplissage();
				}else{
					this.pointeur.get(i).rechercheBonnePlace(data);
				}
				break;
			}
		}
		if(!trouve){
			if(this.isFeuille()){
				this.valeur.add(data);
				this.mettreAJourTauxDeRemplissage();
			}else{
				this.pointeur.get(this.pointeur.size()-1).rechercheBonnePlace(data);
			}
		}
	}

	public void getTauxRecursive(Taux taux) {
		for(int i = 0; i < this.pointeur.size(); i++){
			this.pointeur.get(i).getTauxRecursive(taux);
		}
		taux.addTaux(tauxremplissage);
	}

}
