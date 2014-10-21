package arbreBplus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Donner l'ordre de vos arbres : ");
		int ordre = 0;
		while(ordre < 1){
			try{
				ordre = sc.nextInt();
				if(ordre < 1)
					System.out.println("Erreur, nombre entier trop petit.");
			}catch(InputMismatchException e){
				System.out.println("Erreur, ce n'est pas un nombre entier, donner un nombre entier positif.");
				sc.next();
			}
		}
		Arbre<Integer> age = new Arbre<>(ordre);
		Arbre<String> nom = new Arbre<>(ordre);

		System.out.println("Inserer le nom du dossier dans lesquels sont contenus vos valeurs.");
		String dossierString = sc.next();
		try {
			final File dossier = new File(dossierString);
			if(dossier.isDirectory()){
				for(File file : dossier.listFiles()){
					if(file.isFile()){
						FileReader fis = new FileReader(file.getAbsoluteFile());
						BufferedReader br = new BufferedReader(fis);
						String text;
						while((text= br.readLine()) != null){
							try {
								text = text.trim();
								String[] info = text.split(":");
								String type = info[0].toLowerCase();
								if(type.equals("nom")){
									nom.ajouterData(info[1], file.getName());
								}else if(type.equals("age")){
									Integer data = Integer.parseInt(info[1]);
									age.ajouterData(data, file.getName());
								}										
							} catch (ObjectAlreadyExistsException | NoeudNonFeuilleException e) {
								System.out.println("Valeur déjà existante.");
							}
						}
						br.close();
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur lors de la lecture d'un fichier, Fichier introuvable.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur lors de la lecture d'un fichier, vérifiez les droits d'accès et l'intégralité du fichier.");

		}	

		boolean sortie = false;
		while(!sortie){
			System.out.println("Actions faisables : \n"
					+ "1 - Chercher le fichier contenant la référence.\n"
					+ "2 - Voir les arbres. \n"
					+ "3 - Supprimer une Valeur.\n"
					+ "4 - Quitter.");

			int choix = -1;
			while(choix > 4 || choix < 1){
				try{
					choix = sc.nextInt();
					if(choix > 4 && choix < 1)
						System.out.println("Erreur, le numero doit etre entre 1 et 4.");
				}catch(InputMismatchException e){
					System.out.println("Erreur, ce n'est pas un nombre entier, donner un nombre entier.");
					sc.next();
				}
			}
			switch(choix){
			case 1 : 
				System.out.println("Rechercher à partir de : 1) Age 	2) Nom");
				int selection = -1;
				while(selection > 2 || selection < 1){
					try{
						selection = sc.nextInt();
						if(selection > 2 || selection < 1)
							System.out.println("Erreur, le numero doit être entre 1 et 2.");
					}catch(InputMismatchException e){
						System.out.println("Erreur, ce n'est pas un nombre entier, donner un nombre entier positif.");
						sc.next();
					}
				}
				String fichier;
				if(selection == 1){
					System.out.println("Donner l'âge recherché :");
					boolean ok =  false;
					int ageRecherche = 0;
					while(!ok){
						try{
							ageRecherche= sc.nextInt();
							ok = true;
						}catch(InputMismatchException e){
							System.out.println("Erreur, ce n'est pas un nombre entier, donner un nombre entier.");
							sc.next();
						}
					}
					try {
						fichier = age.getNomFichier(ageRecherche);
						resultatRecherche(fichier, dossierString, sc);
					} catch (NoeudNonFeuilleException e) {
						System.out.println("Le programme a essayé de récupérer la valeur dans un noued non feuille.");
						e.printStackTrace();
					} catch (NonExistentObjectException e) {
						System.out.println("La valeur n'existe pas.");
					}
				}else{
					System.out.println("Donner le nom recherché :");
					sc.nextLine();
					String nomRecherche = sc.nextLine();
					try {
						fichier = nom.getNomFichier(nomRecherche);
						resultatRecherche(fichier, dossierString, sc);
					} catch (NoeudNonFeuilleException e) {
						System.out.println("Le programme a essayé de récupérer la valeur dans un noued non feuille.");
						e.printStackTrace();
					} catch (NonExistentObjectException e) {
						System.out.println("La valeur n'existe pas.");
					}
				}
				break;
			case 2 :
				age.recursiveToString();
				System.out.println("Taux de remplissage : " + age.calculTaux());
				nom.recursiveToString();
				System.out.println("Taux de remplissage : " + nom.calculTaux());				
				break;
			case 3 :
				System.out.println("Supprimer à partir de : 1) Age 	2) Nom");
				selection = -1;
				while(selection > 2 || selection < 1){
					try{
						selection = sc.nextInt();
						if(selection > 2 || selection < 1)
							System.out.println("Erreur, le numero doit etre entre 1 et 2.");
					}catch(InputMismatchException e){
						System.out.println("Erreur, ce n'est pas un nombre entier, donner un nombre entier positif.");
						sc.next();
					}
				}
				if(selection == 1){
					System.out.println("Age a supprimer : ");
					Integer valsupp = sc.nextInt();
					try {
						age.supprimerData(valsupp);
						System.out.println(valsupp + " a été supprimé.");
					} catch (NoeudNonFeuilleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					System.out.println("Nom à supprimer : ");
					sc.nextLine();
					String valsupp = sc.nextLine();
					try {
						nom.supprimerData(valsupp);
						System.out.println(valsupp + " a été supprimé.");
					} catch (NoeudNonFeuilleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
				break;
			case 4 : 
				sortie = true;
				break;
			}
		} 
	}

	public static void resultatRecherche(String fichier, String dossier, Scanner sc){
		System.out.println("Fichier (ligne) :" + fichier);
		System.out.println("Lire le fichier ? (O/n)");
		if(sc.nextLine().equals("n")){
			try {
				File file = new File(dossier + File.separator + fichier);
				FileReader fis = new FileReader(file.getAbsoluteFile());
				BufferedReader br = new BufferedReader(fis);
				String text;
				while((text= br.readLine()) != null){
					System.out.println(text);
				}
				br.close();
			} catch (FileNotFoundException e1) {
				System.out.println("Fichier manquant, base de données corrompue.");
				e1.printStackTrace();
			} catch (IOException e) {
				System.out.println("Erreur de lecture.");
				e.printStackTrace();
			}
		}
	}
}
