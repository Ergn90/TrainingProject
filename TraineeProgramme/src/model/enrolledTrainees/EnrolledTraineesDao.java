package model.enrolledTrainees;

import model.courses.Courses;
import model.skala.Skala;
import model.trainee.Trainee;

import java.util.List;

public interface EnrolledTraineesDao {
    List<Courses> getAllCoursesByTrainees(Trainee trainee);
    List<Skala> getAllSkalaByTraines(Trainee trainee);
    List<Trainee> getAllTraineesByCourses(Courses courses);
    List<Skala> getAllSkalaByCourses(Courses courses);
    List<Trainee> getAllTraineesBySkala(Skala skala);
    List<Courses> getAllCoursesBySkala(Skala skala);
    Skala getSkala(int traineeID,int coursesID);
    Courses getCourse(int traineeID,int skalaID);
    Trainee getTrainee(int coursesID,int skalaID);
    boolean insertEnrolledTraines(Courses courses,Skala skala,Trainee trainee);
    boolean deletEnrolledTraines(int traineeID,int skalaID,int coursesID);
}
