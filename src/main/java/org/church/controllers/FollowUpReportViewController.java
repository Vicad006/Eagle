package org.church.controllers;

import org.church.entities.FollowUpReport;
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
public class FollowUpReportViewController {

    private Date timeNow = new Date();

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private MembershipStatusRepository statusRepository;
    @Autowired
    private FollowUpReportRepository followUpReportRepository;


    // Retrieve report for a single person http.../reports?person=7
    @GetMapping("/reports")
    public String getOnePersonReport(@RequestParam("person") Integer personId, Model model) {

        Person person = personRepository.findById(personId).get();
        List<FollowUpReport> report = followUpReportRepository.findByPerson(person);
        model.addAttribute("report",report);

        return "personreports";
    }
}
