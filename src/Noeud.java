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
	Noeud(int ordre){
		this.ordre = ordre;
		this.racine = true;
		this.id = NEXT_ID;
		NEXT_ID++;
	}

	Noeud(int ordre, Noeud<T> pere){
		this.ordre = ordre;
		this.pere = pere;
		this.racine = false;
		this.id = NEXT_ID;
		NEXT_ID++;
	}

	// Méthodes 
	public void split(){

		if(this.isRacine() == true){ //Cas ou je split la racine
			int nbvaleur = this.getOrdre() +1;
			Noeud<T> fils1 = new Noeud<T>(this.getOrdre(), this);
			Noeud<T> fils2 = new Noeud<T>(this.getOrdre(), this);

			int pair = nbvaleur % 2;
			this.copierValeurDansFils(fils1, fils2, nbvaleur/2+pair);

			fils1.mettreAJourTauxDeRemplissage();
			fils2.mettreAJourTauxDeRemplissage();
			
			if(!this.feuille){
				this.copierPointeurDansFils(fils1, fils2, nbvaleur/2+pair);
			}
			this.getPointeur().add(0, fils1);
			this.getPointeur().add(1, fils2);
		}else{
			
		}
	}
	
	private void copierValeurDansFils(Noeud<T> fils1, Noeud<T> fils2, int limit){
		Iterator<T> it = this.valeur.iterator();
		int actuel = 0;
		while(it.hasNext()){
			T valeur = it.next();
			if(actuel < limit){
				if(actuel != limit - 1 ){
					it.remove();
					fils1.getValeur().add(valeur);
				}
			}else{
				it.remove();
				fils2.getValeur().add(valeur);
			}
			actuel++;
		}
	}

	private void copierPointeurDansFils(Noeud<T> fils1, Noeud<T> fils2, int limit){
		for(int i = 0; i < limit; i++){
			fils1.addNoeud(i, this.pointeur.get(i));
		}
		for(int i = limit; i < this.pointeur.size(); i++){
			fils2.addNoeud(i - limit,this.pointeur.get(i));
		}
		this.pointeur.clear();
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
