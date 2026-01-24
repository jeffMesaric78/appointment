package com.app;

//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
//@EnableMongoRepositories
public interface UserRepo extends CrudRepository<User, String> {

	User findByEmail(String email);

	User findByEmailAndPassword(String email, String password);

	@Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
	User seeIfUserExists(String username);

}
