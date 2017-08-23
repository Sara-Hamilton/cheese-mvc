package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.cheesemvc.models.Cheese.*;

@Controller
@RequestMapping("cheese")
public class CheeseController {

      // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    //public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {
    public String processAddCheeseForm(@ModelAttribute Cheese newCheese){
        CheeseData.add(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }

        return "redirect:";
        }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {

        String name = CheeseData.getById(cheeseId).getName();

        model.addAttribute("cheeseToEdit", CheeseData.getById(cheeseId));
        model.addAttribute("title", "Edit Cheese " + name + " ID " + cheeseId );

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public String processEditForm(int cheeseId, String name, String description) {
         CheeseData.getById(cheeseId).setName(name);
         CheeseData.getById(cheeseId).setDescription(description);

        //Cheese cheeseToEdit = CheeseData.getById(cheeseId);
        //cheeseToEdit.setName(name);
        //cheeseToEdit.setDescription(description);
        //CheeseData.add(cheeseToEdit);

        return "redirect:";
    }
}
