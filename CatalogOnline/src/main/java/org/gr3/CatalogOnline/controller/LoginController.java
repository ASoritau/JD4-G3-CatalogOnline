package org.gr3.CatalogOnline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {
    private boolean isUserLoggedIn = false;

    @RequestMapping("/")
    public RedirectView redirectWithUsingRedirectView(@RequestParam(required = false, defaultValue = "false") String logged) {
        this.isUserLoggedIn = Boolean.parseBoolean(logged);
        if (isUserLoggedIn) {
            return new RedirectView(userOverview());
        } else {
            return new RedirectView(login());
        }
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/dashboard")
    public String userOverview() {
        return "dashboard";
    }
}