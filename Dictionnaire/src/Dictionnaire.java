import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Dictionnaire {

	/*
	
	2 - phono : les formes phonologique du mot
	3 - lemme : les lemmes de ce mot
	4 - cgram : les catégories grammaticales de ce mot
	5 - genre : le genre
	6 - nombre : le nombre
	7 - freqlemfilms : la frequence du lemme selon le corpus de sous-titres
	8 - freqlemlivres : la frequence du lemme selon le corpus de livre
	9 - freqfilms : la frequence du mot selon le corpus de sous-titre
	10 - freqlivres : la frequence du mot selon le corpus de livres
	11 - infover : modes, temps, et personnes possibles pour les verbes
	12 - nbhomogr : nombre d'homographes
	13 - nbhomoph : nombre d'homophones
	14 - islem : indique si c'est un lemme ou pas
	15 - nblettres : le nombre de lettre
	16 - nbphono : nombre de phonèmes
	17 - cvcv : la structure orthographique
	18 - p_cvcv : la structure phonologique
	19 - voisorth : nombre de voisins orthographiques
	20 - voisphono : nombre de voisins phonologiques
	21 - puorth : point d'unicité orthographique
	22 - puphono : point d'unicité phonologique
	23 - syll : forme phonologique syllabée
	24 - nbsyll : nombre de syllabes
	25 - cv-cv : structure phonologique syllabée
	26 - orthrenv : forme orthographique inversée
	27 - phonrenv : forme phonologique inversée
	28 - orthosyll : forme orthographique syllabée
	
	ADJ Adjectif
	ADJ:dem Adjectif démonstratif
	ADJ:ind Adjectif indéfini
	ADJ:int Adjectif interrogatif
	ADJ:num Adjectif numérique
	ADJ:pos Adjectif possessif
	ADV Adverbe
	ART:def Article défini
	ART:inf Article indéfini
	AUX Auxiliaire
	CON Conjonction
	LIA Liaison euphonique (l')
	NOM Nom commun
	ONO Onomatopée
	PRE Préposition
	PRO:dem Pronom démonstratif
	PRO:ind Pronom indéfini
	PRO:int Pronom interrogatif
	PRO:per Pronom personnel
	PRO:pos Pronom possessif
	PRO:rel Pronom relatif
	VER Verbe
	
	*/
	
	/*** Attributs Dictionnaire ***/
	
	private Noeud racine;
	
	/*** Constructeurs Dictionnaires ***/
	
	public Dictionnaire() {
		this.racine = new Noeud('\0');
	}
	
	/*** Getters et Setters Dictionnaires ***/
	
    public Noeud getRacine() {return racine;}
	public void setRacine(Noeud racine) {this.racine = racine;}
	
	/*** Methodes Dictionnaires ***/
	
	// Ajoute un mot dans l'arbre avec le code POS depuis une HashMap
    public void ajouterMot(ArrayList<Object> ligne) {
    	ajouterMotRecursif(ligne, this.getRacine(), ligne.get(0).toString());
    }

	// Ajoute un mot dans l'arbre récursivement
    private void ajouterMotRecursif(ArrayList<Object> data, Noeud noeud, String Fmot) {
    	String mot = data.get(0).toString();
        if (mot.isEmpty()) {
        	noeud.incrementOccurence();
        	noeud.getOccurence().add(Occurence(data, Fmot));
            return;
        }
        char lettre = mot.charAt(0);
        Noeud enfant = chercherEnfant(noeud, lettre);
        if (enfant == null) {
            enfant = new Noeud(lettre);
            noeud.getEnfants().add(enfant);
        }
        data.set(0, data.get(0).toString().substring(1));
        ajouterMotRecursif(data, enfant, Fmot);
    }

    public boolean rechercherMot(String mot) {
    	boolean tmp = rechercherMotRecursivement(mot, this.getRacine());
    	return tmp;
    }
    
    private boolean rechercherMotRecursivement(String mot, Noeud noeud) {
    	if(mot.isEmpty()) {
    		if(noeud.getNbOccurence() > 0)
        		return true;
    	}else {
        	Noeud node = chercherEnfant(this.getRacine(), mot.charAt(0));
        	if( node != null)
        		return !rechercherMotRecursivement(mot.substring(1), node);
    	}
    	return false;
    }
    
    // Cherche un enfant du noeud donné avec une lettre donnée
    private Noeud chercherEnfant(Noeud noeud, char lettre) {
        for (Noeud enfant : noeud.getEnfants()) {
            if (enfant.getLettre() == lettre) {return enfant;}
        }
        return null;
    }
    
    // Vérifie si un mot existe dans le Dictionnaire
    public boolean contientMot(String mot) {
        Noeud noeudCourant = this.getRacine();
        for (int i = 0; i < mot.length(); i++) {
            char lettre = mot.charAt(i);
            Noeud enfant = chercherEnfant(noeudCourant, lettre);
            if (enfant == null) {
                return false;
            }
            noeudCourant = enfant;
        }
        return noeudCourant.isFinMot();
    }
    
    // Affiche tous les mots de l'arbre
    public void afficherMots() {
        afficherMotsRecursif(this.getRacine(), "");
    }

    // Affiche tous les mots de l'arbre récursivement
    private void afficherMotsRecursif(Noeud noeud, String prefixe) {
    	if(noeud.getOccurence().size() >= 1) {
    		for(int k=0; k<noeud.getNbOccurence();k++) {
            	System.out.println(prefixe + " (" + " Occ : " + noeud.getNbOccurence() + noeud.getOccurence().get(k).getphono() + " " + noeud.getOccurence().get(k).getLemme() + " " + noeud.getOccurence().get(k).getCgram() + " " + noeud.getOccurence().get(k).getGenre() + " " + noeud.getOccurence().get(k).getNombre() + " " + noeud.getOccurence().get(k).getFreqlemfilms() + " " + noeud.getOccurence().get(k).getFreqlemlivres() + " " + noeud.getOccurence().get(k).getFreqfilms() +
                   		" " + noeud.getOccurence().get(k).getFreqlivres() + " " + noeud.getOccurence().get(k).getInfover() + " " + noeud.getOccurence().get(k).getNbhomogr() + " " + noeud.getOccurence().get(k).getNbhomoph() + " " + noeud.getOccurence().get(k).getIslem() + " " + noeud.getOccurence().get(k).getNblettre() + " " + noeud.getOccurence().get(k).getNbphono() + " " + noeud.getOccurence().get(k).getCvcv() + " " + noeud.getOccurence().get(k).getP_cvcv() + " " +
                   		noeud.getOccurence().get(k).getVoisortho() + " " + noeud.getOccurence().get(k).getVoisphono() + " " + noeud.getOccurence().get(k).getPuortho() + " " + noeud.getOccurence().get(k).getPuphono() + " " + noeud.getOccurence().get(k).getSyll() + " " + noeud.getOccurence().get(k).getNbsyll()   + " " + noeud.getOccurence().get(k).getCv_cv() + " " + noeud.getOccurence().get(k).getOrthrenv() + " " + noeud.getOccurence().get(k).getPhonrenv() + " " +
                   		noeud.getOccurence().get(k).getOrthosyll() + ")");
            }
        }
        for (Noeud enfant : noeud.getEnfants()) {
            afficherMotsRecursif(enfant, prefixe + enfant.getLettre());
        }
    }
    
    // Affiche tous les mots de l'arbre
    public void afficherXMots(int X) {
        afficherXMotsRecursif(new AtomicInteger(0),X,this.getRacine(), "");
    }
    
    // Affiche tous les mots de l'arbre récursivement
    private void afficherXMotsRecursif(AtomicInteger i,int X,Noeud noeud, String prefixe) {
    	if(noeud.getOccurence().size() >= 1) {
    		if (i.get()<X) {
    			i.incrementAndGet();
    			for(int k=0; k<noeud.getNbOccurence();k++) {
            		System.out.println(prefixe + " (" + " Occ : " + noeud.getNbOccurence() + noeud.getOccurence().get(k).getphono() + " " + noeud.getOccurence().get(k).getLemme() + " " + noeud.getOccurence().get(k).getCgram() + " " + noeud.getOccurence().get(k).getGenre() + " " + noeud.getOccurence().get(k).getNombre() + " " + noeud.getOccurence().get(k).getFreqlemfilms() + " " + noeud.getOccurence().get(k).getFreqlemlivres() + " " + noeud.getOccurence().get(k).getFreqfilms() +
                    		" " + noeud.getOccurence().get(k).getFreqlivres() + " " + noeud.getOccurence().get(k).getInfover() + " " + noeud.getOccurence().get(k).getNbhomogr() + " " + noeud.getOccurence().get(k).getNbhomoph() + " " + noeud.getOccurence().get(k).getIslem() + " " + noeud.getOccurence().get(k).getNblettre() + " " + noeud.getOccurence().get(k).getNbphono() + " " + noeud.getOccurence().get(k).getCvcv() + " " + noeud.getOccurence().get(k).getP_cvcv() + " " +
                    		noeud.getOccurence().get(k).getVoisortho() + " " + noeud.getOccurence().get(k).getVoisphono() + " " + noeud.getOccurence().get(k).getPuortho() + " " + noeud.getOccurence().get(k).getPuphono() + " " + noeud.getOccurence().get(k).getSyll() + " " + noeud.getOccurence().get(k).getNbsyll()   + " " + noeud.getOccurence().get(k).getCv_cv() + " " + noeud.getOccurence().get(k).getOrthrenv() + " " + noeud.getOccurence().get(k).getPhonrenv() + " " +
                    		noeud.getOccurence().get(k).getOrthosyll() + ")");
            	}
            }
    	}
        for (Noeud enfant : noeud.getEnfants()) {
            afficherXMotsRecursif(i,X,enfant, prefixe + enfant.getLettre());
        }
    }
    
    // Compte tout les mots de l'arbre
    public int compterMots() {
        return compterMotsRecursif(this.getRacine());
    }

    // Compte tout les mots de l'arbre recursivement
    private int compterMotsRecursif(Noeud noeud) {
        int compteur = (noeud.getOccurence().size() >= 1) ? noeud.getNbOccurence() : 0;
        for (Noeud enfant : noeud.getEnfants()) {
            compteur += compterMotsRecursif(enfant);
        }
        return compteur;
    }
    
    // crée une occurence du dernier noeud de chaque mot avec ses donnée associé
    public Noeud Occurence(ArrayList<Object> data, String FirstElement) {
    	Noeud node = new Noeud(FirstElement.charAt(FirstElement.length()-1));
    	node.setFinMot(true);
		if (data.get(1).toString().charAt(0) != '$')
			node.setphono(data.get(2).toString());
        if (data.get(2).toString().charAt(0) != '$')
        	node.setLemme(data.get(2).toString());
        if (data.get(3).toString().charAt(0) != '$')
            node.setCgram(data.get(3).toString());
        if (data.get(4).toString().charAt(0) != '$')
           	node.setGenre(data.get(4).toString().charAt(0));
        if (data.get(5).toString().charAt(0) != '$')
            node.setNombre(data.get(5).toString().charAt(0));
        if (data.get(6).toString().charAt(0) != '$')
        	node.setFreqlemfilms(Double.parseDouble(data.get(6).toString()));
        if (data.get(7).toString().charAt(0) != '$')
        	node.setFreqlemlivres(Double.parseDouble(data.get(7).toString()));
        if (data.get(8).toString().charAt(0) != '$')
        	node.setFreqfilms(Double.parseDouble(data.get(8).toString()));
        if (data.get(9).toString().charAt(0) != '$')
        	node.setFreqlivres(Double.parseDouble(data.get(9).toString()));
        if (data.get(10).toString().charAt(0) != '$')
        	node.setInfover(data.get(10).toString());
        if (data.get(11).toString().charAt(0) != '$')
        	node.setNbhomogr(Integer.parseInt(data.get(11).toString()));
        if (data.get(12).toString().charAt(0) != '$')
        	node.setNbhomoph(Integer.parseInt(data.get(12).toString()));
        if (data.get(13).toString().charAt(0) != '$')
        	node.setIslem(Integer.parseInt(data.get(13).toString()));
        if (data.get(14).toString().charAt(0) != '$')
        	node.setNblettre(Integer.parseInt(data.get(14).toString()));
        if (data.get(15).toString().charAt(0) != '$')
        	node.setNbphono(Integer.parseInt(data.get(15).toString()));
        if (data.get(16).toString().charAt(0) != '$')
        	node.setCvcv(data.get(16).toString());
        if (data.get(17).toString().charAt(0) != '$')
        	node.setP_cvcv(data.get(17).toString());
        if (data.get(18).toString().charAt(0) != '$')
        	node.setVoisortho(Integer.parseInt(data.get(18).toString()));
        if (data.get(19).toString().charAt(0) != '$')
        	node.setVoisphono(Integer.parseInt(data.get(19).toString()));
        if (data.get(20).toString().charAt(0) != '$')
        	node.setPuortho(Integer.parseInt(data.get(20).toString()));
        if (data.get(21).toString().charAt(0) != '$')
        	node.setPuphono(Integer.parseInt(data.get(21).toString()));
        if (data.get(22).toString().charAt(0) != '$')
        	node.setSyll(data.get(22).toString());
        if (data.get(23).toString().charAt(0) != '$')
        	node.setNbsyll(Integer.parseInt(data.get(23).toString()));
        if (data.get(24).toString().charAt(0) != '$')
        	node.setCv_cv(data.get(24).toString());
        if (data.get(25).toString().charAt(0) != '$')
        	node.setOrthrenv(data.get(25).toString());
        if (data.get(26).toString().charAt(0) != '$')
        	node.setPhonrenv(data.get(26).toString());
        if (data.get(27).toString().charAt(0) != '$')
        	node.setOrthosyll(data.get(27).toString());
        
        return node;
    }
	
	/*** Override Dictionnaires ***/
	
    /**** ----------------------------------------------------------------------------------------------------------- ****/
    
	private static class Noeud {
		
		/*** Attributs Noeud ***/
			
		private char lettre;
		private ArrayList<Noeud> enfants;
        private boolean finMot;
        
        private ArrayList<Noeud> occurence;
        private int nboccurence;
        
        private String phono;
        private String lemme;
        private String cgram;
        private char genre;
        private char nombre;
        private double freqlemfilms;
        private double freqlemlivres;
        private double freqfilms;
        private double freqlivres;
        private String infover;
        private int nbhomogr;
        private int nbhomoph;
        private int islem;
        private int nblettre;
        private int nbphono;
        private String cvcv;
        private String p_cvcv;
        private int voisortho;
        private int voisphono;
        private int puortho;
        private int puphono;
        private String syll;
        private int nbsyll;
        private String cv_cv;
        private String orthrenv;
        private String phonrenv;
        private String orthosyll;
        
        /*** Constructeurs Noeud ***/
        
        public Noeud (char lettre) {
        	
        	this.lettre = lettre;
        	this.enfants = new ArrayList<>();
        	this.finMot = false;
        	
        	this.occurence = new ArrayList<Noeud>();
        	this.nboccurence = 0;
        	
        	this.phono = "";
        	this.lemme = "";
        	this.cgram = "";
        	this.genre = '\0';
        	this.nombre = '\0';
        	this.freqlemfilms = 0.0;
        	this.freqlemlivres = 0.0;
        	this.freqfilms = 0.0;
        	this.freqlivres = 0.0;
        	this.infover = "";
        	this.nbhomogr = 0;
        	this.nbhomoph = 0;
        	this.islem = 0;
        	this.nblettre = 0;
        	this.nbphono = 0;
        	this.cvcv = "";
        	this.p_cvcv = "";
        	this.voisortho = 0;
        	this.voisphono = 0;
        	this.puortho = 0;
        	this.puphono = 0;
        	this.syll = "";
        	this.nbsyll = 0;
        	this.cv_cv = "";
        	this.orthrenv = "";
        	this.phonrenv = "";
        	this.orthosyll = "";
        }

		/*** Getters et Setters Noeud ***/
        
		public char getLettre() {return lettre;}
		public void setLettre(char lettre) {this.lettre = lettre;}
		public ArrayList<Noeud> getEnfants() {return enfants;}
		public void setEnfants(ArrayList<Noeud> enfants) {this.enfants = enfants;}
		public boolean isFinMot() {return finMot;}
		public void setFinMot(boolean finMot) {this.finMot = finMot;}

		public ArrayList<Noeud> getOccurence() {return occurence;}
		public int getNbOccurence() {return this.nboccurence;}
		public void incrementOccurence() {this.nboccurence++;}
		
		public String getphono() {return phono;}
		public void setphono(String phono) {phono = phono;}
		public String getLemme() {return lemme;}
		public void setLemme(String lemme) {this.lemme = lemme;}
		public String getCgram() {return cgram;}
		public void setCgram(String cgram) {this.cgram = cgram;}
		public char getGenre() {return genre;}
		public void setGenre(char genre) {this.genre = genre;}
		public char getNombre() {return nombre;}
		public void setNombre(char nombre) {this.nombre = nombre;}
		public double getFreqlemfilms() {return freqlemfilms;}
		public void setFreqlemfilms(double freqlemfilms) {this.freqlemfilms = freqlemfilms;}
		public double getFreqlemlivres() {return freqlemlivres;}
		public void setFreqlemlivres(double freqlemlivres) {this.freqlemlivres = freqlemlivres;}
		public double getFreqfilms() {return freqfilms;}
		public void setFreqfilms(double freqfilms) {this.freqfilms = freqfilms;}
		public double getFreqlivres() {return freqlivres;}
		public void setFreqlivres(double freqlivres) {this.freqlivres = freqlivres;}
		public String getInfover() {return infover;}
		public void setInfover(String infover) {this.infover = infover;}
		public int getNbhomogr() {return nbhomogr;}
		public void setNbhomogr(int nbhomogr) {this.nbhomogr = nbhomogr;}
		public int getNbhomoph() {return nbhomoph;}
		public void setNbhomoph(int nbhomoph) {this.nbhomoph = nbhomoph;}
		public int getIslem() {return islem;}
		public void setIslem(int islem) {this.islem = islem;}
		public int getNblettre() {return nblettre;}
		public void setNblettre(int nblettre) {this.nblettre = nblettre;}
		public int getNbphono() {return nbphono;}
		public void setNbphono(int nbphono) {this.nbphono = nbphono;}
		public String getCvcv() {return cvcv;}
		public void setCvcv(String cvcv) {this.cvcv = cvcv;}
		public String getP_cvcv() {return p_cvcv;}
		public void setP_cvcv(String p_cvcv) {this.p_cvcv = p_cvcv;}
		public int getVoisortho() {return voisortho;}
		public void setVoisortho(int voisortho) {this.voisortho = voisortho;}
		public int getVoisphono() {return voisphono;}
		public void setVoisphono(int voisphono) {this.voisphono = voisphono;}
		public int getPuortho() {return puortho;}
		public void setPuortho(int puortho) {this.puortho = puortho;}
		public int getPuphono() {return puphono;}
		public void setPuphono(int puphono) {this.puphono = puphono;}
		public String getSyll() {return syll;}
		public void setSyll(String syll) {this.syll = syll;}
		public int getNbsyll() {return nbsyll;}
		public void setNbsyll(int nbsyll) {this.nbsyll = nbsyll;}
		public String getCv_cv() {return cv_cv;}
		public void setCv_cv(String cv_cv) {this.cv_cv = cv_cv;}
		public String getOrthrenv() {return orthrenv;}
		public void setOrthrenv(String orthrenv) {this.orthrenv = orthrenv;}
		public String getPhonrenv() {return phonrenv;}
		public void setPhonrenv(String phonrenv) {this.phonrenv = phonrenv;}	
		public String getOrthosyll() {return orthosyll;}
		public void setOrthosyll(String orthosyll) {this.orthosyll = orthosyll;}
        
        /*** Methodes Noeud ***/
		
		public boolean estMot() {
			return (this.getNbOccurence() > 0) ? true : false;
		}
		
        /*** Override Noeud ***/
        
	}
}
