package model.courses;

import java.util.Date;

public class Courses {
    private int courseID;
    private Date courseYear;
    private int courseName;

    private String courseRoom;
    private String courseDescription;

    public Courses(int courseID, Date courseYear, int courseName, String courseRoom, String courseDescription) {
        this.courseID = courseID;
        this.courseYear = courseYear;
        this.courseName = courseName;
        this.courseRoom = courseRoom;
        this.courseDescription = courseDescription;
    }
    public Courses() {
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseName() {
        return courseName;
    }

    public void setCourseName(int courseName) {
        this.courseName = courseName;
    }

    public Date getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(Date courseYear) {
        this.courseYear = courseYear;
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
}
