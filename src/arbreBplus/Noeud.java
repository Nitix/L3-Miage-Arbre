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
	private ArrayList<String> pointeurFichier = new ArrayList<>();
	private boolean feuille;
	private boolean racine;
	private Noeud<T> pere;
	private Arbre<T> arbre;

	// Constructeur
	public Noeud(int ordre, Arbre<T> arbre){
		this.ordre = ordre;
		this.racine = true;
		this.feuille = true;
		this.arbre = arbre;
		this.id = NEXT_ID;
		NEXT_ID++;
	}

	public Noeud(int ordre, Noeud<T> pere, Arbre<T> arbre){
		this.ordre = ordre;
		this.pere = pere;
		this.feuille = false;
		this.racine = false;
		this.arbre = arbre;
		this.id = NEXT_ID;
		NEXT_ID++;
	}

	public Noeud<T> rechercheBonnePlace(T data){ 
		if(this.isFeuille())
			return this;
		for(int i = 0; i < this.valeur.size(); i++){
			if(this.valeur.get(i).compareTo(data) >= 0){
				return this.pointeur.get(i).rechercheBonnePlace(data);
			}
		}
		return this.pointeur.get(this.pointeur.size()-1).rechercheBonnePlace(data);
	}

	// Méthodes 
	public void split(){
		int nbvaleur = this.valeur.size();
		int pair = nbvaleur % 2;
	
		if(this.isRacine() == true){ //Cas ou je split la racine
			Noeud<T> fils1 = new Noeud<T>(this.getOrdre(), this, this.arbre);
			Noeud<T> fils2 = new Noeud<T>(this.getOrdre(), this, this.arbre);
	
			if(this.isFeuille()){
				this.copierValeurDansNoeud(fils1, 0, nbvaleur/2+pair);
				
				fils1.setFeuille(true);
				fils2.setFeuille(true);
				
				this.copierPointeurFichierDansNoeud(fils1, 0, nbvaleur/2+pair);
				this.copierPointeurFichierDansNoeud(fils2, nbvaleur/2+pair, nbvaleur);
			}else{
				this.copierValeurDansNoeud(fils1, 0, nbvaleur/2+pair-1);
			}
			this.copierValeurDansNoeud(fils2, nbvaleur/2+pair, nbvaleur);
	
			T valeur = this.valeur.get(nbvaleur/2-1+pair);
			this.valeur.clear();
			this.valeur.add(valeur);
	
			this.pointeurFichier.clear();
			
			if(!this.feuille){
				this.copierPointeurDansNoeud(fils1, 0, nbvaleur/2+pair);
				this.copierPointeurDansNoeud(fils2, nbvaleur/2+pair, pointeur.size());
				this.pointeur.clear();
			}else{
				this.setFeuille(false);
			}
			this.getPointeur().add(0, fils1);
			this.getPointeur().add(1, fils2);
			
			fils1.mettreAJourTauxDeRemplissage();
			fils2.mettreAJourTauxDeRemplissage();
			fils1.checkSplit();
			fils2.checkSplit();
		}else{
			Noeud<T> noeudFrere = new Noeud<T>(this.getOrdre(), this.pere, this.arbre);
			T valeur = this.getValeur().get(nbvaleur/2-1+pair);
			this.copierValeurDansNoeud(noeudFrere,nbvaleur/2+pair , nbvaleur);
			if(!this.isFeuille()){
				this.copierPointeurDansNoeud(noeudFrere, nbvaleur/2+pair, pointeur.size());
				this.supprimerPointeur(nbvaleur/2+pair, pointeur.size());
				this.valeur.subList(nbvaleur/2+pair-1, nbvaleur).clear();
			}else{
				noeudFrere.setFeuille(true);
				this.valeur.subList(nbvaleur/2+pair, nbvaleur).clear();
				this.copierPointeurFichierDansNoeud(noeudFrere,nbvaleur/2+pair,nbvaleur);
				this.pointeurFichier.subList(nbvaleur/2+pair,nbvaleur).clear();
			}
			this.mettreAJourTauxDeRemplissage();
			pere.ajouter(noeudFrere, this, valeur); 
			pere.mettreAJourTauxDeRemplissage();
			pere.checkSplit();
			noeudFrere.mettreAJourTauxDeRemplissage();
			noeudFrere.checkSplit();
		}
		this.mettreAJourTauxDeRemplissage();
	}

	public void fusion(){
		if(!this.isRacine()){
			int index = this.pere.getPointeur().indexOf(this);
			Noeud<T> brother;
			if(index>0){
				brother = this.pere.getPointeur().get(index-1);
				if(!this.feuille){
					Noeud<T> childOfBrother = brother.getPointeur().get(brother.getPointeur().size()-1);
					brother.getValeur().add(childOfBrother.getValeur().get(childOfBrother.getValeur().size()-1));
					this.copierPointeurDansNoeud(brother, 0, this.pointeur.size());
				}else{
					this.copierPointeurFichierDansNoeud(brother, 0, this.getPointeurFichier().size());					
				}
				this.copierValeurDansNoeud(brother, 0, this.getValeur().size());
				pere.getValeur().remove(index-1);
			}else{
				brother = this.pere.getPointeur().get(index+1);
				if(!this.feuille){
					this.valeur.add(this.pointeur.get(this.pointeur.size()-1).getValeur().get(this.pointeur.get(this.pointeur.size()-1).getValeur().size()-1));
					this.copierPointeurDansNoeud(brother, 0, this.pointeur.size(), 0);
				}else{
					this.copierPointeurFichierDansNoeud(brother, 0, this.getPointeurFichier().size());					
				}
				this.copierValeurDansNoeud(brother, 0, this.getValeur().size(), 0);
				pere.getValeur().remove(index);
			}
			brother.mettreAJourTauxDeRemplissage();
			this.pere.getPointeur().remove(index);
			pere.mettreAJourTauxDeRemplissage();
			brother.checkSplit();
			if(pere.isRacine()){
				if(pere.getValeur().size() == 0){
					arbre.setRacine(brother);
					brother.setRacine(true);
					brother.setPere(null);
				}
			}else{
				pere.checkFusion();
			}
		}

	}

	public void mettreAJourTauxDeRemplissage() {
		this.tauxremplissage = (double) this.valeur.size() / this.ordre;
	}

	public void add(T data, String file) throws ObjectAlreadyExistsException, NoeudNonFeuilleException{
		if(this.valeur.contains(data))
			throw new ObjectAlreadyExistsException();
		boolean trouve = false;
		for(int i = 0; i < this.valeur.size(); i++){
			if(this.valeur.get(i).compareTo(data) >= 0){
				trouve = true;
				if(!this.isFeuille()){
					throw new NoeudNonFeuilleException();
				}
					this.valeur.add(i, data);
					this.pointeurFichier.add(i, file);
					this.mettreAJourTauxDeRemplissage();
					this.checkSplit();				
				break;
			}
		}
		if(!trouve){
			if(this.isFeuille()){
				this.valeur.add(data);
				this.pointeurFichier.add(file);
				this.mettreAJourTauxDeRemplissage();
				this.checkSplit();
			}else{
				throw new NoeudNonFeuilleException();
			}
		}
	}

	public void addNoeud(int index, Noeud<T> noeud) {
		this.pointeur.add(index, noeud);
	}

	private void ajouter(Noeud<T> noeudfils, Noeud<T> noeudfilsorigine, T valeur) {
		int index = this.pointeur.indexOf(noeudfilsorigine);
		this.valeur.add(index, valeur);  
		this.pointeur.add(index+1, noeudfils);
	}


	public void remove(T data) throws NoeudNonFeuilleException{
		if(!this.isFeuille()){
			throw new NoeudNonFeuilleException();
		}
		int index = this.valeur.indexOf(data);
		if(index != -1){
			this.valeur.remove(index);
			this.pointeurFichier.remove(index);
			if(index != 0){
				pere.remplaceOccurenceOf(data, this.valeur.get(index-1));
			}
			this.mettreAJourTauxDeRemplissage();
			this.checkFusion();
		}
	}

	private void supprimerPointeur(int debut, int fin){
		this.pointeur.subList(debut, fin).clear();
	}

	private void remplaceOccurenceOf(T data, T t) {
		int index = this.valeur.indexOf(data);
		if(index  != -1){
			this.valeur.remove(index);
			this.valeur.add(index, t);
		}
		if(!this.racine)
			pere.remplaceOccurenceOf(data, t);
	}

	private void copierValeurDansNoeud(Noeud<T> noeud, int debut, int fin){
		for(int i = debut; i < fin; i++){
			noeud.getValeur().add(this.valeur.get(i));
		}
	}
	
	
	private void copierValeurDansNoeud(Noeud<T> noeud, int debut, int fin, int initialIndex) {
		for(int i = debut; i < fin; i++){
			noeud.getValeur().add(initialIndex, this.valeur.get(i));
			initialIndex++;
		}
	}
	
	
	private void copierPointeurDansNoeud(Noeud<T> noeud, int debut, int fin){
		for(int i = debut; i < fin; i++){
			Noeud<T> filsOrigine = this.pointeur.get(i);
			noeud.getPointeur().add(filsOrigine);
			filsOrigine.setPere(noeud);
		}
	}

	private void copierPointeurDansNoeud(Noeud<T> noeud, int debut, int fin, int initialIndex){
		for(int i = debut; i < fin; i++){
			Noeud<T> filsOrigine = this.pointeur.get(i);
			noeud.getPointeur().add(initialIndex, filsOrigine);
			filsOrigine.setPere(noeud);
			initialIndex++;
		}
	}

	private void copierPointeurFichierDansNoeud(Noeud<T> noeud, int debut, int fin){
		for(int i = debut; i < fin; i++){
			noeud.getPointeurFichier().add(this.pointeurFichier.get(i));
		}
	}
	

	private void copierPointeurFichierDansNoeud(Noeud<T> noeud, int debut, int fin, int initialIndex) {
		for(int i = debut; i < fin; i++){
			if(i == this.pointeurFichier.size())
				System.out.println("nope");
			noeud.getPointeurFichier().add(initialIndex, this.pointeurFichier.get(i));
			initialIndex++;
		}
	}


	private void checkSplit() {
		if(this.tauxremplissage > 1)
			this.split();
	}

	private void checkFusion(){
		if(this.isFeuille()){
			if(this.valeur.size() < (this.ordre+1)/2)
				this.fusion();	
		}else if(!this.racine){
			if(this.pointeur.size() <(int) Math.round( (double)((this.ordre+1)/2)))
				this.fusion();	
		}
		
	}

	public void getTauxRecursive(Taux taux) {
		for(int i = 0; i < this.pointeur.size(); i++){
			this.pointeur.get(i).getTauxRecursive(taux);
		}
		taux.addTaux(tauxremplissage);
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

	
	public ArrayList<String> getPointeurFichier() {
		return pointeurFichier;
	}

	public void setPointeurFichier(ArrayList<String> pointeurFichier) {
		this.pointeurFichier = pointeurFichier;
	}
	
	public String getNomFichier(T data) throws NoeudNonFeuilleException, NonExistentObjectException {
		if(!this.isFeuille()){
			throw new NoeudNonFeuilleException();
		}
		int index = this.valeur.indexOf(data);
		if(index == -1){
			throw new NonExistentObjectException();
		}
		return this.pointeurFichier.get(index);
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
		Iterator<String> fichier = this.pointeurFichier.iterator();
		if(fichier.hasNext()){
			res.append(fichier.next());
		}
		while(fichier.hasNext()){
			res.append(" | ");
			res.append(fichier.next());
		}
		res.append("  --- T = ");
		res.append(this.tauxremplissage);
		return res.toString();
	}

	public void recursiveToString(){
		System.out.println(this.toString());
		Iterator<Noeud<T>> it = this.pointeur.iterator();
		while(it.hasNext()){
			it.next().recursiveToString();
		}
	}

	private void debug(){
		if(this.isRacine())
			this.recursiveToString();
		else
			pere.debug();
	}
}
