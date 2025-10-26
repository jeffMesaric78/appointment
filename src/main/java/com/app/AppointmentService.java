package com.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class AppointmentService {

	//@Autowired
	private final AppointmentRepo repo;

	//@Autowired
	private final UserRepo userRepo;

	//@Autowired
	private final AdminRepo adminRepo;

    public AppointmentService(AppointmentRepo repo, UserRepo userRepo, AdminRepo adminRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
    }

    /**
	 * Creates a new user account.
	 * @param patient The user object containing user details.
	 * @return true if the user account is successfully created, false otherwise.
	 */
	public boolean createUserAccount(Patient patient) {

		System.out.println("num records: "+userRepo.count());
		patient.setId(String.valueOf(userRepo.count()+1));

		if (userRepo.save(patient) != null) {
			return true;
		} else
			return false;
	}

	/**
	 * Finds a user by their email.
	 * @param email The email of the user to find.
	 * @return true if the user is found.
	 */
	public boolean findByUserEmail(String email) {
		System.out.println("email: "+email);
		Patient p = userRepo.findByEmail(email);
		if (p != null) {
			System.out.println("p: " + p);
			return true;
		}
		else {
			System.out.println("p null");
			return false;
		}

	}

	/**
	 * Authenticates a user based on email and password.
	 * @param email The email of the user.
	 * @param password The password of the user.
	 * @return true if the email and password match a user, false otherwise.
	 */
	public boolean userLogin(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password) != null;
	}

	/**
	 * Authenticates an admin based on email and password.
	 * @param email The email of the admin.
	 * @param password The password of the admin.
	 * @return true if the email and password match an admin, false otherwise.
	 */
	public boolean adminLogin(String email, String password) {
		return adminRepo.findByEmailAndPassword(email, password) != null;
	}

	/**
	 * Saves an appointment.
	 * @param appointment The appointment object to save.
	 * @return true if the appointment is successfully saved.
	 */
	public boolean saveData(Appointment appointment) {
		appointment.setAppointmentId(generateRandomString());
		repo.save(appointment);
		return true;
	}

	/**
	 * Retrieves all appointments.
	 * @return A list of all appointments.
	 */
	public List<Appointment> findAllAppointments() {
		//return repo.findAll();
		return new ArrayList<>();
	}

	/**
	 * Generates a random string for appointment ID.
	 * @return A random string prefixed with "RQ" followed by 5 random digits.
	 */
	public static String generateRandomString() {
		final String FIXED_PART = "RQ";
		final int RANDOM_PART_LENGTH = 5;
		StringBuilder sb = new StringBuilder(FIXED_PART);
		Random random = new Random();

		for (int i = 0; i < RANDOM_PART_LENGTH; i++) {
			int digit = random.nextInt(10);
			sb.append(digit);
		}

		return sb.toString();
	}
}
