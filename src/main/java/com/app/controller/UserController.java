package com.app.controller;

import com.app.entity.UserAccount;
import com.app.repo.AppointmentRepo;
import com.app.repo.UserRepo;
import com.app.service.JwtService;
import com.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Transactional
@CrossOrigin(origins = "http://localhost:5173") // Specify the exact origin of your frontend
public class UserController {

	private final JwtService jwtService;


	//@Autowired
	private final UserService userService;

	//@Autowired
	//private final AppointmentRepo repo;

	//private final UserRepo userRepo;

    public UserController(JwtService jwtService,
						  UserService service,
						  AppointmentRepo repo,
						  UserRepo userRepo) {
        this.jwtService = jwtService;
        this.userService = service;
        //this.repo = repo;
        //this.userRepo = userRepo;
    }


	////use RequestBody for json, ModelAttribute for form data
	@PostMapping("/user/create")
	public String createUser(@RequestBody UserAccount userAccount) {

		System.out.println("entry..");
		System.out.println(userAccount);

		boolean result = userService.findByUserEmail(userAccount.getEmail());

		System.out.println("result: "+result);
		String ret = "";

		if (result) {
			System.out.println("test");
			//model.addAttribute("exist", "This " + patient.getEmail() + " Already Exist");
			ret = "This " + userAccount.getEmail() + " already exists";
		} else if (userService.createUserAccount(userAccount)) {
			//model.addAttribute("success", "Your User Account created");
			ret = "success, user account created";
		} else {
			//model.addAttribute("error", "Your User Account creationed Failed");
			ret = "error, user creation failed";
		}

		return ret;
	}


	@GetMapping("/user/login")
	public ResponseEntity<String> userLogin(@RequestParam("username") String username) {
		boolean loginstatus = userService.userLogin(username);
		System.out.println("login status: "+loginstatus);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();

		if (loginstatus) {
			//return "redirect:/appointment";
			node.put("status","found");
			//return new ResponseEntity<>("found", HttpStatus.OK);

			String token = jwtService.generateToken(username);
			System.out.println("token: "+token);

			//boolean b = jwtService.validateToken(token);
			//System.out.println("b: "+b);

			node.put("token",token);

		} else {
			//model.addAttribute("error", "Login Failed");
			//return "userlogin";
			node.put("status","not found");
			//return new ResponseEntity<>("not found", HttpStatus.OK);
		}
        //return null;
		return new ResponseEntity<>(node.toPrettyString(), HttpStatus.OK);
    }



}
