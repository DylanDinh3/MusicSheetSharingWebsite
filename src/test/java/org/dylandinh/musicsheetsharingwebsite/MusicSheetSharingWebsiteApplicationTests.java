package org.dylandinh.musicsheetsharingwebsite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import javax.transaction.Transactional;

import org.dylandinh.musicsheetsharingwebsite.model.Sheet;
import org.dylandinh.musicsheetsharingwebsite.model.User;
import org.dylandinh.musicsheetsharingwebsite.repository.SheetRepository;
import org.dylandinh.musicsheetsharingwebsite.repository.UserRepository;
import org.dylandinh.musicsheetsharingwebsite.service.SheetService;
import org.dylandinh.musicsheetsharingwebsite.service.UserServiceImpl;
import org.dylandinh.musicsheetsharingwebsite.upload.SheetInput;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
@Transactional
class MusicSheetSharingWebsiteApplicationTests {
	private UserServiceImpl userServiceImpl;
	private SheetService sheetService;
	private SheetRepository sheetRepository;
	private UserRepository userRepository;

	@Autowired
	public MusicSheetSharingWebsiteApplicationTests(UserServiceImpl userServiceImpl, SheetService sheetService,
			SheetRepository sheetRepository, UserRepository userRepository) {
		this.userServiceImpl = userServiceImpl;
		this.sheetService = sheetService;
		this.sheetRepository = sheetRepository;
		this.userRepository = userRepository;
	}

	@Test
	void contextLoads() {
		assertNotNull(userServiceImpl);
		assertNotNull(sheetService);
		assertNotNull(sheetRepository);
		assertNotNull(userRepository);
	}

	@Test
	void testUserDetailsServiceCurrentUser() {
		UserDetails ud = userServiceImpl.loadUserByUsername("user@gmail.com");
		assertNotNull(ud);
		assertEquals("user@gmail.com", ud.getUsername());
	}

	@Test
	void testCreateNewSheet() {
		SheetInput sheetInput = new SheetInput();
		sheetInput.setTitle("Test Sheet Music");
		sheetInput.setComposers("Mozart");
		sheetInput.setInstruments("Piano");
		sheetInput.setImages("imageURL");
		User user = userRepository.findByUsername("user");
		sheetInput.setUser(user);
		Sheet sheet = sheetService.createNewSheet(sheetInput);
		
		assertEquals(sheet.getTitle(), "Test Sheet Music");
		assertEquals(sheet.getComposers().toString(), "[Mozart]");
		assertEquals(sheet.getInstruments().toString(), "[Piano]");
		assertEquals(sheet.getImagesPath(), "imageURL");
		assertEquals(sheet.getUser(), user);
	}
	
	@Test
	void testUpdateSheet() {
		SheetInput sheetInput = new SheetInput();
		sheetInput.setId(46);
		sheetInput.setTitle("Eine Kleine Nachtmusik");
		sheetInput.setComposers("Mozart");
		sheetInput.setInstruments("Piano");
		sheetInput.setImages("imageURL");
		User user = userRepository.findByUsername("user");
		sheetInput.setUser(user);
		Sheet sheet = sheetService.updateSheet(sheetInput);
		sheetRepository.save(sheet);
		
		Sheet updatedSheet = sheetRepository.findById(46);
		assertEquals(46, updatedSheet.getSheetId());
		assertEquals("Eine Kleine Nachtmusik", updatedSheet.getTitle());
		assertEquals("[Mozart]", updatedSheet.getComposers().toString());
		assertEquals("[Piano]", updatedSheet.getInstruments().toString());
		assertEquals("imageURL", updatedSheet.getImagesPath());
		assertEquals(user, updatedSheet.getUser());
	}
}
