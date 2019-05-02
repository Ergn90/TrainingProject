package model.course;

import java.time.LocalDate;

public class Course {
    private int courseID;
    private LocalDate startDate;
    private LocalDate endDate;
    private String courseName;
    private String courseRoom;
    private String courseDescription;

    public Course(int courseID, LocalDate startDate, LocalDate endDate, String courseName, String courseRoom, String courseDescription) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return getPeriodeOfYear();
    }

    public String getPeriodeOfYear(){
        byte ending;
        if (startDate.getMonthValue() < 5){
            ending = 1;
        }else{
            ending = 2;
        }
        return courseName+ startDate.getYear()+"." + ending;
    }
}
