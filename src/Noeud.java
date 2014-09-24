import java.util.ArrayList;

public class Noeud<T extends Comparable<T>> {
	
	// Attributs
	private int ordre;
	private double tauxremplissage = 0;
	private ArrayList<T> valeur = new ArrayList<T>();
	private ArrayList<T> pointeur = new ArrayList<T>();
	private boolean feuille;
	private boolean racine;
	private Noeud Pere;
	
	// Constructeur
	Noeud(int o){
		ordre = o;
		racine = true;
	}
	
	Noeud(int o, Noeud p){
		ordre = o;
		Pere = p;
		racine = false;
	}
	
	// Méthodes 
	public void split(Noeud aspliter){
		
		if(aspliter.isRacine() == true){ //Cas ou je split la racine et que racine = feuille. 
			int nbvaleur = aspliter.getOrdre() +1;
			Noeud fils1 = new Noeud(aspliter.getOrdre(), aspliter);
			fils1.setFeuille(true);
			Noeud fils2 = new Noeud(aspliter.getOrdre(), aspliter);
			fils2.setFeuille(true);
			
			
			if(nbvaleur % 2 == 0){
				for(int i = 0; i<=nbvaleur/2; i++){
					fils1.getValeur().add(aspliter.getValeur().get(i));
					fils1.setTauxremplissage(fils1.getValeur().size()/fils1.getOrdre());
					if(i != nbvaleur){
						aspliter.getValeur().remove(i);
					}
				}
				for(int j = nbvaleur/2+1; j==nbvaleur;j++){
					fils2.getValeur().add(aspliter.getValeur().get(j));
					fils2.setTauxremplissage(fils2.getValeur().size()/fils1.getOrdre());
				}
				aspliter.getPointeur().set(0, fils1);
				aspliter.getPointeur().set(1, fils2);
			}
			
			if(nbvaleur % 2 != 0){
				//TODO
			}
		}else{
			//TODO
		}
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

	public ArrayList<T> getPointeur() {
		return pointeur;
	}

	public void setPointeur(ArrayList<T> pointeur) {
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

	public Noeud getPere() {
		return Pere;
	}

	public void setPere(Noeud pere) {
		Pere = pere;
	}
	
}
