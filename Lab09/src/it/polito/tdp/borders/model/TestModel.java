package it.polito.tdp.borders.model;

import java.util.LinkedList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		List<GradoStato> gradiStati = new LinkedList<GradoStato>();
		gradiStati = model.calcolaConfini(2000);
		for (GradoStato g : gradiStati) {
			System.out.println(g);
		}
		
//		System.out.println("Creo il grafo relativo al 2000");
//		model.createGraph(2000);
		
//		List<Country> countries = model.getCountries();
//		System.out.format("Trovate %d nazioni\n", countries.size());

//		System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());
		
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));		
		
	}

}
