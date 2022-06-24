package org.dylandinh.musicsheetsharingwebsite.model;

import java.util.Set;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "sheets")
public class Sheet {
	@Id
	@Column(name = "sheet_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sheetId;

	@Column(name = "title")
	private String title;

	@Lob
	@Column(name = "sheets_image")
	private MultipartFile images;

	@ManyToOne
	@JoinColumn(name = "user_sheets", nullable = false)
	private User user;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "composers_sheets", joinColumns = @JoinColumn(name = "composer_id"), inverseJoinColumns = @JoinColumn(name = "sheet_id"))
	private Set<Composer> composers;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "instruments_sheets", joinColumns = @JoinColumn(name = "instrument_id"), inverseJoinColumns = @JoinColumn(name = "sheet_id"))
	private Set<Instrument> instruments;

	public int getSheetId() {
		return sheetId;
	}

	public void setSheetId(int sheetId) {
		this.sheetId = sheetId;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setName(String title) {
		this.title = title;
	}

	public MultipartFile getImages() {
		return images;
	}

	public void setImages(MultipartFile images) {
		this.images = images;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Composer> getComposers() {
		return composers;
	}

	public void setComposers(Set<Composer> composers) {
		this.composers = composers;
	}

	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}
}
