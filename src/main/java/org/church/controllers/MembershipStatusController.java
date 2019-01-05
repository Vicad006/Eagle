package org.church.controllers;

import org.church.entities.MembershipStatus;
import org.church.repositories.MembershipStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class MembershipStatusController {
    private Date timeNow = new Date();

    @Autowired
    private MembershipStatusRepository membershipStatusRepository;

    @GetMapping("/status")
    public List<MembershipStatus> getAllMembershipStatus() {
        List<MembershipStatus> membershipStatus = membershipStatusRepository.findAll();
        return membershipStatus;
    }

    @GetMapping("/status/{id}")
    public MembershipStatus getOneMembershipStatus(@PathVariable Integer id) {
        MembershipStatus membershipStatus = membershipStatusRepository.findById(id).get();
        return membershipStatus;

    }

    @PostMapping("/status")
    public void createOneMembershipStatus(@RequestBody MembershipStatus membershipStatus) {
        membershipStatusRepository.save(membershipStatus);

    }


    @DeleteMapping("/status")
    public void deleteAllMembershipStatus() {
        membershipStatusRepository.deleteAll();

    }

    @DeleteMapping("/status/{id}")
    public void deleteManyMembershipStatus(@PathVariable List<Integer> ids) {
        List<MembershipStatus> status = new ArrayList<>();

        for (Integer id : ids) {
            MembershipStatus membershipStatus = membershipStatusRepository.findById(id).get();
            status.add(membershipStatus);
        }
        membershipStatusRepository.deleteInBatch(status);

    }

}
