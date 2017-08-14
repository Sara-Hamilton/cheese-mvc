package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping("cheese")
public class CheeseController {

    // static HashMap<String, String> cheeses = new HashMap<>();
    static HashMap<String, Cheese> cheeses = new HashMap<>();

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        // ArrayList<String> cheeses = new ArrayList<>();
        // this was moved outside of the method and made static so that it can be accessed
        // and used from another method in the add template
        // cheeses.add("cheddar"); - these were used initially to add cheeses to the list
        // cheeses.add("parmesan");
        // cheeses.add("munster");

        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {
        if(!(cheeseName.isEmpty())) {
            //cheeses.put(cheeseName, cheeseDescription);
            Cheese cheese = new Cheese();
            cheese.setName(cheeseName);
            cheese.setDescription(cheeseDescription);
            cheeses.put(cheeseName, cheese);
        }
        // redirect to /cheese - the requestMapping specified in the controller for the class
        // to redirect somewhere else, specify the location after the :
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam ArrayList<String> cheeseList) {

        for (String item : cheeseList) {
        cheeses.remove(item);
            }
        return "redirect:";
        }
}
