package org.dylandinh.musicsheetsharingwebsite.model;

import javax.persistence.*;

@Entity
@Table(name = "instruments")
public class Instrument {
	@Id
	@Column(name = "instrument_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int instrumentId;

	@Column(name = "name", unique = true)
	private String name;
	
	public Instrument() {
		
	}
	
	public Instrument(String name) {
		this.name = name;
	}

	public int getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(int instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		Integer id = (Integer)instrumentId;
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Integer id = instrumentId;
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Instrument other = (Instrument)obj;
		Integer otherId = other.getInstrumentId();
		if(id == null) {
			if(otherId != null)
				return false;
		}
		else if(!id.equals(otherId))
			return false;
		return true;
	}
}
