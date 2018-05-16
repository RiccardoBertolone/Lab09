package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

public class CountryIdMap {

	private Map<Integer, Country> map;
	
	public CountryIdMap() {
		map = new HashMap<>();
	}
	
	public Country get(int cCode) {
		return map.get(cCode);
	}
	
	public Country get(Country country) {
		Country old = map.get(country.getcCode());
		if (old == null) {
			map.put(country.getcCode(), country);
			return country;
		}
		return old;
	}
	
	public void put(Country country, int cCode) {
		map.put(cCode, country);
	}
	
}
