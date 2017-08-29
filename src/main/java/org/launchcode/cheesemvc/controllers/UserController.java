package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, @RequestParam String verifyPassword) {

        String password = user.getPassword();
        String username = user.getUsername();
        ArrayList<User> users = new ArrayList<>(); // I think I need to pull the existing list somehow
        model.addAttribute("users", users);

        if (errors.hasErrors() || (!password.equals(verifyPassword))) {
            if(password != "" && !password.equals(verifyPassword)) {
                model.addAttribute("errorMessage", "Passwords do not match");
            // } else if(username.matches(regex, "abcABC") {
                // model.addAttribute("usernameError", "Username can only contain letters");
            }
            return "user/add";

        } else {
            user = new User();
            user.setUsername(username);
            users.add(user);

            return "user/index";

        }

    }
}
