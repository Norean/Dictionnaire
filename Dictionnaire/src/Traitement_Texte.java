import java.util.ArrayList;

public class Traitement_Texte {
	
	/*** Attributs ***/
	
	private ArrayList<String> texte;
	
	/*** Constructeurs ***/
	
	public Traitement_Texte() {
		this.texte = new ArrayList<String>();
	}
	
	/*** Getters et Setters ***/
	
	public ArrayList<String> getTexte() {return texte;}
	public void setTexte(ArrayList<String> texte) {this.texte = texte;}
	
	/*** Methodes ***/
	
	// Met le contenu d'un texte en minuscule
	public ArrayList<String> MajToMin() {
		ArrayList<String> minuscule = new ArrayList<String>();
		for(int i=0; i<getTexte().size(); i++) {
			minuscule.add(getTexte().get(i).toLowerCase());
		}
		return minuscule;
	}
	
	//recherche les mots dans le dictionnaire et met dans un ArrayList les mots que le dictionnaire ne connait pas
	public ArrayList<String> rechercheNouveauMot(Dictionnaire dico){
		ArrayList<String> liste =  new ArrayList<String>();
		for(String mot : this.getTexte()) {
			if(dico.rechercherMot(mot))
				liste.add(mot);
		}
		return liste;
	}
	
	//Permet d'afficher le contenu d'un arraylist
	public void afficherArrayList() {
        for (String element : this.getTexte()) {
            System.out.println(element);
        }
    }
	
	/*** Overrides ***/
	
}
