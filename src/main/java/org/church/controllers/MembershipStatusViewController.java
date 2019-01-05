package org.church.controllers;

import org.church.entities.FollowUpReport;
import org.church.entities.MembershipStatus;
import org.church.repositories.MembershipStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MembershipStatusViewController {
    private Date timeNow = new Date();

    @Autowired
    private MembershipStatusRepository membershipStatusRepository;



    //View all the existing status in the database
    @GetMapping("/status")
    public String getAllMembershipStatus(Model model) {
        List<MembershipStatus> membershipStatus = membershipStatusRepository.findAll();
        model.addAttribute("statusList", membershipStatus);
        return "memberstatus";
    }


    //This navigates to the create status Form
    @GetMapping("/status/add")
    public String createNewStatus(MembershipStatus membershipStatus) {
        return "createstatusform";
    }

    // Creates new Record in the database
    @PostMapping("/status")
    public String createOneMembershipStatus(@ModelAttribute MembershipStatus membershipStatus) {
        membershipStatusRepository.save(membershipStatus);

        return "redirect:/status";
    }


    // Retrieve a single user specified status data and present it in a form for user to edit
    @GetMapping("/status/{id}")
    public String getOneMembershipStatus(@PathVariable Integer id,
                                         Model model) {
        MembershipStatus membershipStatus = membershipStatusRepository.findById(id).get();

        model.addAttribute("status",membershipStatus);
        return "statusdetails";

    }

    //Submit edited status record and return to the view status page
    @PutMapping("/status")
    public String updateOneReport(@ModelAttribute MembershipStatus membershipStatus){
        //FollowUpReport report = followUpReportRepository.findById(id).get();
        //boolean isExist = membershipStatusRepository.existsById(membershipStatus.getId());

        if (true){
            membershipStatusRepository.save(membershipStatus);
        }
        return "redirect:/status";
    }


    @DeleteMapping("/status")
    public String deleteManyMembershipStatus(@RequestParam("id") List<Integer> ids) {
        List<MembershipStatus> status = new ArrayList<>();

        for (Integer id : ids) {
            MembershipStatus membershipStatus = membershipStatusRepository.findById(id).get();
            status.add(membershipStatus);
        }
        membershipStatusRepository.deleteInBatch(status);

        return "redirect:/status";
    }







































/*
    @DeleteMapping("/status")
    public void deleteAllMembershipStatus() {
        membershipStatusRepository.deleteAll();

    }
*/
}
