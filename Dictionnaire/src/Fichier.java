import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Fichier {

	/*** Attributs ***/
	
	private BufferedReader lecteur;
	private String chemin;
	
	/*** Constructeurs ***/
	
	public Fichier(String cheminFichier) throws IOException{
		this.chemin = cheminFichier;
	}
	
	/*** Getters et Setters ***/
	
	public BufferedReader getLecteur() {return lecteur;}
	public void setLecteur() throws FileNotFoundException {this.lecteur = new BufferedReader(new FileReader(this.getChemin()));}
	public String getChemin() {return chemin;}
	public void setChemin(String chemin) {this.chemin = chemin;}

	/*** Methodes ***/
	
	//charge le dictionnaire
	public void RemplirDictionnaire(Dictionnaire dico, String chemin) throws IOException{
		String ligne;
		this.setChemin(chemin);
		this.setLecteur();
		while((ligne = this.getLecteur().readLine()) != null) {
			String[] mots = ligne.split("\\|");
			ArrayList<Object> motsList = new ArrayList<>();
			motsList.addAll(Arrays.asList(mots));
			dico.ajouterMot(motsList);
		}
	}
	
	// renvoie le contenu d'un fichier dans un arraylist<String>
	public ArrayList<String> LireFichier(String chemin) throws IOException {
		String ligne;
		this.setChemin(chemin);
		this.setLecteur();
		ArrayList<String> fichier = new ArrayList<String>();
		while((ligne = this.getLecteur().readLine()) != null) {
			fichier.add(ligne);
		}
		return fichier;
	}
	
	/*** Overrides ***/
	
}
