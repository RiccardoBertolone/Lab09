package it.polito.tdp.borders.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	SimpleGraph<Country, DefaultEdge> grafo ;
	BordersDAO dao;
	CountryIdMap mappaPaesi = new CountryIdMap();
	
	public Model() {
		grafo = new SimpleGraph<>(DefaultEdge.class);
		dao = new BordersDAO();
	}

	public List<GradoStato> calcolaConfini(int anno) {
		List<Country> paesi = dao.loadAllCountries(mappaPaesi);
		Graphs.addAllVertices(grafo, paesi);
		
		List<Border> confini = dao.getCountryPairs(anno);
		for (Border b : confini) {
			Country stato1 = mappaPaesi.get(b.getState1no());
			Country stato2 = mappaPaesi.get(b.getState2no());
			grafo.addEdge(stato1, stato2);
		}
		
		List<GradoStato> gradiStati = new LinkedList<GradoStato>();
		for(Country c : grafo.vertexSet()) {
			gradiStati.add(new GradoStato(c, grafo.degreeOf(c)));
		}
		
		return gradiStati;
	}

	public int getNumeroComponentiConnesse() {
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(grafo);
		return ci.connectedSets().size();
	}
	
	public List<Country> getStati() {
		return dao.loadAllCountries(mappaPaesi);
	}

	public List<Country> trovaVicini(Country selezionato) {
		BreadthFirstIterator<Country, DefaultEdge> bfi = new BreadthFirstIterator<>(grafo, selezionato);
		List<Country> vicini = new LinkedList<Country>();
		while (bfi.hasNext()) {
			vicini.add(bfi.next());
		}
		return vicini;
	}

	public Set<Country> trovaViciniRecursive(Country selezionato) {
		Set<Country> cConnessa = new LinkedHashSet<Country>();
		this.recursive(cConnessa, selezionato);
		return cConnessa;
	}

	private void recursive(Set<Country> cConnessa, Country country) {
		
		boolean flag = false;
		if (!cConnessa.contains(country)) {
			cConnessa.add(country);
			flag = true;
		}
		if (flag) {
			for(Country c : Graphs.neighborListOf(grafo, country)) {
				recursive(cConnessa, c);
			}
		}
		
	}

}
