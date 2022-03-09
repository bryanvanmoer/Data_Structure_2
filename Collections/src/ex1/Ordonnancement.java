package ex1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Ordonnancement {
	public static final int NIVEAU_PRIORITE_MAX=5;
	
	private HashMap<Integer, Deque<Tache>> ordonnancement;

	public Ordonnancement(){
		
		ordonnancement = new HashMap<Integer,Deque<Tache>>();
		for(int i=1 ; i<=NIVEAU_PRIORITE_MAX; i++) {
			ordonnancement.put(i, new ArrayDeque<Tache>());
		}
	}
	
	public void ajouterTache (String descriptif, int priorite){
		
		Tache t = new Tache(descriptif, priorite);
		ordonnancement.get(priorite).add(t);
	}
	
	//renvoie la tache prioritaire
	//renvoie null si plus de tache presente
	public Tache attribuerTache(){
		
		for(int i = NIVEAU_PRIORITE_MAX ; i > 0 ; i--) {
			
			if(!ordonnancement.get(i).isEmpty()) {
			 return ordonnancement.get(i).peekFirst();	
			}
		}
		return null;
	}
}
