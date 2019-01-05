package org.church.repositories;

import org.church.entities.FollowUpReport;
import org.church.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowUpReportRepository extends JpaRepository<FollowUpReport, Integer> {

     List<FollowUpReport> findByPerson(Person person);

     List<FollowUpReport> findByContactDate(String date);

}



