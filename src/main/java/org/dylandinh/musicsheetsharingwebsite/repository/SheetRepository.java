package org.dylandinh.musicsheetsharingwebsite.repository;

import org.dylandinh.musicsheetsharingwebsite.model.Sheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRepository extends JpaRepository<Sheet, Integer> {
	
}
