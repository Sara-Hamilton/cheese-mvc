package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.CheeseType;
import org.launchcode.cheesemvc.models.Data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

      // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese()); // same as ("cheese", new Cheese())
        model.addAttribute("cheeseTypes", CheeseType.values());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    //public String processAddCheeseForm(@RequestParam String cheeseName, @RequestParam String cheeseDescription) {
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese, Errors errors, Model model){

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/add";
        }

        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
        }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {

        // String name = CheeseData.getById(cheeseId).getName();
        Cheese cheese = cheeseDao.findOne(cheeseId);

        model.addAttribute("cheese", cheese);
        model.addAttribute("title", "Edit Cheese " + cheese.getName() + " ID " + cheeseId );
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public String processEditForm(Model model, @ModelAttribute @Valid Cheese cheese, Errors errors,
                                  @RequestParam int cheeseId, String name, String description, CheeseType type, int rating) {

        // cheeseID added to model and th:if statements added to edit.html hidden input to prevent cheeseId from being set to 0 if there are errors
        if (errors.hasErrors()) {
            model.addAttribute("cheeseID", cheeseId);
            //model.addAttribute("cheese", cheese);
            model.addAttribute("title", "Edit Cheese " + cheese.getName() + " ID " + cheeseId );
            model.addAttribute("cheeseTypes", CheeseType.values());
            return "cheese/edit";
        }

        // CheeseData.getById(cheeseId) was replaced by cheeseDao.findOne(cheeseId)
        cheeseDao.findOne(cheeseId).setName(name);
        cheeseDao.findOne(cheeseId).setDescription(description);
        cheeseDao.findOne(cheeseId).setType(type);
        cheeseDao.findOne(cheeseId).setRating(rating);

        cheeseDao.save(cheeseDao.findOne(cheeseId));

        return "redirect:";
    }

}
