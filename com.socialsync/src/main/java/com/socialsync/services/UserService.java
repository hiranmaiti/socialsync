package com.socialsync.services;

import com.socialsync.entities.User;

public interface UserService {
	
boolean userExists(String username, String email);
	
	void addUser(User user);

	boolean validateUser(String username, String password);

	User getUser(String username);

	void updateUser(User user);

	void updatePassword(String username, String newPassword);

	User findByUsername(String username);
	
	boolean usernameExists(String username);

	boolean verifyEmail(String username, String email);

	void resetPassword(String username, String newPassword);

}
