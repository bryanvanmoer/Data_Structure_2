
public class Flight {
	
	private final Airport source;
	private final Airport destination;
	private final String airline;
	private double distance;
	
	public Flight(String airline, Airport source, Airport destination) {
		this.source = source;
		this.destination = destination;
		this.airline = airline;
	}
	

	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double distance) {
		this.distance = distance;
	}

	public Airport getSource() {
		return source;
	}
	public Airport getDestination() {
		return destination;
	}



	public String getAirline() {
		return airline;
	}



	@Override
	public String toString() {
		return "Flight [source=" + source.getName() + ", destination=" + destination.getName() + ", airline=" + airline + ", distance="
				+ distance + "]";
	}




	
	
	
	

	
	
}
