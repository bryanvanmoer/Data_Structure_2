
public class Airport {
	private final String iata;
	private final String name;
	private final String city;
	private final String country;
	private final double longitude;
	private final double latitude;
	


	public Airport(String iata, String name, String city, String country, double longitude, double latitude) {
	this.iata = iata;
	this.name = name;
	this.city = city;
	this.country = country;
	this.longitude = longitude;
	this.latitude = latitude;
	}
	
	
	
	public String getIata() {
		return iata;
	}



	public String getName() {
		return name;
	}



	public String getCity() {
		return city;
	}



	public String getCountry() {
		return country;
	}



	public double getLongitude() {
		return longitude;
	}



	public double getLatitude() {
		return latitude;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((iata == null) ? 0 : iata.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (iata == null) {
			if (other.iata != null)
				return false;
		} else if (!iata.equals(other.iata))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Airport [iata=" + iata + ", name=" + name + ", city=" + city + ", country=" + country + ", longitude="
				+ longitude + ", latitude=" + latitude + "]";
	}

	

}
