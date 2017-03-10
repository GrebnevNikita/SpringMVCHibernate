package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.PersonService;

import java.util.List;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired(required = true)
    @Qualifier(value = "personService")
    public void setPersonService(PersonService ps) {
        this.personService = ps;
    }

    @RequestMapping(value = "/persons/{pageid}/{searchString}", method = RequestMethod.GET)
    public String listPersons(@PathVariable int pageid, @PathVariable String searchString, Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("search", searchString);
        model.addAttribute("pageid", this.personService.getPageList(pageid,searchString));
        model.addAttribute("listPersons", this.personService.listPersons(pageid, searchString));
        return "person";
    }

    //For add and update person both
    @RequestMapping(value = "/person/add/{pageid}/{searchString}", method = RequestMethod.POST)
    public String addPerson(@PathVariable int pageid, @PathVariable String searchString, @ModelAttribute("person") Person p) {
        if (p.getId() == 0) {
            //new person, add it
            this.personService.addPerson(p);
        } else {
            //existing person, call update
            this.personService.updatePerson(p);
        }
        return "redirect:/persons/9999999/{searchString}";
    }

    @RequestMapping(value = "/person/search/{pageid}/{searchString}", method = RequestMethod.POST)
    public String searchPerson(@PathVariable int pageid, @PathVariable String searchString, @ModelAttribute("person") Person p) {
        if (p.getName().length() > 0) {
            return "redirect:/persons/1/" + p.getName();

        } else {
            return "redirect:/persons/1/nosearch";
        }
    }

    @RequestMapping("/remove/{id}/{searchString}")
    public String removePerson(@PathVariable String searchString, @PathVariable("id") int id) {
        this.personService.removePerson(id);
        return "redirect:/persons/1/{searchString}";
    }
    @RequestMapping("/create10random" )
    public String create10random()
    {
        this.personService.create10random();
        return "redirect:/persons/1/nosearch";
    }
    @RequestMapping("/deleteAll")
    public String deleteAll() {
        this.personService.deleteAll();
        return "redirect:/persons/1/nosearch";
    }
    @RequestMapping("/edit/{id}/{searchString}")
    public String editPerson(@PathVariable String searchString, @PathVariable("id") int id, Model model) {
        model.addAttribute("search", searchString);
        model.addAttribute("person", this.personService.getPersonById(id));
//        model.addAttribute("listPersons", this.personService.listPersons(1));
        return "person";
    }

}
