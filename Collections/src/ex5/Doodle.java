package ex5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Doodle {
	private PlageHoraire[] plages;
	// a compl�ter
	private Map<PlageHoraire, Set<String>> horaires;

	public Doodle(PlageHoraire... plages) {
		this.plages = plages;
		// a compl�ter
		this.horaires = new HashMap<PlageHoraire, Set<String>>();
		
		for(int i=0 ; i<plages.length ; i++) {
			HashSet<String> participants = new HashSet<String>();
			horaires.put(plages[i], participants);
		}
	}

	// ajoute les disponibilit�s d'un participant sous forme d'un tableau de booleen.
	// les indices du tableau correspondent aux indices du tableau plages
	// true � l'indice i veut dire que le participant est disponible pour la plage � l'indice i du tableau plages
	// false � l'indice i veut dire que le participant n'est pas disponible pour la plage � l'indice i du tableau plages
	public void ajouterDisponibilites(String participant,
			boolean[] disponibilites) {
		if (disponibilites.length != plages.length)
			throw new IllegalArgumentException();
		//a compl�ter
		for(int i=0 ; i<disponibilites.length; i++) {
			if(disponibilites[i]) {
				horaires.get(plages[i]).add(participant);
			}
		}
	}
	
	// Hypoth�se: la PlageHoraire plage en param�tre fait bien partie du tableau plages
	// renvoie vrai si le participant est disponible pour cette plage horaire
	// renvoie faux si le participant n'est pas disponible ou s'il n'a pas rempli le
	// sondage doodle
	public boolean estDisponible(String participant, PlageHoraire plage) {
		return horaires.get(plage).contains(participant);
	}

	// renvoie une des plages horaires qui a le maximum de participants pr�vus
	// cette m�thode est appel�e apr�s que les participants aient rempli leurs disponibilit�s
	public PlageHoraire trouverPlageQuiConvientLeMieux() {
		PlageHoraire max = new PlageHoraire(null, null);
		for(Entry<PlageHoraire, Set<String>> horaires : horaires.entrySet()) {
			if(max.getNbParticipantPresent()<horaires.getValue().size()) {
				max = horaires.getKey();
				max.setNbParticipantPresent(horaires.getValue().size());
			}
		}
		return max;
	}

}
