package org.gr3.CatalogOnline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class CatalogOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogOnlineApplication.class, args);
//		testSite();
	}

//	public static void testSite() {
//		TestController controller = new TestController();
//	}
}
