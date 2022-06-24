package org.dylandinh.musicsheetsharingwebsite.service;

import java.util.HashSet;
import java.util.Set;

import org.dylandinh.musicsheetsharingwebsite.model.Composer;
import org.dylandinh.musicsheetsharingwebsite.model.Instrument;
import org.dylandinh.musicsheetsharingwebsite.model.Sheet;
import org.dylandinh.musicsheetsharingwebsite.model.User;
import org.dylandinh.musicsheetsharingwebsite.repository.SheetRepository;
import org.dylandinh.musicsheetsharingwebsite.repository.UserRepository;
import org.dylandinh.musicsheetsharingwebsite.upload.SheetInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SheetService {
	@Autowired
	SheetRepository sheetRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Sheet createNewSheet(User user, SheetInput sheetInput) {
		Sheet sheet = new Sheet();
		
		sheet.setTitle(sheetInput.getTitle());
		
		String[] composersArray = sheetInput.getComposers().split(",");
		Set<Composer> composers = new HashSet<Composer>();
		for(int i = 0; i < composersArray.length; i++) {
			composers.add(new Composer(composersArray[i]));
		}
		sheet.setComposers(composers);
		
		String[] instrumentsArray = sheetInput.getComposers().split(",");
		Set<Instrument> instruments = new HashSet<Instrument>();
		for(int i = 0; i < instrumentsArray.length; i++) {
			instruments.add(new Instrument(instrumentsArray[i]));
		}
		sheet.setInstruments(instruments);
		
		sheet.setUser(user);
		
		return sheet;
	}
}
