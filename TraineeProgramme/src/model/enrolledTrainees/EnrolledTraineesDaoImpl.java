package model.enrolledTrainees;

import model.courses.Courses;
import model.skala.Skala;
import model.trainee.Trainee;

import java.util.List;

public class EnrolledTraineesDaoImpl implements EnrolledTraineesDao {
    @Override
    public List<Courses> getAllCoursesByTrainees(Trainee trainee) {
        return null;
    }

    @Override
    public List<Skala> getAllSkalaByTrainnes(Trainee trainee) {
        return null;
    }

    @Override
    public List<Trainee> getAllTraineesByCourses(Courses courses) {
        return null;
    }

    @Override
    public List<Skala> getAllSkalaByCourses(Courses courses) {
        return null;
    }

    @Override
    public List<Trainee> getAllTraineesBySkala(Skala skala) {
        return null;
    }

    @Override
    public List<Courses> getAllCoursesBySkala(Skala skala) {
        return null;
    }

    @Override
    public Skala getSkala(int traineeID, int coursesID) {
        return null;
    }

    @Override
    public Courses getCourse(int traineeID, int skalaID) {
        return null;
    }

    @Override
    public Trainee getTrainee(int coursesID, int skalaID) {
        return null;
    }

    @Override
    public boolean insertEnrolledTraines(Courses courses, Skala skala, Trainee trainee) {
        return false;
    }

    @Override
    public boolean updateEnrolledTraines(Courses courses, Skala skala, Trainee trainee) {
        return false;
    }

    @Override
    public boolean updateEnrolledTraines(int traineeID, int skalaID, int coursesID) {
        return false;
    }
}
