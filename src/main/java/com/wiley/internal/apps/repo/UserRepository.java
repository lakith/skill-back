package com.wiley.internal.apps.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wiley.internal.apps.domain.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> { 
	
	User findUsersByUserName(String userName);
	
	List<User> findByUserNameIgnoreCaseContaining(String userName);

	@Query("SELECT count (u.userName) FROM User u ")
	int getUserCount();
}

