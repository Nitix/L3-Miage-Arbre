package arbreBplus;

import java.util.ArrayList;
import java.util.Iterator;


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
		this.id = NEXT_ID;
		NEXT_ID++;
	}

	public Noeud(int ordre, Noeud<T> pere){
		this.ordre = ordre;
		this.pere = pere;
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
			}else{
				this.copierValeurDansNoeud(fils1, 0, nbvaleur/2+pair-1);
			}
			this.copierValeurDansNoeud(fils2, nbvaleur/2+pair, nbvaleur);
			
			T valeur = this.valeur.get(nbvaleur/2);
			this.valeur.clear();
			this.valeur.add(valeur);
			
			fils1.mettreAJourTauxDeRemplissage();
			fils2.mettreAJourTauxDeRemplissage();
			
			if(!this.feuille){
				this.copierPointeurDansNoeud(fils1, 0, nbvaleur/2+pair);
				this.copierPointeurDansNoeud(fils2, nbvaleur/2+pair, pointeur.size());
				this.pointeur.clear();
			}
			this.getPointeur().add(0, fils1);
			this.getPointeur().add(1, fils2);
		}else{
			Noeud<T> noeudFrere = new Noeud<T>(this.getOrdre(), this.pere);
			noeudFrere.copierPointeurDansNoeud(noeudFrere, nbvaleur/2+pair, pointeur.size());
			noeudFrere.mettreAJourTauxDeRemplissage();
			this.supprimerPointeur(nbvaleur/2, pointeur.size());
			pere.ajouter(noeudFrere, this, this.getValeur().get(nbvaleur/2));
		}
		this.mettreAJourTauxDeRemplissage();
	}
	
	private void ajouter(Noeud<T> noeudfils, Noeud<T> noeudfilsorigine, T valeur) {
		int index = this.pointeur.indexOf(noeudfilsorigine);
		this.valeur.add(index-1, valeur);
		this.pointeur.add(index, noeudfilsorigine);
		//TODO vérifier overflow
		//this.verifierOverflow();
	}

	private void supprimerPointeur(int debut, int fin){
		for(int i=debut; i<fin; i++){
			this.pointeur.remove(i);
		}
	}
		
	private void copierValeurDansNoeud(Noeud<T> noeud, int debut, int fin){
		for(int i = debut; i < fin; i++){
			noeud.getValeur().add(this.valeur.get(i));
		}
	}

	private void copierPointeurDansNoeud(Noeud<T> noeud, int debut, int fin){
		for(int i = debut; i < fin; i++){
			noeud.getPointeur().add(this.pointeur.get(i));
		}
	}

	public void addNoeud(int index, Noeud<T> noeud) {
		this.pointeur.add(index, noeud);
	}

	public void mettreAJourTauxDeRemplissage() {
		this.tauxremplissage = this.valeur.size() / this.ordre;
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
		while(j.hasNext()){
			res.append("@"+j.next().getID());
			res.append(" | ");
		}
		
		return res.toString();
	}

}
