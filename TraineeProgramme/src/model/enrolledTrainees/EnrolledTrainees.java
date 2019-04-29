package model.enrolledTrainees;

import model.course.Course;
import model.skala.Skala;
import model.trainee.Trainee;

public class EnrolledTrainees {
    private Course course;
    private Trainee trainee;
    private Skala skala;

    public EnrolledTrainees(Course course, Trainee trainee, Skala skala) {
        this.course = course;
        this.trainee = trainee;
        this.skala = skala;
    }

    public EnrolledTrainees() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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

    @Override
    public String toString() {
        return "EnrolledTrainees{" +
                "course=" + course +
                ", trainee=" + trainee +
                ", skala=" + skala +
                '}';
    }
}