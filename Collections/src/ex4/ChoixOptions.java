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
	
	
	//constructeur prenant un entier et une suite de string en paramètres
	//ces string représentent les noms des différentes options possibles
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

	// cette méthode encode les préférences des étudiants
	// il ne faut pas vérifier que ces choix soient valides
	public void ajouterPreferences(Etudiant etu, String choix1, String choix2,
			String choix3) {
		List<String> preferences = new ArrayList<String>();
		preferences.add(choix1);
		preferences.add(choix2);
		preferences.add(choix3);
		preferencesEtudiants.put(etu, preferences);
	}

	// cette méthode est appelée après que les étudiants aient donné leurs préférences
	// cette méthode attribue les options aux étudiants en favorisant les étudiants 
	// ayant les meilleures moyennes si il n'y a plus de place disponible dans certaines 
	// options. Pour les étudiants faibles, si les deux premières options sont pleines, 
	// il faut recourir au troisième choix.
	// Cette méthode doit faire appel à la méthode inscrireEtudiant de la classe Option.
	public void attribuerOptions() {
		//On procède à l'attribution des options 
		for(Etudiant e : preferencesEtudiants.keySet()) {
			//On recupere la liste des preferences de l'etudiant
			List<String> listePreferences = preferencesEtudiants.get(e);
			//On parcourt la liste des preference de l'etudiant
			for(String s : listePreferences) {
				//On recupere l'option via la clé String de la map options
				Option o = options.get(s);
				//Si l'inscription est valide
				if(o.inscrireEtudiant(e)) {
					//Alors on passe à l'autre étudiant
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
