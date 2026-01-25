package com.app.service;

import com.app.entity.UserAccount;
import com.app.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class UserService {

	//@Autowired
	//private final AppointmentRepo repo;

	//@Autowired
	private final UserRepo userRepo;

	//@Autowired
	//private final AdminRepo adminRepo;

    public UserService(UserRepo userRepo) {
        //this.repo = repo;
        this.userRepo = userRepo;
        //this.adminRepo = adminRepo;
    }

    /**
	 * Creates a new user account.
	 * @param userAccount The user object containing user details.
	 * @return true if the user account is successfully created, false otherwise.
	 */
	public boolean createUserAccount(UserAccount userAccount) {

		//System.out.println("num records: "+ userRepo.count());
		//userAccount.setId(String.valueOf(userRepo.count()+1));
		//userAccount.setId(null);

        userRepo.save(userAccount);

        return true;
    }


	public boolean findByUserEmail(String email) {
		System.out.println("email: "+email);
		UserAccount p = userRepo.findByEmail(email);
		if (p != null) {
			System.out.println("p: " + p);
			return true;
		}
		else {
			System.out.println("p null");
			return false;
		}

	}



	public boolean userLogin(String username) {

		//return userRepo.seeIfUserExists(username) != null;
		return userRepo.findByUsername(username) != null;
	}




}
