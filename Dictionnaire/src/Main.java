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
		
		int choix = 0;
		
		while (choix != 4) {
		System.out.println("1 - nombre de mots :\n2 - afficher les X permiers mots du dictionnaire\n3 - traitement de texte :\n4 - exit :");
		choix = scanner.nextInt();
        
        	switch (choix) {
        		case 1:
        			System.out.println("/*** Comptage en cours ... ***/");
        			System.out.println(dico.compterMots());
        			break;
        		case 2:
        			System.out.println("Combien de mots voulez vous affichez ?");
        			int X = scanner.nextInt();
        			dico.afficherXMots(X);
        			break;
        		case 3:
        			System.out.println("/*** Traitement en cours ... ***/");
        			int choix2 = 0;
        			while (choix2 != 3) {
        				System.out.println("1 - Definir le fichier a utiliser\n2 - recherche de nouveau mot\n3 - Sauvegarde du dictionnaire\n4 - exit :");
        				choix2 = scanner.nextInt();
        				switch (choix2) {
        					case 1:
        						System.out.println("Quel est le nom du fichier a traiter ?");
        						nom += scanner.next();
        						txt.setTexte(fichier.LireFichier(nom));
        						txt.MajToMin();
        						txt.SupprSpe();
        						break;
        					case 2:
        						ArrayList<String> mots = txt.rechercheNouveauMot(dico);
        						for (String mot : mots) {
        				            System.out.println(mot);
        				        }
        						break;
        					case 3:
        						fichier.SauvegarderDictionnaire(dico);
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
