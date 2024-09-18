package com.socialsync.entities;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String name;
	private String email;
	private String password;
	private String dob;
	private String gender;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] photo;

	public String getPhotoBase64() {
		if (photo == null) {
			return null;
		}
		return Base64.getEncoder().encodeToString(photo);
	}
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] backgroundPhoto;
	
	public String getBackgroundPhotoBase64() {
	    if (backgroundPhoto == null) {
	        return null;
	    }
	    return Base64.getEncoder().encodeToString(backgroundPhoto);
	}

	
	@OneToMany
	private List<Post> posts;
	
	private String bio;
	private String linkedin;
	private String github;
	private String x;
	private String website;
	private String city;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String username, String name, String email, String password, String dob, String gender,
			byte[] photo, byte[] backgroundPhoto, List<Post> posts, String bio, String linkedin, String github,
			String x, String website, String city) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		this.photo = photo;
		this.backgroundPhoto = backgroundPhoto;
		this.posts = posts;
		this.bio = bio;
		this.linkedin = linkedin;
		this.github = github;
		this.x = x;
		this.website = website;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getBackgroundPhoto() {
		return backgroundPhoto;
	}

	public void setBackgroundPhoto(byte[] backgroundPhoto) {
		this.backgroundPhoto = backgroundPhoto;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", email=" + email + ", password="
				+ password + ", dob=" + dob + ", gender=" + gender + ", photo=" + Arrays.toString(photo)
				+ ", backgroundPhoto=" + Arrays.toString(backgroundPhoto) + ", posts=" + posts + ", bio=" + bio
				+ ", linkedin=" + linkedin + ", github=" + github + ", x=" + x + ", website=" + website + ", city="
				+ city + "]";
	}

	

	

	
}
