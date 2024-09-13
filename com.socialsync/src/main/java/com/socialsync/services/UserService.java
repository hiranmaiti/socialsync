package com.socialsync.services;

import com.socialsync.entities.User;

public interface UserService {
	
boolean userExists(String username, String email);
	
	void addUser(User user);

	boolean validateUser(String username, String password);

}
