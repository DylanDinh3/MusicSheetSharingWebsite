package org.dylandinh.musicsheetsharingwebsite.upload;

import org.springframework.web.multipart.MultipartFile;

public class SheetInput {
	private String title;
	private String composers;
	private String instruments;
	private MultipartFile images;
	private String user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComposers() {
		return composers;
	}

	public void setComposers(String composers) {
		this.composers = composers;
	}

	public String getInstruments() {
		return instruments;
	}

	public void setInstruments(String instruments) {
		this.instruments = instruments;
	}
	
	public MultipartFile getImages() {
		return images;
	}
	
	public void setImages(MultipartFile images) {
		this.images = images;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
