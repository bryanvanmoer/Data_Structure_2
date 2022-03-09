package ex3;

import java.time.LocalTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.MILLIS;

public class LocationPatins {
	
	private Map<Integer, Queue<Integer>> pointures;
	private Map<Integer, LocalTime> casierUtilises;
	
	public LocationPatins(int[] casiers) {
		this.casierUtilises = new HashMap<Integer, LocalTime>();
		this.pointures = new HashMap<Integer,Queue<Integer>>();
		
		for(int i = 0; i < casiers.length; i++) {
			if(!pointures.containsKey(casiers[i])) {
				pointures.put(casiers[i], new ArrayDeque<Integer>());
			}
			pointures.get(casiers[i]).add(i);
		}
	}

	// date1 < date2
	private static double prix(LocalTime date1, LocalTime date2) {
		// 1 euro par milliseconde (c'est assez cher en effet)
		return MILLIS.between(date1, date2) ;
	}

	public int attribuerCasierAvecPatins(int pointure) {
		if (pointure < 33 || pointure > 48)
			throw new IllegalArgumentException();
		LocalTime l = LocalTime.now();
		
		
		//a compléter
		if(!pointures.get(pointure).isEmpty() && pointures.containsKey(pointure)) {
			int aRenvoyer = pointures.get(pointure).remove();
			casierUtilises.put(aRenvoyer, l);
			return aRenvoyer;
		}
		return -1;
		
	}

	public double libererCasier(int numeroCasier) {
		//a completer
		return prix(casierUtilises.get(numeroCasier), LocalTime.now());
	}

}
