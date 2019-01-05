package org.church.controllers;

import org.church.entities.FollowUpReport;
import org.church.entities.MembershipStatus;
import org.church.entities.Person;
import org.church.repositories.FollowUpReportRepository;
import org.church.repositories.MembershipStatusRepository;
import org.church.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PersonViewController {

    private Logger logger = LoggerFactory.getLogger(PersonViewController.class);

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MembershipStatusRepository membershipStatusRepository;

    @Autowired
    private FollowUpReportRepository followUpReportRepository;
    private Date timenow = new Date();


    //View all the existing person in the database
    @GetMapping("/persons")
    public String getAllPerson(Model model){
        List<Person> person = personRepository.findAll();
        model.addAttribute("persons", person);
        return "persons";

    }


    //This navigates to the create Person Form
    @GetMapping("/persons/add")
    public String createNewPerson(Person person, Model model)
    {
        List<MembershipStatus> membershipStatus = membershipStatusRepository.findAll();

        model.addAttribute("statusList",membershipStatus);
        return "createpersonform";
    }

    // Creates new Record in the database
    @PostMapping("/persons")
    public String createOnePerson(@ModelAttribute Person person) {

        person.setRegisteredTime(timenow);
        personRepository.save(person);

        return "redirect:/persons";
    }


    // Retrieve a single user specified status data and present it in a form for user to edit  http.../persons/1
    @GetMapping("/persons/{id}")
    public String getOnePerson(@PathVariable Integer id, Model model) {

        Person person = personRepository.findById(id).get();
        List<MembershipStatus> membershipStatus = membershipStatusRepository.findAll();

        model.addAttribute("person",person);
        model.addAttribute("statusList",membershipStatus);

        return "persondetails";

    }

    //Submit edited status record and return to the view status page
    @PutMapping("/persons")
    public String updateOneReport(@ModelAttribute Person person){

        boolean isExist = personRepository.existsById(person.getId());

        if (isExist){
            person.setRegisteredTime(timenow);
            personRepository.save(person);
        }
        return "redirect:/persons";
    }



    @DeleteMapping("/persons")
    public String deletePersons(@RequestParam("id") List<Integer> ids) {
        List<Person> people = new ArrayList<>();

        for (Integer id : ids) {
            Person person = personRepository.findById(id).get();
            people.add(person);
        }
        personRepository.deleteInBatch(people);

        return "redirect:/persons";
    }




}













