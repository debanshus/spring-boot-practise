package com.debanshu.practice.controller;

import com.debanshu.practice.models.Account;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(
            HttpSession session,
            Model model) {
        if (session.getAttribute("account") != null) {
            model.addAttribute("account", (Account) session.getAttribute("account"));
            return "profile";
        } else {
            LOGGER.error("Navigating to index");
            return "index";
        }
    }
}
