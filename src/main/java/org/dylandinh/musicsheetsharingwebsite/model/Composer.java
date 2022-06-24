package org.dylandinh.musicsheetsharingwebsite.model;

import javax.persistence.*;

@Entity
@Table(name = "composers")
public class Composer {
	@Id
	@Column(name = "composer_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int composerId;

	@Column(name = "name", unique = true)
	private String name;
	
	public Composer() {
		
	}
	
	public Composer(String name) {
		this.name = name;
	}

	public int getComposerId() {
		return composerId;
	}

	public void setId(int composerId) {
		this.composerId = composerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
