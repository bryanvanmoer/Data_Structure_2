package ex4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ChoixOptions {
	
	//Comparator Etudiant
	private static Comparator<Etudiant> comparateur = new Comparator<Etudiant>() {
		public int compare(Etudiant e1, Etudiant e2) {
			int moyenne1 = e1.getMoyenne();
			int moyenne2 = e2.getMoyenne();
			double difference = moyenne2 - moyenne1;
			if(difference==0) return e1.getNom().compareTo(e2.getNom());
			else return (int) Math.signum(difference);
		};
	};
	
	// associe le nom d'une option avec son objet Option correspondant
	private Map<String, Option> options;
	// ajouter ici les autres attributs
	private Map<Etudiant, List<String>> preferencesEtudiants;
	
	
	//constructeur prenant un entier et une suite de string en param�tres
	//ces string repr�sentent les noms des diff�rentes options possibles
	public ChoixOptions(int nbEtudiantsParOption, String... nomsOption) {
		this.options = new HashMap<String, Option>();
		if (nomsOption.length < 3)
			throw new IllegalArgumentException();
		for (int i = 0; i < nomsOption.length; i++) {
			String nomOption = nomsOption[i];
			options.put(nomOption, new Option(nomOption, nbEtudiantsParOption));
		}
		// initialiser les nouveaux attributs
		this.preferencesEtudiants = new TreeMap<>(comparateur);
	}

	// cette m�thode encode les pr�f�rences des �tudiants
	// il ne faut pas v�rifier que ces choix soient valides
	public void ajouterPreferences(Etudiant etu, String choix1, String choix2,
			String choix3) {
		List<String> preferences = new ArrayList<String>();
		preferences.add(choix1);
		preferences.add(choix2);
		preferences.add(choix3);
		preferencesEtudiants.put(etu, preferences);
	}

	// cette m�thode est appel�e apr�s que les �tudiants aient donn� leurs pr�f�rences
	// cette m�thode attribue les options aux �tudiants en favorisant les �tudiants 
	// ayant les meilleures moyennes si il n'y a plus de place disponible dans certaines 
	// options. Pour les �tudiants faibles, si les deux premi�res options sont pleines, 
	// il faut recourir au troisi�me choix.
	// Cette m�thode doit faire appel � la m�thode inscrireEtudiant de la classe Option.
	public void attribuerOptions() {
		//On proc�de � l'attribution des options 
		for(Etudiant e : preferencesEtudiants.keySet()) {
			//On recupere la liste des preferences de l'etudiant
			List<String> listePreferences = preferencesEtudiants.get(e);
			//On parcourt la liste des preference de l'etudiant
			for(String s : listePreferences) {
				//On recupere l'option via la cl� String de la map options
				Option o = options.get(s);
				//Si l'inscription est valide
				if(o.inscrireEtudiant(e)) {
					//Alors on passe � l'autre �tudiant
					break;
				}
			}
		}
	}
	
	public String toString(){
		String s="";
		for (Option o:options.values()){
			s=s+o+"\n"+"-----------------"+"\n";
		}
		return s;
	}
}
