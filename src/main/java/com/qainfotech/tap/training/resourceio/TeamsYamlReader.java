package com.qainfotech.tap.training.resourceio;

import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author rishabh.kumar
 *
 */
public class TeamsYamlReader {
	/**
	 * get a list of individual objects from db yaml file
	 * 
	 * @return
	 */
	List<Individual> individual_list;

	public List<Individual> getListOfIndividual() {
		individual_list = new ArrayList();
		try {
			Yaml yaml = new Yaml();
			InputStream in = new FileInputStream(new File("src/main/resources/db.yaml"));

			Map<String, Object> map = (Map<String, Object>) yaml.load(in);
			ArrayList yaml_list = (ArrayList) map.get("individuals");
			for (int i = 0; i < yaml_list.size(); i++) {
				Map map1 = (Map<String, Object>) yaml_list.get(i);
				Map<String, Object> my_map = new HashMap<String, Object>();
				my_map.put("name", map1.get("name"));
				my_map.put("id", map1.get("id"));
				my_map.put("active", map1.get("active"));

				Individual indiObject = new Individual(my_map);

				individual_list.add(indiObject);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return individual_list;

	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> individual_list = getListOfIndividual();
		Individual ind = null;
		for (int i = 0; i < individual_list.size(); i++) {
			ind = individual_list.get(i);
			if (ind.getId().equals(id))
				break;
		}
		return ind;

	}

	/**
	 * get individual object by name
	 * 
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> individual_list = getListOfIndividual();
		Individual ind = null;

		for (int i = 0; i < individual_list.size(); i++) {
			ind = individual_list.get(i);
			if (ind.getName().equals(name)) {

				break;
			}

		}

		return ind;

	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 */

	public List<Individual> getListOfInactiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> individual_list = getListOfIndividual();

		Individual ind = null;

		List<Individual> ListOfInactiveIndividuals = new ArrayList<Individual>();

		for (int i = 0; i < individual_list.size(); i++) {
			ind = individual_list.get(i);
			if (!ind.isActive()) {
				ListOfInactiveIndividuals.add(ind);
			}
		}
		return ListOfInactiveIndividuals;

	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 */
	public List<Individual> getListOfActiveIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		List<Individual> individual_list = getListOfIndividual();
		Individual ind = null;
		List<Individual> ListOfActiveIndividuals = new ArrayList<Individual>();

		for (int i = 0; i < individual_list.size(); i++) {
			ind = individual_list.get(i);
			if (ind.isActive()) {
				ListOfActiveIndividuals.add(ind);
			}
		}
		return ListOfActiveIndividuals;

	}

	/**
	 * get a list of team objects from db yaml
	 * 
	 * @return
	 */
	public List<Team> getListOfTeams() {
		throw new UnsupportedOperationException("Not implemented.");

	}
}
