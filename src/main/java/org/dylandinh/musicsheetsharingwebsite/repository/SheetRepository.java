package org.dylandinh.musicsheetsharingwebsite.repository;

import java.util.List;

import org.dylandinh.musicsheetsharingwebsite.model.Sheet;
import org.dylandinh.musicsheetsharingwebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRepository extends JpaRepository<Sheet, Integer> {
	List<Sheet> findByUser(User user);
	Sheet findById(int id);
	Sheet findByTitle(String title);
}
