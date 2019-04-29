package model.course;

import controller.Controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Course {
    private int courseID;
    private Date startDate;
    private Date endDate;
    private String courseName;
    private String courseRoom;
    private String courseDescription;

    public Course(int courseID, Date startDate, Date endDate, String courseName, String courseRoom, String courseDescription) {
        this.courseID = courseID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseName = courseName;
        this.courseRoom = courseRoom;
        this.courseDescription = courseDescription;
    }

    public Course() {
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {

        this.courseName = courseName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseID() {
        return courseID;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return courseName + "-"+getYearCourse();
    }

    private String getYearCourse() {
        LocalDate localDate = Controller.convertToLocalDateViaMilisecond(startDate);
        return String.valueOf(localDate.getYear());
    }
}
