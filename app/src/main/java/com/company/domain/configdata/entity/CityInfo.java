
package com.company.domain.configdata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity Mapping Class. 
 * Haven't added all the columns
 * 
 * @author sumit.bhardwaj
 */

@Entity
@Table(name = "WORLD_CITIES")
public class CityInfo {

	@Id
	private String id;

	@Column(name = "NAME_OF_TOWN", nullable = false)
	private String city;

	@Column(name = "STATE", nullable = false)
	private String state;

	@Column(name = "TYPE", nullable = false)
	private String type;

	@Column(name = "POPULATION", nullable = false)
	private String population;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	
	
}
