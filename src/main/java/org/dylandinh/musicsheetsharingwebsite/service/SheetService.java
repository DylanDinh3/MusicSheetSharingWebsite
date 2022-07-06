package org.dylandinh.musicsheetsharingwebsite.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dylandinh.musicsheetsharingwebsite.model.Composer;
import org.dylandinh.musicsheetsharingwebsite.model.Instrument;
import org.dylandinh.musicsheetsharingwebsite.model.Sheet;
import org.dylandinh.musicsheetsharingwebsite.repository.ComposerRepository;
import org.dylandinh.musicsheetsharingwebsite.repository.InstrumentRepository;
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
	ComposerRepository composerRepository;
	
	@Autowired
	InstrumentRepository instrumentRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public Sheet createNewSheet(SheetInput sheetInput) {
		Sheet sheet = new Sheet();
		
		sheet.setTitle(sheetInput.getTitle());
		
		String[] composersArray = sheetInput.getComposers().split(",\\s*");
		Set<Composer> composers = new HashSet<Composer>();
		for(int i = 0; i < composersArray.length; i++) {
			if(composerRepository.findByName(composersArray[i]) != null)
				composers.add(composerRepository.findByName(composersArray[i]));
			else
				composers.add(new Composer(composersArray[i]));
		}
		sheet.setComposers(composers);
		
		String[] instrumentsArray = sheetInput.getInstruments().split(",\\s*");
		Set<Instrument> instruments = new HashSet<Instrument>();
		for(int i = 0; i < instrumentsArray.length; i++) {
			if(instrumentRepository.findByName(instrumentsArray[i]) != null)
				instruments.add(instrumentRepository.findByName(instrumentsArray[i]));
			else
				instruments.add(new Instrument(instrumentsArray[i].substring(0, 1).toUpperCase() + instrumentsArray[i].substring(1)));
		}
		sheet.setInstruments(instruments);
		
		sheet.setImagesPath(sheetInput.getImages());
		
		sheet.setUser(sheetInput.getUser());
		
		return sheet;
	}
	
	public Sheet updateSheet(SheetInput sheetInput) {
		Sheet sheet = new Sheet();
		
		sheet.setSheetId(sheetInput.getId());
		
		sheet.setTitle(sheetInput.getTitle());
		
		String[] composersArray = sheetInput.getComposers().split(",\\s*");
		Set<Composer> composers = new HashSet<Composer>();
		for(int i = 0; i < composersArray.length; i++) {
			if(composerRepository.findByName(composersArray[i]) != null)
				composers.add(composerRepository.findByName(composersArray[i]));
			else {
				composerRepository.save(new Composer(composersArray[i]));
				composers.add(composerRepository.findByName(composersArray[i]));
			}
		}
		sheet.setComposers(composers);
		
		String[] instrumentsArray = sheetInput.getInstruments().split(",\\s*");
		Set<Instrument> instruments = new HashSet<Instrument>();
		for(int i = 0; i < instrumentsArray.length; i++) {
			if(instrumentRepository.findByName(instrumentsArray[i]) != null)
				instruments.add(instrumentRepository.findByName(instrumentsArray[i]));
			else {
				Instrument newInstrument = new Instrument(instrumentsArray[i].substring(0, 1).toUpperCase() + instrumentsArray[i].substring(1));
				instrumentRepository.save(newInstrument);
				instruments.add(instrumentRepository.findByName(newInstrument.getName()));
			}
		}
		sheet.setInstruments(instruments);
		
		sheet.setImagesPath(sheetInput.getImages());
		
		sheet.setUser(sheetInput.getUser());
		
		return sheet;
	}
	
	public List<SheetInput> convertSheet(List<Sheet> sheets) {
		List<SheetInput> sheetInputs = new ArrayList<SheetInput>();
		for(Sheet sheet : sheets) {
			SheetInput sheetInput = new SheetInput();
			sheetInput.setId(sheet.getSheetId());
			sheetInput.setImages(sheet.getImagesPath());
			sheetInput.setTitle(sheet.getTitle());
			
			String[] composers = new String[sheet.getComposers().size()];
			int count = 0;
			for(Composer composer : sheet.getComposers()) {
				composers[count] = composer.getName();
				count++;
			}
			Arrays.sort(composers);
			String sortedComposers = "";
			for(int i = 0; i < composers.length; i++)
				sortedComposers += composers[i] + ", ";
			sortedComposers = sortedComposers.substring(0, sortedComposers.length() - 2);
			sheetInput.setComposers(sortedComposers);
			
			String[] instruments = new String[sheet.getInstruments().size()];
			count = 0;
			for(Instrument instrument : sheet.getInstruments()) {
				instruments[count] = instrument.getName();
				count++;
			}
			Arrays.sort(instruments);
			String sortedInstruments = "";
			for(int i = 0; i < instruments.length; i++) 
				sortedInstruments += instruments[i] + ", ";
			sortedInstruments = sortedInstruments.substring(0, sortedInstruments.length() - 2);
			sheetInput.setInstruments(sortedInstruments);
			
			sheetInput.setUser(sheet.getUser());
			
			sheetInputs.add(sheetInput);
		}
		return sheetInputs;
	}
}
