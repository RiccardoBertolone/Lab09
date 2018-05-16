package it.polito.tdp.borders.db;

import java.util.List;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.CountryIdMap;
import it.polito.tdp.borders.model.Model;
import it.polito.tdp.borders.model.Border;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();
		Model model = new Model();

		System.out.println("Lista di tutte le nazioni:");
		List<Country> countries = dao.loadAllCountries(new CountryIdMap());
		for (Country c : countries) {
			System.out.println(c.getStateNme());
		}
		List<Border> borders = dao.getCountryPairs(1902);
		for (Border b : borders) {
			System.out.println(b);
		}
	}
}
