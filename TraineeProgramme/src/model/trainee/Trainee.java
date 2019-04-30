package model.trainee;

import model.location.Location;

import java.time.LocalDate;

public class Trainee {
    private int traineeID;
    private String lastName;
    private String firstName;
    private LocalDate birthday;
    private String address;
    private String school;
    private String email;
    private Location location;

    public Trainee() {
    }

    public Trainee(int traineeID, String lastName, String firstName, LocalDate birthday, String address, String school, String email, Location location) {
        this.traineeID = traineeID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.address = address;
        this.school = school;
        this.email = email;
        this.location = location;
    }

    public Integer getTraineeID() {
        return traineeID;
    }

    public void setTraineeID(int traineeID) {
        this.traineeID = traineeID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "traineeID=" + traineeID +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", school='" + school + '\'' +
                ", email='" + email + '\'' +
                ", location=" + location +
                '}';
    }
}

