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
		Arbre<Integer> arbre = new Arbre<>(100);
		
		try {
			FileReader fis = new FileReader("nombre.txt");
			BufferedReader br = new BufferedReader(fis);
			String text;
			while((text= br.readLine()) != null){
				Integer nombre = Integer.parseInt(text);
				try {
					arbre.ajouterData(nombre);
				} catch (ObjectAlreadyExistsException e) {
					System.out.println("Valeur déjà existante");
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
	}

}