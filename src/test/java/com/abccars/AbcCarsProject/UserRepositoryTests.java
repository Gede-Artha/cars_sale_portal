package com.abccars.AbcCarsProject;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.abccars.AbcCarsProject.Users.Role;
import com.abccars.AbcCarsProject.Users.RoleRepository;
import com.abccars.AbcCarsProject.Users.User;
import com.abccars.AbcCarsProject.Users.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	@Test
	public void testAddRoleToExistingUser() {
		User user = userRepo.findById(2L).get();
		Role USER = roleRepo.findByName("USER");
		user.addRole(USER);
		User savedUser = userRepo.save(user);
		assertThat(savedUser.getRoles().size()).isEqualTo(2);		
	}
	
	@Test
	public void testFindByEmail() {
		String email = "gedeadmin@gmail.com";
		User user = userRepo.findByEmail(email);
		assertThat(user.getEmail()).isEqualTo(email);
	}
	

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("komang@gmail.com");
		user.setPassword("123456");
		user.setFirstName("komang");
		user.setLastName("nurmina");
		User savedUser = userRepo.save(user);
		User existUser = entityManager.find(User.class, savedUser.getId());
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
		
	}
}
