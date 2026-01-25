package com.app.repo;

//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.app.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

//@Repository
//@EnableMongoRepositories
public interface UserRepo extends CrudRepository<UserAccount, String> {

	UserAccount findByUsername(String username);
	UserAccount findByEmail(String email);
	UserAccount findByEmailAndPassword(String email, String password);

	//@Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
	//UserAccount seeIfUserExists(String username);

}
