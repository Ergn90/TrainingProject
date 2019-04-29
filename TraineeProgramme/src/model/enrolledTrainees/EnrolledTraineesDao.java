package model.enrolledTrainees;

import model.course.Course;
import model.skala.Skala;
import model.trainee.Trainee;

import java.util.List;

public interface EnrolledTraineesDao {
    List<Course> getAllCoursesByTrainee(Trainee trainee);
    List<Skala> getAllSkalaByTrainee(Trainee trainee);
    List<Trainee> getAllTraineesByCourse(Course course);
    List<Skala> getAllSkalaByCourse(Course course);
    List<Trainee> getAllTraineesBySkala(Skala skala);
    List<Course> getAllCoursesBySkala(Skala skala);
    Skala getSkala(int traineeID,int coursesID);
    Course getCourse(int traineeID, int skalaID);
    Trainee getTrainee(int coursesID,int skalaID);
    List<EnrolledTrainees> getEnrolledTrainesByCourseID(int courseID );
    List<EnrolledTrainees> getEnrolledTrainesByTraineeID(int traineeID );
    boolean insertEnrolledTraines(Course course, Skala skala, Trainee trainee);
    boolean deletEnrolledTraines(int traineeID,int skalaID,int coursesID);
    boolean deletEnrolledTrainesByTrainee(int traineeID);
    boolean deletEnrolledTrainesByCourse(int  coursesID);

}
