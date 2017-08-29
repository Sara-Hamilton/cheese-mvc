package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("title", "New User");
        return "user/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, @RequestParam String username, String passwordVerify) {
        String password = user.getPassword();
        //if (password == passwordVerify);
        if (password.equals(passwordVerify)) {
            return "user/add";
        } else {

            user = new User();
            user.setUsername(username);

            model.addAttribute("user");
            model.addAttribute("username", "username");
            // User user = new User();
            return "user/index";

        }

    }
}
