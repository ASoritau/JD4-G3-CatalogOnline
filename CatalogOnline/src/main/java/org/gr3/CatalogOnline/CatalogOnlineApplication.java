package org.gr3.CatalogOnline;

import org.gr3.CatalogOnline.model.User;
import org.gr3.repo.UserRepo;
import org.gr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@SpringBootApplication
@Controller
public class CatalogOnlineApplication {
	@Autowired
	private static UserService userService;

	@Autowired
	private static UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(CatalogOnlineApplication.class, args);
//		testSite();
	}

//	public static void testSite() {
//		TestController controller = new TestController();
//	}
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}

	List<User> users = userRepo.findAllUsers();

}
