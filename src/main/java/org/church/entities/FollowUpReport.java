package org.church.entities;

import javax.persistence.*;

@Entity
public class FollowUpReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contactDate;

    @Column(length = 10485760)
    private String reportDetails;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public FollowUpReport() {

    }


    public FollowUpReport(String contactDate, String reportDetails) {
        this.contactDate = contactDate;
        this.reportDetails = reportDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setContactDate(String contactDate) {
        this.contactDate = contactDate;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
