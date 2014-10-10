package arbreBplus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Donner l'ordre de vos arbres : ");
		int ordre = sc.nextInt();
		
		//Map<String, ? extends Comparable<?>> arbres = new HashMap<>();

		Arbre<Integer> age = new Arbre<>(ordre);
		Arbre<String> nom = new Arbre<>(ordre);
		
		boolean sortie = false;
		/*
		while(!sortie){
			System.out.println("Actions faisables : \n"
					+ "1 - Charger un Fichier.\n"
					+ "2 - Voir les arbres. \n"
					+ "3 - Supprimer une Valeur.\n"
					+ "4 - Quitter.");
			
			int choix = sc.nextInt();
			
			switch(choix){
			case 1 : 
				System.out.println("Inserer le nom de dossier dans lesquels sont contenus vos valeurs.");
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
											nom.ajouterData(info[1]);
										}else{
											Integer data = Integer.parseInt(info[1]);
											age.ajouterData(data);
										}										
									} catch (ObjectAlreadyExistsException | NoeudNonFeuilleException e) {
										System.out.println("Valeur déjà existante.");
									}
								}
							}
						}
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				break;
			case 2 :
				age.recursiveToString();
				System.out.println("Taux de remplissage : " + age.calculTaux());
				nom.recursiveToString();
				System.out.println("Taux de remplissage : " + nom.calculTaux());				
				break;
			case 3 :
				System.out.println("Valeur à supprimer : ");
				Integer valsupp = sc.nextInt();
				try {
					age.supprimerData(valsupp); // FIXME si pas bon
				} catch (NoeudNonFeuilleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4 : 
				sortie = true;
				break;
			}
		} */
		
		System.out.println("Inserer le nom de dossier dans lesquels sont contenus vos valeurs.");
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
								//s	nom.ajouterData(info[1]);
								}else{
									Integer data = Integer.parseInt(info[1]);
									age.ajouterData(data);
								}										
							} catch (ObjectAlreadyExistsException | NoeudNonFeuilleException e) {
								System.out.println("Valeur déjà existante.");
							}
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		while(true){
		Integer valsupp = sc.nextInt();
		try {
			age.supprimerData(valsupp); // FIXME si pas bon
			age.recursiveToString();
			System.out.println("Taux de remplissage : " + age.calculTaux());
		} catch (NoeudNonFeuilleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}