package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Pattern;


@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("users", UserData.getAll());

        return "user/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("users", UserData.getAll());
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User newUser, Errors errors, String verifyPassword) {

        model.addAttribute("users", UserData.getAll());

        String password = newUser.getPassword();
        String username = newUser.getUsername();
        //String email = user.getEmail();
        //int userId = user.getUserId();

        if (errors.hasErrors() || (!password.equals(verifyPassword))) {
            if (password != "" && !password.equals(verifyPassword)) {
                model.addAttribute("errorMessage", "Passwords do not match");
                } else if(! Pattern.matches(".*[a-zA-Z]+.*[a-zA-Z]", username)) {
                model.addAttribute("usernameError", "Username can only contain letters");
            }
            return "user/add";

        } else {
            //user = new User();
            //user.setUsername(username);
            //user.setEmail(email);
            //user.setUserId(userId);
            //user.setDate();
            UserData.add(newUser);

            return "user/index";
        }

    }


    @RequestMapping(value = "/details/{userId}", method = RequestMethod.GET)
    public String listUserAndEmail(Model model, @PathVariable int userId) {

        User user = UserData.getById(userId);

        //model.addAttribute("users", UserData.getAll());
        model.addAttribute("user", user);

        return "user/user-detail";
    }
}



