package com.app;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@Slf4j
@Transactional
public class AppointmentController {

	//@Autowired
	private final AppointmentService service;

	//@Autowired
	private final AppointmentRepo repo;

	private final UserRepo userRepo;

    public AppointmentController(AppointmentService service, AppointmentRepo repo, UserRepo userRepo) {
        this.service = service;
        this.repo = repo;
        this.userRepo = userRepo;
    }


	
	/**
	 * Handles GET requests for the home page.
	 * @param model The model to pass attributes to the view.
	 * @return The name of the home view.
	 */
	/*@GetMapping("/")
	public String getIndexPage(Model model) {
		return "home";
	}*/

	@RequestMapping("/")
	public String index () {
		return "home";
	}


	/**
	 * Handles GET requests for the appointment page.
	 * @param model The model to pass attributes to the view.
	 * @return The name of the appointment view.
	 */
	@GetMapping("/appointment")
	public String getAppointmentPage(Model model) {
		return "appointment";
	}

	/**
	 * Handles GET requests for the dashboard page.
	 * @param model The model to pass attributes to the view.
	 * @return The name of the dashboard view.
	 */
	@GetMapping("/dashboard")
	public String getDashboardPage(Model model) {
		List<Appointment> appointments = service.findAllAppointments();
		model.addAttribute("appointments", appointments);
		model.addAttribute("totalAppointments", repo.count());
		model.addAttribute("totalBooked", repo.countByStatus("booked"));
		model.addAttribute("totalPending", repo.countByStatus("pending"));
		return "dashboard";
	}

	/**
	 * Handles GET requests for the user registration page.
	 * @param model The model to pass attributes to the view.
	 * @param user The user model attribute.
	 * @return The name of the register view.
	 */
	@GetMapping("/patient/create")
	public String getUserCreate(Model model, @ModelAttribute User user) {
		return "register";
	}

	/**
	 * Handles POST requests for user registration.
	 * @param model The model to pass attributes to the view.
	 * @param user The user model attribute.
	 * @return The name of the register view.
	 */
	@PostMapping("/patient/create")
	public String getPostUser(Model model, @ModelAttribute User user) {

		System.out.println("entry..");
		System.out.println(user);

		boolean result = service.findByUserEmail(user.getEmail());

		System.out.println("result: "+result);

		if (result) {
			System.out.println("test");
			model.addAttribute("exist", "This " + user.getEmail() + " Already Exist");
		} else if (service.createUserAccount(user)) {
			model.addAttribute("success", "Your User Account created");
		} else {
			model.addAttribute("error", "Your User Account creationed Failed");
		}
		return "register";
	}






	/**
	 * Handles GET requests for the user login page.
	 * @return The name of the user login view.
	 */
	@GetMapping("/patient/login")
	public String getUserLogin() {
		return "userlogin";
	}

	/**
	 * Handles POST requests for user login.
	 * @param model The model to pass attributes to the view.
	 * @param user The user model attribute.
	 * @return A redirection to the appointment page if login is successful, otherwise the user login view.
	 */
	@PostMapping("/patient/login")
	public String postUserLogin(Model model, @ModelAttribute User user) {
		boolean loginstatus = service.userLogin(user.getEmail(), user.getPassword());
		if (loginstatus) {
			return "redirect:/appointment";
		} else {
			model.addAttribute("error", "Login Failed");
			return "userlogin";
		}
	}

	/**
	 * Handles GET requests for the admin login page.
	 * @param model The model to pass attributes to the view.
	 * @return The name of the admin login view.
	 */
	@GetMapping("/admin/login")
	public String getAdminLogin(Model model) {
		return "adminlogin";
	}

	/**
	 * Handles POST requests for admin login.
	 * @param model The model to pass attributes to the view.
	 * @param admin The admin model attribute.
	 * @return A redirection to the dashboard page if login is successful, otherwise the admin login view.
	 */
	@PostMapping("/admin/login")
	public String postAdminLogin(Model model, @ModelAttribute Admin admin) {
		boolean loginstatus = service.adminLogin(admin.getEmail(), admin.getPassword());
		if (loginstatus) {
			return "redirect:/dashboard";
		} else {
			model.addAttribute("error", "Login Failed");
			return "adminlogin";
		}
	}

	/**
	 * Handles GET requests for the search page.
	 * @param model The model to pass attributes to the view.
	 * @return The name of the search view.
	 */
	@GetMapping("/search")
	public String getStatusPage(Model model) {
		model.addAttribute("appointment", new Appointment());
		return "search";
	}

	/**
	 * Handles POST requests for searching appointments by ID.
	 * @param appointmentId The ID of the appointment to search.
	 * @param model The model to pass attributes to the view.
	 * @return The name of the search view with search results.
	 */
	@PostMapping("/search")
	public String searchAppointment(@RequestParam("appointmentId") String appointmentId, Model model) {
		Appointment appointmentResult = repo.findByAppointmentId(appointmentId);

		if (appointmentResult != null) {
			model.addAttribute("appointment", appointmentResult);
		} else {
			model.addAttribute("error", "Appointment not found");
		}
		return "search";
	}

	/**
	 * Handles POST requests for creating an appointment.
	 * @param model The model to pass attributes to the view.
	 * @param appointment The appointment model attribute.
	 * @return The name of the appointment view with success or error message.
	 */
	@PostMapping("/appointment")
	public String postIndexPage(Model model, @ModelAttribute Appointment appointment) {
		if (service.saveData(appointment)) {
			model.addAttribute("success", "Your Appointment Request ID : " + appointment.getAppointmentId());
		} else {
			model.addAttribute("error", "Unable Book your Appointment due to Error");
		}
		return "appointment";
	}
}
