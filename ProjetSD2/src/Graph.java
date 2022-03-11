import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
	
	public Map<String,Airport> airports;
	public Map<String, Set<Flight>> flightsAdjacent;
	
	public Graph(File airportsFile, File flightsFile) {
		airports = new HashMap<String, Airport>();
		flightsAdjacent = new HashMap<String, Set<Flight>>();
	
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
			Flight flight = new Flight(a[0], a[1], a[2]);
			Set<Flight> f = flightsAdjacent.get(flight.getCodeIATASource());
			if( f == null) {
				f = new HashSet<Flight>();
				f.add(flight);
			}
			else 
				f.add(flight);
			
			flightsAdjacent.put(flight.getCodeIATASource(), f);
			
			flightLine = br.readLine();
		}
		br.close();
		fr.close();
		
	}
	
	//BFS
	public void calculerItineraireMinimisantNombreVol(String codeIATASource, String codeIATADestination) {
		Map<Airport, Airport> itinerary = new HashMap<Airport, Airport>();
		Set<Airport> visitedAirports = new HashSet<Airport>();
		Deque<Airport> queue = new ArrayDeque<Airport>();
		
	}
	
	//Dijkstra
	public void calculerItineraireMiniminantDistance(String codeIATASource, String codeIATADestination) {
		
	}
}
