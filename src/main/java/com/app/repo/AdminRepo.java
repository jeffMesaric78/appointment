package com.app.repo;

//import org.springframework.data.mongodb.repository.MongoRepository;
//mport org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.app.entity.Admin;
import org.springframework.data.repository.CrudRepository;

//@Repository
//@EnableMongoRepositories
public interface AdminRepo extends CrudRepository<Admin, String> {

	Admin findByEmailAndPassword(String email, String password);

}
