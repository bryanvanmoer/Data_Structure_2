package ex2;

public class Badge {
	private int numero;

	public Badge(int numero) {
		super();
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
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
		Badge other = (Badge) obj;
		if (numero != other.numero)
			return false;
		return true;
	}
	
	

}
