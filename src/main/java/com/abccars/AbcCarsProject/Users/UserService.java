package com.abccars.AbcCarsProject.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired RoleRepository roleRepo;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	public void registerDefaultUser(User user) {
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
		encodePassword(user);
		userRepo.save(user);
	}
	
	
	
	public void UpdatedDefaultUser(User user) {
		Role roleUser = roleRepo.findByName("User");
		user.addRole(roleUser);
		userRepo.save(user);
	}
	
	public User findUserById(Long id) {
		return userRepo.findById(id).get();
	}

	
	
	public List<User> listAll() {
		return userRepo.findAll();
	}
	
	 public void deleteUserById(Long id)
	    {
	    	userRepo.deleteById(id);
	    }

	public User get(Long id) {
		return userRepo.findById(id).get();
	}
	
	public List<Role> listRoles() {
		return roleRepo.findAll();
	}
	
//	public void save(User user) {
//		encodePassword(user);		
//		userRepo.save(user);
//		
//	}
	
	public void saveUser(User user) {
		encodePassword(user);		
		userRepo.save(user);
		
	}
	
	public void save(User user) {
		Role User = roleRepo.findByName("User");
		encodePassword(user);
		user.addRole(User);
		userRepo.save(user);
		
	}
	
	private void encodePassword(User user) {
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);		
	}
}

