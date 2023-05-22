import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String [] args) throws IOException {
		
		System.out.println("/*** Début Initialisation ***/");
		
		Scanner scanner = new Scanner(System.in);
		Fichier fichier = new Fichier("");
		Dictionnaire dico = new Dictionnaire();
		Traitement_Texte txt = new Traitement_Texte();
		String nom = "./Ressources/Livres/";
		
		System.out.println("/*** Initialisation en cours ... ***/");
		
		System.out.println("/*** Remplissage du dictionnaire en cours ... ***/");
		fichier.RemplirDictionnaire(dico,"./Ressources/Dico.txt");
		System.out.println("/*** Fin de remplissage du dictionnaire ***/");
		
		String choix = "";
		
		while (!choix.equalsIgnoreCase("exit")) {
		System.out.println("1 - nombre de mots :\n2 - traitement de texte :\nEND - exit :");
		choix = scanner.nextLine();
        
        	switch (choix.toLowerCase()) {
        		case "nombre de mots":
        			System.out.println("/*** Comptage en cours ... ***/");
        			System.out.println(dico.compterMots());
        			break;
        		case "traitement de texte":
        			System.out.println("/*** Traitement en cours ... ***/");
        			String choix2 = "";
        			while (!choix2.equalsIgnoreCase("exit")) {
        				System.out.println("1 - choix du fichier\n2 - mettre en minuscule\n3 - recherche de nouveau mot\n");
        				choix2 = scanner.nextLine();
        				switch (choix2.toLowerCase()) {
        					case "choix du fichier":
        						System.out.println("Quel est le nom du fichier a traiter ?");
        						nom += scanner.nextLine();
        						txt.setTexte(fichier.LireFichier(nom));
        						break;
        					case "mettre en minuscule":
        						txt.setTexte(txt.MajToMin());
        						break;
        					case "recherche de nouveau mot":
        						ArrayList<String> mots = txt.rechercheNouveauMot(dico);
        						for (String mot : mots) {
        				            System.out.println(mot);
        				        }
        						break;
        					default:
        						break;
        				}
        			}
        			break;
        		default:  
        			break;
        	}
		}
		scanner.close();
		System.out.println("/*** Fin Initialisation ***/");
	}
	
	public static void pause() {
		try {
            Thread.sleep(10000); // 10000 millisecondes = 10 secondes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
