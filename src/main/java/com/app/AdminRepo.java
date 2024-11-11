package com.app;

//import org.springframework.data.mongodb.repository.MongoRepository;
//mport org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
//@EnableMongoRepositories
public interface AdminRepo extends CrudRepository<Admin, String> {

	Admin findByEmailAndPassword(String email, String password);

}
