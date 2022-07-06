package org.dylandinh.musicsheetsharingwebsite.repository;

import org.dylandinh.musicsheetsharingwebsite.model.Instrument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentRepository extends JpaRepository<Instrument, Integer> {
	Instrument findByName(String name);
}
