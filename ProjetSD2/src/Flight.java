
public class Flight {
	
	private final String codeIATASource;
	private final String codeIATADestination;
	private final String airline;
	
	public Flight(String airline, String codeIATASource, String codeIATADestination) {
		this.codeIATASource = codeIATASource;
		this.codeIATADestination = codeIATADestination;
		this.airline = airline;
	}

	public String getCodeIATASource() {
		return codeIATASource;
	}

	public String getCodeIATADestination() {
		return codeIATADestination;
	}

	public String getAirline() {
		return airline;
	}

	@Override
	public String toString() {
		return "Flight [codeIATASource=" + codeIATASource + ", codeIATADestination=" + codeIATADestination
				+ ", airline=" + airline + "]";
	}
	
	
	
	

	
	
}
