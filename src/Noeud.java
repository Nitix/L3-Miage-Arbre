import java.util.ArrayList;

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
		
		if(this.isRacine() == true){ //Cas ou je split la racine et que racine = feuille. 
			int nbvaleur = this.getOrdre() +1;
			Noeud<T> fils1 = new Noeud<T>(this.getOrdre(), this);
			Noeud<T> fils2 = new Noeud<T>(this.getOrdre(), this);
			
			int pair = nbvaleur % 2;
			for(int i = 0; i<=nbvaleur/2+pair-1; i++){
				fils1.getValeur().add(this.getValeur().get(i));
				if(i != nbvaleur){
					this.getValeur().remove(i);
				}
			}
			for(int j = nbvaleur/2+pair; j<nbvaleur;j++){
				fils2.getValeur().add(this.getValeur().get(j));
				this.getValeur().remove(j);
			}
			
			fils1.mettreAJourTauxDeRemplissage();
			fils2.mettreAJourTauxDeRemplissage();
			
			
			this.getPointeur().set(0, fils1);
			this.getPointeur().set(1, fils2);
		}else{
			//TODO
		}
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
	
}
