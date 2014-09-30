package arbreBplus;

public class Taux {

	private double sommeTaux = 0;
	private double nombreNoeud = 0;
	
	public double getTaux(){
		if(nombreNoeud == 0)
			return 0;
		else
			return sommeTaux/nombreNoeud;
	}
	
	public void addTaux(double taux){
		this.sommeTaux += taux;
		this.nombreNoeud++;
	}
	
}
