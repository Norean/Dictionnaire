import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Fichier {

	/*** Attributs ***/
	
	private BufferedReader lecteur;
	private BufferedWriter writer;
	private String chemin;
	
	/*** Constructeurs ***/
	
	public Fichier(String cheminFichier) throws IOException{
		this.chemin = cheminFichier;
	}
	
	/*** Getters et Setters ***/
	
	public BufferedReader getLecteur() {return lecteur;}
	public void setLecteur() throws FileNotFoundException {this.lecteur = new BufferedReader(new FileReader(this.getChemin()));}
	public BufferedWriter getWriter() {return this.writer;}
	public void setWriter() throws IOException {this.writer = new BufferedWriter(new FileWriter(this.getChemin()));}
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
	
	//sauvegarde le dictionnaire en écrasant le précédent
	public void SauvegarderDictionnaire(Dictionnaire dico) throws IOException {
		ArrayList<String> data = dico.Save();
		this.setChemin("./Ressources/SaveDico.txt");
		this.setWriter();
		for(String element : data) {
			this.getWriter().write(element);
		}
	}
	
	//renvoie le contenu d'un fichier dans un arraylist<String>
	public ArrayList<String> LireFichier(String chemin) throws IOException {
		String ligne;
		this.setChemin(chemin);
		this.setLecteur();
		ArrayList<String> fichier = new ArrayList<String>();
		while((ligne = this.getLecteur().readLine()) != null) {
			String[] mots = ligne.split(" ");
			for(String str : mots) {
				fichier.add(str);
			}
		}
		return fichier;
	}
	
	/*** Overrides ***/
	
}
