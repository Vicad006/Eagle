package org.church.controllers;

import org.church.entities.MembershipStatus;
import org.church.entities.Person;
import org.church.repositories.MembershipStatusRepository;
import org.church.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PersonController {

    private Logger logger = LoggerFactory.getLogger(Person.class);
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MembershipStatusRepository statusRepository;


    @GetMapping("/persons")
    public List<Person> getAllPersons(){

        List<Person> personList = personRepository.findAll();

        return personList;
    }


    @GetMapping("/persons/{id}")
    public Person getOnePerson(@PathVariable Integer id){

        Person person = personRepository.findById(id).get();
        return person;
    }

/* Delete just one person
    @DeleteMapping("/persons/{id}")
    public void deleteOnePerson(@PathVariable Integer id){

        personRepository.deleteById(id); */


        /*Person person = personRepository.findById(id).get();
        personRepository.delete(person);
    }*/




    @DeleteMapping("/persons/{id}")
    public void deleteManyPersons(@PathVariable List<Integer> ids){
        List<Person> personList = new ArrayList<>();

        for(Integer id: ids) {
            Person person = personRepository.findById(id).get();
            personList.add(person);
        }
        personRepository.deleteInBatch(personList);

    }


    // .../persons?membershipstatus=1
    @PostMapping("/persons")
    public void createOnePerson(@RequestBody Person person,
                                @RequestParam("membershipstatus") Integer membershipStatusId){

        // Convert the membership status id to membership status entity:
        MembershipStatus membershipStatus = statusRepository.findById(membershipStatusId).get();

        Date timeNow = new Date();
        person.setMembershipStatus(membershipStatus);
        person.setRegisteredTime(timeNow);

        personRepository.save(person);

    }


    // .../persons?status=1
    // .../persons/4?membershipstatus=2
    @PutMapping("/persons/{id}")
    public void updateOnePerson(@RequestBody Person person,
                                @PathVariable Integer id,
                                @RequestParam("membershipstatus") Integer membershipStatusId)
    {

        // Convert the membership status id to membership status entity:
        MembershipStatus membershipStatus = statusRepository.findById(membershipStatusId).get();

        person.setMembershipStatus(membershipStatus);
        person.setId(id);

        personRepository.save(person);
    }


}



    /*
    1.) createOnePerson method for creating a new Person
    2.) To create a new person, the user must submit the Person data as specified - @RequestBody Person person
    3.) The person data definitely must be of the Person Object Type with variables

        private Integer id;  //
        private String firstAttendance;
        private String name;
        private String mobileNum;
        private String email;
        private String address;
        private String landLine;
        private String postCode;
        private String area;
        private String firstComment;
        private Date registeredTime; //
        private String entryStatus;

        @ManyToOne
        @JoinColumn(name = "membership_status_id")
        private MembershipStatus membershipStatus;

    4.) Even though the listed variables is what makes up a person type, It is the constructor that
        determines the minimum parameters required from the user to create a Person type
    5.) variable id, must be auto-generated, so we dont require the user to submit it, therefore it won't be part of the constructor
    6.) variable registeredTime is to capture the instance when data is submitted, so we are going to calculate that on the server, user should not submit it,
        so it is also excluded from the constructor
    7.) variable membership status is of data type MembershipStatus, so putting it in the constructor will require the user to submit data type MembershipStatus
        // but user can only submit integers and strings. Therefore its also excluded from the constructor
    8.) Therefore this is the constructor argument list.
        public Person(
            String firstAttendance, String name, String mobileNum, String email,
            String address, String landLine, String postCode, String area,
            String firstComment, String entryStatus)
    And that is what is require to be SUBMITTED AS @RequestBody Person person by the user

    9.) User should therefore pass the membership status as part of url parameter, hence - @RequestParam("membershipstatus") Integer membershipStatusId

    10.) The registeredTime and membership status will be updated in the submitted person USING setters
    11.) And complete data is finally saved.

     */