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
    List<Skala> getSkala(int traineeID,int coursesID);
    List<Course>  getCourse(int traineeID, int skalaID);
    List<Trainee> getTrainee(int coursesID,int skalaID);
    EnrolledTrainees getEnrolledTrainees(int courseID,int skalaID,int traineeID);
    List<EnrolledTrainees> getEnrolledTraineesByCourseID(int courseID );
    List<EnrolledTrainees> getEnrolledTraineesByTraineeID(int traineeID );
    boolean insertEnrolledTrainees(Course course, Skala skala, Trainee trainee);
    boolean deleteEnrolledTrainees(int traineeID, int skalaID, int coursesID);
    boolean deleteEnrolledTraineesByTrainee(int traineeID);
    boolean deleteEnrolledTraineesByCourse(int  coursesID);

}
