package model.enrolledTrainees;

import model.courses.Courses;
import model.skala.Skala;
import model.trainee.Trainee;

public class EnrolledTrainees {
    private Courses course;
    private Trainee trainee;
    private Skala skala;

    public EnrolledTrainees(Courses course, Trainee trainee, Skala skala) {
        this.course = course;
        this.trainee = trainee;
        this.skala = skala;
    }

    public EnrolledTrainees() {
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public Skala getSkala() {
        return skala;
    }

    public void setSkala(Skala skala) {
        this.skala = skala;
    }
}
