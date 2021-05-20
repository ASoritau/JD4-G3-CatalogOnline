package org.gr3;

import org.gr3.repo.UserRepo;
import org.gr3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({"org.gr3"})
//@SpringBootApplication(scanBasePackages={"org.gr3.service","org.gr3.CatalogOnline", "org.gr3.repo"})
@SpringBootApplication
public class CatalogOnlineApplication {
	@Autowired
	private static UserService userService;

	@Autowired
	private static UserRepo userRepo;

//	@Autowired
//	CatalogOnlineApplication catalogOnlineApplication;

	public static void main(String[] args) {
		SpringApplication.run(CatalogOnlineApplication.class, args);
//		testSite();
	}

//	public static void testSite() {
//		TestController controller = new TestController();
//	}
//	@RequestMapping(value = "/hello", method = RequestMethod.GET)
//	public String hello() {
//		return "hello";
//	}
//
//	List<User> users = userRepo.findAllUsers();

}
