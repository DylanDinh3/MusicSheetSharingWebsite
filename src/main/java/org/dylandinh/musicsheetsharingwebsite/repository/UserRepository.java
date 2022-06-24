package org.dylandinh.musicsheetsharingwebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.dylandinh.musicsheetsharingwebsite.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	User findByUsername(String username);
}
