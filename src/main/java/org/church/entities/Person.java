package org.church.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "person")
    private Set<FollowUpReport> followUpReport;


    public Person(){
    }



    public Person(
            String firstAttendance, String name, String mobileNum, String email,
            String address, String landLine, String postCode, String area,
            String firstComment, String entryStatus) {
        this.firstAttendance = firstAttendance;
        this.name = name;
        this.mobileNum = mobileNum;
        this.email = email;
        this.address = address;
        this.landLine = landLine;
        this.postCode = postCode;
        this.area = area;
        this.firstComment = firstComment;
        this.entryStatus = entryStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstAttendance() {
        return firstAttendance;
    }

    public void setFirstAttendance(String firstAttendance) {
        this.firstAttendance = firstAttendance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandLine() {
        return landLine;
    }

    public void setLandLine(String landLine) {
        this.landLine = landLine;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFirstComment() {
        return firstComment;
    }

    public void setFirstComment(String firstComment) {
        this.firstComment = firstComment;
    }

    public Date getRegisteredTime() {
        return registeredTime;
    }

    public void setRegisteredTime(Date registeredTime) {
        this.registeredTime = registeredTime;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

    public MembershipStatus getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(MembershipStatus membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public Set<FollowUpReport> getFollowUpReport() {
        return followUpReport;
    }

    public void setFollowUpReport(Set<FollowUpReport> followUpReport) {
        this.followUpReport = followUpReport;
    }
}
