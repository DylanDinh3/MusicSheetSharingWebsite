package org.dylandinh.musicsheetsharingwebsite.repository;

import org.dylandinh.musicsheetsharingwebsite.model.Composer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComposerRepository extends JpaRepository<Composer, Integer> {
	Composer findByName(String name);
}
