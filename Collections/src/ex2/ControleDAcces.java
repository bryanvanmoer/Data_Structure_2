package ex2;

import java.util.HashMap;
import java.util.HashSet;

public class ControleDAcces {
	
	private HashMap<Employe,Badge> mapBadgeEmploye;
	private HashSet<Badge> ensembleBadge;
	
	public ControleDAcces(){
		mapBadgeEmploye = new HashMap<Employe,Badge>();
		ensembleBadge = new HashSet<Badge>();
	}
	
	// associe le badge � un employ�
	public void donnerBadge (Badge b, Employe e){
		mapBadgeEmploye.put(e,b);
	}
	
	// met � jour les employ�s pr�sents dans le batiment
	public void entrerBatiment (Badge b){
		ensembleBadge.add(b);
	}

	// met � jour les employ�s pr�sents dans le batiment
	public void sortirBatiment (Badge b){
		ensembleBadge.remove(b);
	}
	
	// renvoie vrai si l'employ� est dans le batiment, faux sinon
	public boolean estDansBatiment (Employe e){
		
		Badge b = mapBadgeEmploye.get(e);
		if(ensembleBadge.contains(b)) {
			return true;
		}
		return false;
	}
}
