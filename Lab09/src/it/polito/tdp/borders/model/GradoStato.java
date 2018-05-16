package it.polito.tdp.borders.model;

public class GradoStato {

	private Country country;
	private int grado;
	
	public GradoStato(Country country, int grado) {
		super();
		this.country = country;
		this.grado = grado;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	@Override
	public String toString() {
		return "GradoStato [country=" + country + ", grado=" + grado + "]";
	}
	
	
	
}
