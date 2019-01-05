package org.church.controllers;


import org.church.entities.FollowUpReport;
import org.church.entities.Person;
import org.church.repositories.FollowUpReportRepository;
import org.church.repositories.MembershipStatusRepository;
import org.church.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/rest")
public class FollowUpReportController {


    private Date timeNow = new Date();
    
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MembershipStatusRepository statusRepository;

    @Autowired
    private FollowUpReportRepository followUpReportRepository;


    @GetMapping("/reports")

    public List<FollowUpReport> getAllFollowUpReport() {

        List<FollowUpReport> followUpReportList = followUpReportRepository.findAll();
        return followUpReportList;
    }


    @GetMapping("/reports/{id}")
    public FollowUpReport getOneFollowUpReport(@PathVariable Integer id) {
        FollowUpReport followUpReport = followUpReportRepository.findById(id).get();
        return followUpReport;

    }

    /*
    @DeleteMapping("reports/{id}")
    public void deleteOneFollowUpReport(@PathVariable Integer id) {
        followUpReportRepository.deleteById(id);



        FollowUpReport followUpReport = followUpReportRepository.findById(id).get();
        FollowUpReport followUpReport2 = followUpReportRepository.findById(id).get();
        List<FollowUpReport> reports = new ArrayList<>();
        reports.add(followUpReport);
        reports.add(followUpReport2);
        followUpReportRepository.deleteInBatch(reports);
        followUpReportRepository.delete(followUpReport);

    }

    */


    @DeleteMapping("reports/{id}")
    public void deleteManyFollowUpReports(@PathVariable List<Integer> ids) {

        List<FollowUpReport> reports = new ArrayList<>();
        for (Integer id : ids){

            FollowUpReport followUpReport = followUpReportRepository.findById(id).get();
            reports.add(followUpReport);
        }
        followUpReportRepository.deleteInBatch(reports);

    }


    @DeleteMapping("/reports")
    public void deleteAllFollowUpReport() {
        followUpReportRepository.deleteAll();


    }

    @PostMapping("/reports")
    public void createOneFollowUpReport(@RequestBody FollowUpReport followUpReport) {
        followUpReportRepository.save(followUpReport);

    }

    @PutMapping("/reports/{id}")
    public void updateOneReport(@RequestBody FollowUpReport followUpReport,
                                @PathVariable Integer id){
       //FollowUpReport report = followUpReportRepository.findById(id).get();
        boolean isExist = followUpReportRepository.existsById(id);

        if (isExist){
            followUpReport.setId(id);
        }


        followUpReportRepository.save(followUpReport);

    }




    



}



