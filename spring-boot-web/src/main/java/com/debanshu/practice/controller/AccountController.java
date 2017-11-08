package com.debanshu.practice.controller;

import com.debanshu.practice.models.Account;
import com.debanshu.practice.services.AccountService;
import com.debanshu.practice.form.LoginForm;
import com.debanshu.practice.form.RegisterForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model) {

        if (session.getAttribute("account") != null) {
            model.addAttribute("account", (Account) session.getAttribute("account"));
            return "profile";
        } else {
            LOGGER.error("Navigating to register");
            model.addAttribute("message", "");
            model.addAttribute("registerForm", new RegisterForm());
            return "register";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model,
            @ModelAttribute RegisterForm registerForm) {

        Account account = new Account();
        account.setName(registerForm.getName());
        account.setEmail(registerForm.getEmail());
        account.setPassword(registerForm.getPassword());

        if (accountService.checkEmail(registerForm.getEmail())) {
            model.addAttribute("message", "Email address already exists");
        } else {
            Account newAccount = accountService.register(account);
            model.addAttribute("message", newAccount == null ? "Error in registration" : "Registration successful");
        }

        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model) {

        if (session.getAttribute("account") != null) {
            model.addAttribute("account", (Account) session.getAttribute("account"));
            return "profile";
        } else {
            LOGGER.error("Navigating to logins");
            model.addAttribute("loginForm", new LoginForm());
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String dologin(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model,
            @ModelAttribute LoginForm loginForm) {

        if (accountService.checkEmail(loginForm.getEmail())) {
            Account account = accountService.login(loginForm.getEmail(), loginForm.getPassword());
            if (account != null) {
                session.setAttribute("account", account);
                model.addAttribute("account", account);
                return "profile";
            } else {
                model.addAttribute("loginForm", new LoginForm());
                model.addAttribute("message", "Email address and/or password do not match");
                return "login";
            }

        } else {
            model.addAttribute("message", "Email address does not exist");
            model.addAttribute("loginForm", new LoginForm());
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String dologout(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model,
            @ModelAttribute LoginForm loginForm) {

        session.removeAttribute("account");
        model.addAttribute("message", "You logged out succesfully");

        return "index";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model,
            @PathVariable(value = "id") Long id) {

        accountService.delete(id);

        session.removeAttribute("account");
        model.addAttribute("message", "Your account is deleted");

        return "index";
    }
}
