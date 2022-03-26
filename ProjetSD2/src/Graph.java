import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Graph {
	
	public Map<String,Airport> airports;
	public Map<String, Set<Flight>> exitingFlights;
	
	public Graph(File airportsFile, File flightsFile) {
		airports = new HashMap<String, Airport>();
		exitingFlights = new HashMap<String, Set<Flight>>();
		
		try {
			FileReaderAirports(airportsFile);
			FileReaderFlights(flightsFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void FileReaderAirports(File airportsFile) throws IOException {
			FileReader fr;
		
			fr = new FileReader(airportsFile);
			BufferedReader br = new BufferedReader(fr);
			String airportLine = br.readLine();
			while(airportLine != null) {
				String [] a = airportLine.split(",");
				Airport airport = new Airport(a[0], a[1], a[2], a[3], Double.parseDouble(a[4]), Double.parseDouble(a[5]));
				this.airports.put(airport.getIata(), airport);
				HashSet<Flight> setFlight = new HashSet<Flight>();
				exitingFlights.put(airport.getIata(), setFlight);
				airportLine = br.readLine();
			}
			br.close();
			fr.close();
		
	}
	
	public void FileReaderFlights(File flightsFile) throws IOException {
		FileReader fr;
		
		fr = new FileReader(flightsFile);
		BufferedReader br = new BufferedReader(fr);
		String flightLine = br.readLine();
		while(flightLine != null) {
			String [] a = flightLine.split(",");
			Flight flight = new Flight(a[0], airports.get(a[1]), airports.get(a[2]));
			Airport a1 = flight.getSource();
			Airport a2 = flight.getDestination();
			double distance = Util.distance(a1.getLatitude(), a1.getLongitude(), a2.getLatitude(), a2.getLongitude());
			flight.setDistance(distance);
			
			addFlightToMap(exitingFlights.get(flight.getSource().getIata()),flight, exitingFlights, flight.getSource().getIata());
			
			flightLine = br.readLine();
		}
		br.close();
		fr.close();
		
	}
	
	private void addFlightToMap(Set<Flight> f, Flight flight, Map<String, Set<Flight>> map, String codeIata) {
		if( f == null) {
			f = new HashSet<Flight>();
			f.add(flight);
		}
		else 
			f.add(flight);
		
		map.put(codeIata, f);
	}
	
	//BFS
	public void calculerItineraireMinimisantNombreVol(String codeIATASource, String codeIATADestination) {
		
		Map<Airport, Flight> itineraryFlight = new HashMap<Airport, Flight>();
		Deque<Airport> queue = new ArrayDeque<Airport>();
		
		Airport source = airports.get(codeIATASource);
		Airport destination = airports.get(codeIATADestination);
	
		queue.add(source);
		
		while(!queue.isEmpty() && !itineraryFlight.containsKey(destination)) {
			Airport current = queue.remove();
			
			Set<Flight> flights = exitingFlights.get(current.getIata());
			for(Flight f : flights) {
				Airport a = f.getDestination();
				if(!itineraryFlight.containsKey(a)) {
					queue.add(a);
					itineraryFlight.put(a, f);
				}
			}
		}
		displayFlight(itineraryFlight, source, destination, 0d);
	}

	//Dijkstra
	public void calculerItineraireMiniminantDistance(String codeIATASource, String codeIATADestination) {
		TreeSet<Airport> etiquetteProvisoire = new TreeSet<Airport>(new Comparator<Airport>() {
			public int compare(Airport a1, Airport a2) {
				  int res = a1.getDijkstra().compareTo(a2.getDijkstra());
		          if(res!=0) return res;
		          return a1.getIata().compareTo(a2.getIata());
			}
		});
		
		Map<Airport,Double> etiquetteDefinitive = new HashMap<Airport,Double>();
		Map<Airport, Flight> itineraryFlight = new HashMap<Airport, Flight>();
		
		Airport source = airports.get(codeIATASource);
		Airport destination = airports.get(codeIATADestination);
		source.setDijkstra(0d);
		etiquetteProvisoire.add(source);
		
		while(!etiquetteProvisoire.isEmpty() && !etiquetteDefinitive.containsKey(destination)) {
		
			Airport current = etiquetteProvisoire.first();
			double min = current.getDijkstra();
			etiquetteProvisoire.remove(current);
			etiquetteDefinitive.put(current, min);
			Set<Flight> flights = exitingFlights.get(current.getIata());
			
			for(Flight f : flights) {
				Airport a = f.getDestination();
				if(!etiquetteDefinitive.containsKey(a)) {
					double distanceAfter = f.getDistance() + min;
					if(!etiquetteProvisoire.contains(a)|| a.getDijkstra()>distanceAfter) {
						
						etiquetteProvisoire.remove(a);
						a.setDijkstra(distanceAfter);
					
						etiquetteProvisoire.add(a);
						itineraryFlight.put(a, f);
					} 
				}
			}
			
		}
		
		displayFlight(itineraryFlight, source, destination, etiquetteDefinitive.get(destination));
		

	}
	
	private void displayFlight(Map<Airport,Flight> itineraryFlight, Airport source, Airport destination, Double distance){
		Airport airport = destination;
		List<Flight> listFlight = new ArrayList<Flight>();
		double distanceTmp = 0;
		while(!airport.equals(source)) {
			Flight flight = itineraryFlight.get(airport);
			listFlight.add(flight);
			airport = flight.getSource();
			distanceTmp += flight.getDistance();
		}
		//Dijkstra a deja la distance dans l'etiquette definitive
		if(distance == 0d) {
			distance = distanceTmp;
		}
		System.out.println("Distance : " + distance);
		for(int i = listFlight.size() ; i--  > 0 ;) {
			System.out.println(listFlight.get(i));
		}
	}
	
}

