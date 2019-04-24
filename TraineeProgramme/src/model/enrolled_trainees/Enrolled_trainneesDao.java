package model.enrolled_trainees;

import java.util.Set;

public interface Enrolled_trainneesDao {
    Enrolled_trainees getEnrolled_trainees(int id);
    Set<Enrolled_trainees> getAllEnrolled_trainees();
    boolean insertEnrolled_trainees(Enrolled_trainees courses);
    boolean updateEnrolled_trainees(Enrolled_trainees courses);
    boolean deleteEnrolled_trainees(int id);

}
