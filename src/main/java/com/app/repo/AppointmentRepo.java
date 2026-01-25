package com.app.repo;

//import org.springframework.data.mongodb.repository.MongoRepository;
import com.app.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface AppointmentRepo extends CrudRepository<Appointment, String> {

	long countByStatus(String status);

	Appointment findByAppointmentId(String appointmentId);

}
