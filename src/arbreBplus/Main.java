package arbreBplus;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Donner l'ordre de votre arbre : ");
		int ordre = sc.nextInt();
		Arbre<Integer> arbre = new Arbre<>(ordre);
		
		boolean sortie = false;
		while(!sortie){
			System.out.println("Actions faisables : \n"
					+ "1 - Charger un Fichier.\n"
					+ "2 - Insérer une Valeur.\n"
					+ "3 - Voir l'Arbre. \n"
					+ "4 - Supprimer une Valeur.\n"
					+ "5 - Quitter.");
			
			int choix = sc.nextInt();
			
			switch(choix){
			case 1 : 
				System.out.println("Inséré un nom de fichier dans lesquels sont contenus vos valeurs.");
				String fichier = sc.next();
				try {
					FileReader fis = new FileReader(fichier);
					BufferedReader br = new BufferedReader(fis);
					String text;
					while((text= br.readLine()) != null){
						Integer nombre = Integer.parseInt(text);
						try {
							arbre.ajouterData(nombre);
						} catch (ObjectAlreadyExistsException e) {
							System.out.println("Valeur déjà existante.");
						}
					}
					arbre.recursiveToString();
					System.out.println("Taux de remplissage : " + arbre.calculTaux());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				break;
			case 2 :
				System.out.println("Valeur à inséré : ");
				Integer val = sc.nextInt();
				try {
					arbre.ajouterData(val);
				} catch (ObjectAlreadyExistsException e) {
					System.out.println("Valeur déjà existante.");
				}
				break;
			case 3 :
				arbre.recursiveToString();
				System.out.println("Taux de remplissage : " + arbre.calculTaux());
				break;
			case 4 :
				System.out.println("Valeur à supprimer : ");
				Integer valsupp = sc.nextInt();
				arbre.supprimerData(valsupp);// FIXME si pas bon
				break;
			case 5 : 
				sortie = true;
				break;
			}

		}
	}

}