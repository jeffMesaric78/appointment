package com.app;

//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface AppointmentRepo extends CrudRepository<Appointment, String> {

	long countByStatus(String status);

	Appointment findByAppointmentId(String appointmentId);

}
