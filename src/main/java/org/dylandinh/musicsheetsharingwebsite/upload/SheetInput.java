package org.dylandinh.musicsheetsharingwebsite.upload;

import org.dylandinh.musicsheetsharingwebsite.model.User;

public class SheetInput {
	private int id;
	private String title;
	private String composers;
	private String instruments;
	private String images;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	
	public String getImages() {
		return images;
	}
	
	public void setImages(String images) {
		this.images = images;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
