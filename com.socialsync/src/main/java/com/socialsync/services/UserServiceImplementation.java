package com.socialsync.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialsync.entities.User;
import com.socialsync.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	UserRepository repo;
	
	@Override
	public boolean userExists(String username, String email) {
		User u1= repo.findByEmail(email);
		User u2 = repo.findByUsername(username);
		if ( u1 != null || u2 != null ) {
			return true;
		}
		return false;
	}
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		repo.save(user);	
	}

	
	@Override
    public boolean validateUser(String username, String password) {
        User user = repo.findByUsername(username);
        if (user != null) {
            String dbPassword = user.getPassword();
            return password.equals(dbPassword);
        }
        return false;
    }

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		repo.save(user);
		
	}
	@Override
	public void updatePassword(String username, String newPassword) {
        // Fetch the user by username
        User user = repo.findByUsername(username);

        if (user != null) {
            // Set the new password
            user.setPassword(newPassword);
            // Save the updated user back to the database
            repo.save(user);
        }
    }

}
